package com.rabo.service;

import java.util.List;

import com.rabo.model.Record;

public interface ValidateorService {
	public List<Record> findDuplicateRecords(List<Record> record);
	public List<Record> findErrorEndBalRecrd(List<Record> record);
}
