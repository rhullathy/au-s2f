package com.vw.formhandler.common;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Stack;
import org.apache.log4j.Logger;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NoHttpResponseException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.InitializingBean;

public class HttpClientUtil implements InitializingBean
{
	private static final Logger log = Logger.getLogger(HttpClientUtil.class);

	private HttpClient httpClient = null;
	private HttpClientUtilConfig config = null;

	private String useProxy;

	private String proxyHost;

	private String proxyPort;

	private String pingMaxRetry;

	private String maxConnections;

	private String defaultEncoding;
	
	private HttpClientUtil()
	{
		config = new HttpClientUtilConfig();
//httpClient.getState().setCredentials(new AuthScope("integration.vw.com", 80), new UsernamePasswordCredentials("kbhatia", "KunBha116"));
	}
	
	/**
	 * @param serviceUrlSuffix
	 * @param inputString
	 * @return
	 * @throws Exception
	 */
	public HttpClientUtilResponse invokeService(String urlStr, String inputString) throws Exception 
	{
		return postXmlToUrl(urlStr, inputString);
	}
	
	/**
	 * Configure HttpClient with set of defaults from Properties obj
	 */
	private HttpClient configureHttpClient()
	{
		if (Boolean.parseBoolean(useProxy))
		{
			if (config.getProxyHost()== null)
				config.setProxyHost(proxyHost);
			if (config.getProxyPort()== null)
				config.setProxyPort(Integer.parseInt(proxyPort));			
		}

		if (config.getMaxErrorRetry()== null)
			config.setMaxErrorRetry(Integer.parseInt(pingMaxRetry));
			
		if (config.getMaxConnections()==null)
			config.setMaxConnections(Integer.parseInt(maxConnections));

		/* Set http client parameters */
		HttpClientParams httpClientParams = new HttpClientParams();
		if (config.getUserAgent() == null) {
			config.setUserAgent(
					quoteAttributeValue("Java/"
							+ System.getProperty("java.version") + "/"
							+ System.getProperty("java.class.version") + "/"
							+ System.getProperty("java.vendor")),

					quoteAttributeName("Platform"),
					quoteAttributeValue("" + System.getProperty("os.name")
							+ "/" + System.getProperty("os.arch") + "/"
							+ System.getProperty("os.version"))
					);

		}
		httpClientParams.setParameter(HttpMethodParams.USER_AGENT, config.getUserAgent());
		
		httpClientParams.setParameter(HttpClientParams.RETRY_HANDLER,			
				new HttpMethodRetryHandler() {
					public boolean retryMethod(HttpMethod method, IOException exception, int executionCount) 
					{
						if (executionCount > config.getMaxErrorRetry()) {
							log.error("Maximum Number of Retry attempts reached, will not retry");
							return false;
						}
						log.debug("Retrying request. Attempt " + executionCount);
						if (exception instanceof NoHttpResponseException) {
							log.debug("Retrying on NoHttpResponseException");
							return true;
						}
						if (exception instanceof InterruptedIOException) {
							log.error("Will not retry on InterruptedIOException", exception);
							return false;
						}
						if (exception instanceof UnknownHostException) {
							log.error("Will not retry on UnknownHostException", exception);
							return false;
						}
						if (!method.isRequestSent()) {
							log.debug("Retrying on failed sent request");
							return true;
						}
						return false;
					}
				});

		/* Set host configuration */
		HostConfiguration hostConfiguration = new HostConfiguration();

		/* Set http client */
		httpClient = new HttpClient(httpClientParams);

		/* Set proxy if configured */
		if (config.isSetProxyHost() && config.isSetProxyPort()) {
			log.debug("Configuring Proxy. Proxy Host: " + config.getProxyHost()
					+ " Proxy Port: " + config.getProxyPort());
			hostConfiguration.setProxy(config.getProxyHost(),
					config.getProxyPort());
			if (config.isSetProxyUsername() && config.isSetProxyPassword()) {
				httpClient.getState()
						.setProxyCredentials(
								new AuthScope(config.getProxyHost(),
										config.getProxyPort()),
								new UsernamePasswordCredentials(config
										.getProxyUsername(), config
										.getProxyPassword()));
			}
		}

		httpClient.setHostConfiguration(hostConfiguration);

		return httpClient;
	}

	public String getUseProxy() {
		return useProxy;
	}

	public void setUseProxy(String useProxy) {
		this.useProxy = useProxy;
	}

	public String getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	public String getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(String proxyPort) {
		this.proxyPort = proxyPort;
	}

	public String getPingMaxRetry() {
		return pingMaxRetry;
	}

	public void setPingMaxRetry(String pingMaxRetry) {
		this.pingMaxRetry = pingMaxRetry;
	}

	public String getMaxConnections() {
		return maxConnections;
	}

	public void setMaxConnections(String maxConnections) {
		this.maxConnections = maxConnections;
	}

	public String getDefaultEncoding() {
		return defaultEncoding;
	}

	public void setDefaultEncoding(String defaultEncoding) {
		this.defaultEncoding = defaultEncoding;
	}

	@SuppressWarnings("finally")
	private HttpClientUtilResponse postXmlToUrl(String serviceUrl, String xmlParameterVal) throws Exception
	{
		HttpClientUtilResponse response = new HttpClientUtilResponse();
		PostMethod method = new PostMethod(serviceUrl);
		response.setStatus(-1);

		try {
			/* Set content type and encoding */
			log.debug("Setting content-type to application/x-www-form-urlencoded; charset="
					+ defaultEncoding);
			method.addRequestHeader("Content-Type",
					"application/x-www-form-urlencoded; charset="
							+ defaultEncoding);
			/* Set X-Amazon-User-Agent to header */
			method.addRequestHeader("X-Amazon-User-Agent",
					config.getUserAgent());

			boolean shouldRetry = true;
			int retries = Integer.parseInt(pingMaxRetry);
			do {
				log.debug("Sending Request to host:  " + serviceUrl);

				try {
					/* Post param to PostMethod obj */
					method.addParameter(FormHandlerConstants.XMLPARAM_NAME, xmlParameterVal);
					
					/* Submit request */
					response.setStatus(httpClient.executeMethod(method));
					response.setResponseByteArray( IOUtils.toByteArray(method.getResponseBodyAsStream()) );

					/* Successful response. */
					if (response.getStatus() == HttpStatus.SC_OK)
					{
						response.setUrlPostSuccessful(true);
						shouldRetry = false;
					} 
					else 
					{
						if ((response.getStatus() == HttpStatus.SC_INTERNAL_SERVER_ERROR || response.getStatus() == HttpStatus.SC_SERVICE_UNAVAILABLE)
								&& pauseIfRetryNeeded(++retries))
						{
							if (response.getPreviousTryResponsesStack()==null)
								response.setPreviousTryResponsesStack(new Stack<HttpClientUtilResponse>());
							response.getPreviousTryResponsesStack().push(response);
							shouldRetry = true;
						}
						else
							shouldRetry = false;
					}

				} catch (IOException ioe) {
					log.error("Caught IOException exception", ioe);
					throw ioe;
				} catch (Exception e) {
					log.error("Caught Exception", e);
					throw e;
				} catch (Throwable t) {
					log.error("Caught Exception", t);
					throw t;
				} finally {
					method.releaseConnection();
				}
			} while (shouldRetry);

		} catch (Throwable t) {
			log.error("Caught Exception", t);
			throw new Exception(t.getMessage());
		}
		finally {
			return response;			
		}
	}

	/**
	 * Exponential sleep on failed request. Sleeps and returns true if retry
	 * needed
	 * 
	 * @param retries
	 *            current retry
	 * @throws java.lang.InterruptedException
	 */
	private boolean pauseIfRetryNeeded(int retries) throws InterruptedException {
		if (retries <= config.getMaxErrorRetry()) {
			long delay = (long) (Math.pow(4, retries) * 100L);
			log.debug("Retriable error detected, will retry in " + delay
					+ "ms, attempt number: " + retries);
			Thread.sleep(delay);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Remove all leading whitespace, trailing whitespace, repeated whitespace
	 * and replace any interior whitespace with a single space
	 */
	private String clean(String s) {
		return s.replaceAll("\\s", " ").replaceAll(" {2,}", " ").trim();
	}
	private String quoteAttributeName(String s) {
		return clean(s).replace("\\", "\\\\").replace("=", "\\=");
	}
	private String quoteAttributeValue(String s) {
		return clean(s).replace("\\", "\\\\").replace(";", "\\;")
				.replace(")", "\\)");
	}

	public void afterPropertiesSet() throws Exception {
		httpClient = configureHttpClient();
	}
}
