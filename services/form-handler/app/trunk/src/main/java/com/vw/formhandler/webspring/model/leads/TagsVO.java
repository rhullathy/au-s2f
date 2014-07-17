package com.vw.formhandler.webspring.model.leads;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tags", propOrder = {
    "tagList"
})
/*
 * Represents the <tags>
 * 1) to support FormHandler all interfaces that support Leads Submission:
 */
public class TagsVO implements Serializable
{
	@XmlElement(name="tag")
	public List<TagVO> tagList;

	public TagsVO() {}
	
	public TagsVO(List<TagVO> tagList) {
		this.tagList = tagList;
	}

	public List<TagVO> getTagList() {
		return tagList;
	}

	public void setTagList(List<TagVO> tagList) {
		this.tagList = tagList;
	}
}
