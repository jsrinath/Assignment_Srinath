package com.rabo.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Records {
	 List<Record> records;
	 @XmlElement(name="record")
	public List<Record> getRecord() {
		return records;
	}

	public void setRecord(List<Record> records) {
		this.records = records;
	}
}
