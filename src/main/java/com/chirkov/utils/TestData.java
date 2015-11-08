package com.chirkov.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

public class TestData {

	
	@DataProvider(name = "signUpData")
	public static Object[][] signUpData() throws IOException, FileNotFoundException{
		
		CSVReader reader = new CSVReader(new FileReader("src/test/resources/signUpData.csv"));
		
		List<String[]> allRows = reader.readAll();
		Object[][] data = new Object[allRows.size()][];
		
		for (int i=0; i < allRows.size(); i++){
		data[i] = allRows.get(i);	
		};
		data[allRows.size()-1][2]=DataSupplier.props.getProperty("primaryUserEmail");
		return data;
	}
}
