package com.rabo.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Records {
	 List<Record> recordsList;
	 @XmlElement(name="record")
	public List<Record> getRecord() {
		return recordsList;
	}

	public void setRecord(List<Record> records) {
		this.recordsList = records;
	}
}
