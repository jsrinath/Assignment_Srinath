package com.rabo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rabo.common.RaboConstants;
import com.rabo.model.Person;
import com.rabo.model.Record;
import com.rabo.model.Records;
@Service
public class FileExtractorServiceImpl implements FileExtractorService {
    private static final Logger logger = LoggerFactory.getLogger(FileExtractorServiceImpl.class);

	@Override
	public List<Record> extractCSVRecords(File file) throws IOException {
		try(Reader reader = new FileReader(file);) {
			 
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
			logger.error(RaboConstants.ERROR.getValue(), e);
			throw new FileNotFoundException("Test");
		} catch (NumberFormatException e) {
			logger.error(RaboConstants.ERROR.getValue(), e);
			throw new NumberFormatException("Test");
		}
	}
	

	@Override
	public List<Person> extractCSVRecordsPerson(File file)  throws IOException{
		try(Reader reader = new FileReader(file);) {
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
			logger.error(RaboConstants.ERROR.getValue(), e);
			throw new FileNotFoundException("Test");
		}
	}
	
	
	public List<Record> extractXMLRecords(File file) throws JAXBException{
        JAXBContext jc;
        Records records = null;
		try {
			jc = JAXBContext.newInstance(Records.class);
	        Unmarshaller unmarshaller = jc.createUnmarshaller();
	         records = (Records) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			logger.error(RaboConstants.ERROR.getValue(), e);
			throw new JAXBException("Test");
		}
		
		return records.getRecord();
	}

}
