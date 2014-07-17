package com.vw.formhandler.webspring.mvc.response.kuba;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.vw.formhandler.webspring.mvc.response.AbstractServiceResponseErrorVO;

/**
 * @author Kunal Bhatia
 */
@SuppressWarnings("serial")
@XmlRootElement(name="kubaServiceResponseError")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "kubaServiceResponseError")
public class KubaServiceResponseErrorVO extends AbstractServiceResponseErrorVO
{

	@Override
	public absractErrorSubclassSimpleNames getClassSimpleName() {
		return absractErrorSubclassSimpleNames.KubaServiceResponseErrorVO;
	}
}
