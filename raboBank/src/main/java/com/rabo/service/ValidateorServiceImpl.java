package com.rabo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rabo.model.Record;
@Service
public class ValidateorServiceImpl implements ValidateorService{

	@Override
	public List<Record> findDuplicateRecords(List<Record> records) {
		/*records.stream().collect(Collectors.groupingBy(Function.identity(),
                Collectors.counting()))
                .entrySet().stream()
                  .filter(e -> e.getValue() > 1L)
                .map(e -> e.getKey())
                .collect(Collectors.toList())
                .forEach(rcd -> System.out.println(rcd.getReference()+"==>"+rcd.getDescription()));*/
		Map<Integer, Record> uniqeRecords = new HashMap<Integer, Record>();
		List<Record> duplicateRecords = new ArrayList<Record>();
		for (Record record : records) {
			if (uniqeRecords.containsKey(record.getReference())) {
				duplicateRecords.add(record);
			} else {
				uniqeRecords.put(record.getReference(), record);
			}
		}
		List<Record> finalDuplicateRecords = new ArrayList<Record>();
		finalDuplicateRecords.addAll(duplicateRecords);
		for (Record record : duplicateRecords) {
			if (null != uniqeRecords.get(record.getReference())) {
				finalDuplicateRecords.add(uniqeRecords.get(record.getReference()));
				uniqeRecords.remove(record.getReference());
			}
		}
		return finalDuplicateRecords;

		/*for(Record rcd:records) {
			
		}*/
	}

	@Override
	public List<Record> findErrorEndBalRecrd(List<Record> records) {
		List<Record> endBalErrRcrds = new ArrayList<Record>();
		for (Record record : records) {
			if (Math.round((record.getStartBalance() + record.getMutation()) - record.getEndBalance()) != 0) {
				endBalErrRcrds.add(record);
				System.out.println(record.getAccountNumber()+"-"+record.getEndBalance());
			}
		}
		return endBalErrRcrds;
	}

}
