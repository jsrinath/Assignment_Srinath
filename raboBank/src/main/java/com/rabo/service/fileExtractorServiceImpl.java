package com.rabo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rabo.model.Person;
import com.rabo.model.Record;
import com.rabo.model.Records;
@Service
public class fileExtractorServiceImpl implements FileExtractorService {

	@Override
	public List<Record> extractCSVRecords(File file) {
		// TODO Auto-generated method stub
		try {
			Reader reader = new FileReader(file);
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			
            strategy.setType(Record.class);
            String[] memberFieldsToBindTo = {"reference","accountNumber","description","startBalance","mutation","endBalance"};
            strategy.setColumnMapping(memberFieldsToBindTo);

            CsvToBean<Record> csvToBean = new CsvToBeanBuilder(reader)
                    .withMappingStrategy(strategy)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Record> recordList = csvToBean.parse();
            return recordList;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public List<Person> extractCSVRecordsPerson(File file) {
		// TODO Auto-generated method stub
		try {
			Reader reader = new FileReader(file);
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			
            strategy.setType(Person.class);
            String[] memberFieldsToBindTo = {"firstName","surName","issueCount","dateofBirth"};
            strategy.setColumnMapping(memberFieldsToBindTo);

            CsvToBean<Person> csvToBean = new CsvToBeanBuilder(reader)
                    .withMappingStrategy(strategy)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Person> recordList = csvToBean.parse();
            return recordList;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<Record> extractXMLRecords(File file){
        JAXBContext jc;
        Records records = null;
		try {
			jc = JAXBContext.newInstance(Records.class);
		

	        Unmarshaller unmarshaller = jc.createUnmarshaller();
	         records = (Records) unmarshaller.unmarshal(file);
	     /*   Marshaller marshaller = jc.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.marshal(records, System.out);*/
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return records.getRecord();
	}

}
