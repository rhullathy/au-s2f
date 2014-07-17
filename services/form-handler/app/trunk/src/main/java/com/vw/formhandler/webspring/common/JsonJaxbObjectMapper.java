package com.vw.formhandler.webspring.common;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

public class JsonJaxbObjectMapper extends ObjectMapper{
	public JsonJaxbObjectMapper()
	{
		super();
		JaxbAnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
	    // make deserializer use JAXB annotations (only)
	    this.getDeserializationConfig().setAnnotationIntrospector(introspector);
	    // make serializer use JAXB annotations (only)
	    this.getSerializationConfig().setAnnotationIntrospector(introspector);
	    this.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
	}
}