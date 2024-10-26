package com.springrest.worldbank.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


	   
	        File file = new File("C:\\worldcities.xlsx");   //creating a new file instance

	        FileInputStream fis = null;
	        try {
	            fis = new FileInputStream(file);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        //creating Workbook instance that refers to .xlsx file
	        XSSFWorkbook wb = null;
	        try {
	            wb = new XSSFWorkbook(fis);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object

	        Row row;
	        int rowNum = sheet.getLastRowNum() + 1;
	        int colNum = sheet.getRow(0).getLastCellNum();
	        List<String> listCities = new ArrayList<>();


	      //  System.out.println(rowNum);//196
	     //   System.out.println(colNum);//196

	        for (int i = 0; i <sheet.getLastRowNum(); i++) {

	            row = sheet.getRow(i);

	            String cities=null;
	            if (row.getCell(0) == null) {
	                cities = null;
	            } else {
	                cities = row.getCell(0).toString();
	                listCities.add(cities);
	            }
	           
	        }
	        Collections.sort(listCities);

	        for(String str:listCities) {
            	
            	System.out.println(str);

           }

            System.out.println("list size"+listCities.size());

	    }


		
		
		
		
		
	

}
