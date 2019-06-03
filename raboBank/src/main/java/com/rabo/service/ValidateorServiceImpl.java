package com.rabo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.rabo.model.Record;

@Service
public class ValidateorServiceImpl implements ValidateorService{

	@Override
	public List<Record> findDuplicateRecords(List<Record> records) {
		Map<Integer, Record> uniqeRecords = new HashMap<>();
		List<Record> duplicateRecords = new ArrayList<>();
		for (Record record : records) {
			if (uniqeRecords.containsKey(record.getReference())) {
				duplicateRecords.add(record);
			} else {
				uniqeRecords.put(record.getReference(), record);
			}
		}
		List<Record> finalDuplicateRecords = new ArrayList<>();
		finalDuplicateRecords.addAll(duplicateRecords);
		for (Record record : duplicateRecords) {
			if (null != uniqeRecords.get(record.getReference())) {
				finalDuplicateRecords.add(uniqeRecords.get(record.getReference()));
				uniqeRecords.remove(record.getReference());
			}
		}
		return finalDuplicateRecords;
	}

	@Override
	public List<Record> findErrorEndBalRecrd(List<Record> records) {
		List<Record> endBalErrRcrds = new ArrayList<>();
		for (Record record : records) {
			if (Math.round((record.getStartBalance() + record.getMutation()) - record.getEndBalance()) != 0) {
				endBalErrRcrds.add(record);
			}
		}
		return endBalErrRcrds;
	}

}
