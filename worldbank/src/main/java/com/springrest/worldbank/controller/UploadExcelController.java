package com.springrest.worldbank.controller;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.springrest.worldbank.model.WorldBankData;
import com.springrest.worldbank.repository.WorldBankRepo;
import com.springrest.worldbank.service.WorldBankService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.RowId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class UploadExcelController {

WorldBankData worldBankData;

@Autowired
WorldBankRepo worldBankRepo;

@Autowired
WorldBankService worldBankService;


    @RequestMapping(value="/upload",method=RequestMethod.POST)
    public String uploadExcelFiles(@RequestParam("file") MultipartFile file ){
        {
            File convertFile=new File("C:\\"+file.getOriginalFilename());
            try {
                convertFile.createNewFile();
                FileOutputStream fos=new FileOutputStream(convertFile);
                fos.write(file.getBytes());

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return "file uploaded to "+convertFile;
        }
    }

    
    
    


    
    
    @RequestMapping(value="/page",method=RequestMethod.POST)
    public String returnPage(){
        {
                       return "page is returned";
        }
        
        
        
    }


    
    
    
    
    
    
    
    
    
    

    @PostMapping("/save")
    public String saveData() {
        //public String saveData(@RequestBody Employee employee) {
//	public String saveData(@RequestParam("Customer_Name") String Customer_Name,@RequestParam("Account_Number") String Account_Number,@RequestParam("email") String email,@RequestParam("balance") String balance) {
        /*
         * employee.setCustomer_Name(Customer_Name);
         * employee.setAccount_Number(Account_Number); employee.setEmail(email);
         * employee.setBalance(balance);
         */
    	
    	    try {
            File file = new File("C:\\Inflation-data-updated.xlsx"); 
            FileInputStream fis = new FileInputStream(file);  

           XSSFWorkbook wb = new XSSFWorkbook(fis);
          XSSFSheet sheet = wb.getSheetAt(0); 
            
           
            //	HSSFWorkbook wb=new HSSFWorkbook(fis);
            //	HSSFSheet sheet=wb.getSheetAt(0);
            Row row;
            int rowNum = sheet.getLastRowNum() + 1;         
            int colNum = sheet.getRow(0).getLastCellNum();
            System.out.println(rowNum);//196
            List<String> listYear=new ArrayList<String>();


          for(int i=47;i<=56;i++) {         	
            	listYear.add(sheet.getRow(0).getCell(i).toString());
          }
            

            //2012  2013 2014 2015 2016 2017 2018 2019 2020 2021
            
            
       //     for(int j=47;j<=colNum;j++) {
                
            for(int i=1;i<=sheet.getLastRowNum();i++) {
                row=sheet.getRow(i);        
              /*  String id;
                if(row.getCell(0)==null)
                {
                    id=null;
                } else
                {
                    id=row.getCell(0).toString();
                }

                */
                
                String countrycode;
                if(row.getCell(0)==null) {
                    countrycode=null;
                }
                else {
                    countrycode=row.getCell(0).toString();
                }

                String imfcountrycode;
                if(row.getCell(1)==null) {
                    imfcountrycode=null;
                }
                else {
                    imfcountrycode=row.getCell(1).toString();
                }


                String country;
                if(row.getCell(2)==null) {
                    country=null;
                }
                else {
                    country=row.getCell(2).toString();
                }


                String indicatortype;
                if(row.getCell(3)==null) {
                    indicatortype=null;
                }
                else {
                    indicatortype=row.getCell(3).toString();
                }


                String seriesname;
                if(row.getCell(4)==null) {
                    seriesname=null;
                }
                else {
                	
                    seriesname=row.getCell(4).toString();

                }              
                
              
                                        
                //2012 first row
                //2012 second row 
                //2012 third row
                             
                //2013 first row
                //2013 second row
                //2013 third
                //2013 nth row
                
                //2014 1st row
                //2014 2nd row
                            	
                //  worldBankData.setId(id);
                                   
                 
                       if(row.getCell(47)!=null){
                           String hcpidata1;
                           worldBankData=new WorldBankData();

                	  worldBankData.setCountry(country);
                      worldBankData.setCountrycode(countrycode);
                      worldBankData.setImfcountrycode(imfcountrycode);
                      worldBankData.setIndicatortype(indicatortype);
                      worldBankData.setSeriesname(seriesname);   
                      
                       worldBankData.setYear(listYear.get(0)); 
                        //hcpidata=row.getCell(56).toString();
                      	hcpidata1=row.getCell(47).toString();
                        worldBankData.setHcpidata(hcpidata1);  
                        worldBankRepo.insert(worldBankData);

                  }   
                  
                  
                  
                  
                   if(row.getCell(48)!=null) {
                       String hcpidata2;
                       worldBankData=new WorldBankData();

                	  worldBankData.setCountry(country);
                      worldBankData.setCountrycode(countrycode);
                      worldBankData.setImfcountrycode(imfcountrycode);
                      worldBankData.setIndicatortype(indicatortype);
                      worldBankData.setSeriesname(seriesname);   
                      
                       worldBankData.setYear(listYear.get(1)); 
                        //hcpidata=row.getCell(56).toString();
                      	hcpidata2=row.getCell(48).toString();
                        worldBankData.setHcpidata(hcpidata2);  
                        worldBankRepo.insert(worldBankData);
                  }
                                          
                  
                   if(row.getCell(49)!=null) {
                       String hcpidata3;
                       worldBankData=new WorldBankData();

                	  worldBankData.setCountry(country);
                      worldBankData.setCountrycode(countrycode);
                      worldBankData.setImfcountrycode(imfcountrycode);
                      worldBankData.setIndicatortype(indicatortype);
                      worldBankData.setSeriesname(seriesname);   
                      
                       worldBankData.setYear(listYear.get(2)); 
                        //hcpidata=row.getCell(56).toString();
                      	hcpidata3=row.getCell(49).toString();
                        worldBankData.setHcpidata(hcpidata3);  
                        worldBankRepo.insert(worldBankData);

                    }   
                 
                 
                   if(row.getCell(50)!=null){
                       String hcpidata4;
                       worldBankData=new WorldBankData();

                	  worldBankData.setCountry(country);
                      worldBankData.setCountrycode(countrycode);
                      worldBankData.setImfcountrycode(imfcountrycode);
                      worldBankData.setIndicatortype(indicatortype);
                      worldBankData.setSeriesname(seriesname);   
                      
                       worldBankData.setYear(listYear.get(3)); 
                        //hcpidata=row.getCell(56).toString();
                      	hcpidata4=row.getCell(50).toString();
                        worldBankData.setHcpidata(hcpidata4);  
                        worldBankRepo.insert(worldBankData);
                  }   
                  
                  
                  
                   if(row.getCell(51)!=null) {
                       String hcpidata5;
                       worldBankData=new WorldBankData();

                	  worldBankData.setCountry(country);
                      worldBankData.setCountrycode(countrycode);
                      worldBankData.setImfcountrycode(imfcountrycode);
                      worldBankData.setIndicatortype(indicatortype);
                      worldBankData.setSeriesname(seriesname);   
                      
                       worldBankData.setYear(listYear.get(4)); 
                        //hcpidata=row.getCell(56).toString();
                      	hcpidata5=row.getCell(51).toString();
                        worldBankData.setHcpidata(hcpidata5);  
                        worldBankRepo.insert(worldBankData);
                	   }  
                  
                  
                   if(row.getCell(52)!=null) {
                       String hcpidata6;
                       worldBankData=new WorldBankData();

                	  worldBankData.setCountry(country);
                      worldBankData.setCountrycode(countrycode);
                      worldBankData.setImfcountrycode(imfcountrycode);
                      worldBankData.setIndicatortype(indicatortype);
                      worldBankData.setSeriesname(seriesname);   
                      
                       worldBankData.setYear(listYear.get(5)); 
                        //hcpidata=row.getCell(56).toString();
                      	hcpidata6=row.getCell(52).toString();
                        worldBankData.setHcpidata(hcpidata6);  
                        worldBankRepo.insert(worldBankData);
                	   }  
                  
                   
                   if(row.getCell(53)!=null) {
                       String hcpidata7;
                       worldBankData=new WorldBankData();

                	  worldBankData.setCountry(country);
                      worldBankData.setCountrycode(countrycode);
                      worldBankData.setImfcountrycode(imfcountrycode);
                      worldBankData.setIndicatortype(indicatortype);
                      worldBankData.setSeriesname(seriesname);   
                      
                       worldBankData.setYear(listYear.get(6)); 
                        //hcpidata=row.getCell(56).toString();
                      	hcpidata7=row.getCell(53).toString();
                        worldBankData.setHcpidata(hcpidata7);  
                        worldBankRepo.insert(worldBankData);
                	   }  
                  
                   
                   if(row.getCell(54)!=null) {
                       String hcpidata8;
                       worldBankData=new WorldBankData();

                	  worldBankData.setCountry(country);
                      worldBankData.setCountrycode(countrycode);
                      worldBankData.setImfcountrycode(imfcountrycode);
                      worldBankData.setIndicatortype(indicatortype);
                      worldBankData.setSeriesname(seriesname);   
                      
                       worldBankData.setYear(listYear.get(7)); 
                        //hcpidata=row.getCell(56).toString();
                      	hcpidata8=row.getCell(54).toString();
                        worldBankData.setHcpidata(hcpidata8);  
                        worldBankRepo.insert(worldBankData);
                	   }  
                  
                   
                   
                   if(row.getCell(55)!=null) {
                       String hcpidata9;
                       worldBankData=new WorldBankData();

                	  worldBankData.setCountry(country);
                      worldBankData.setCountrycode(countrycode);
                      worldBankData.setImfcountrycode(imfcountrycode);
                      worldBankData.setIndicatortype(indicatortype);
                      worldBankData.setSeriesname(seriesname);   
                      
                       worldBankData.setYear(listYear.get(8)); 
                        //hcpidata=row.getCell(56).toString();
                      	hcpidata9=row.getCell(55).toString();
                        worldBankData.setHcpidata(hcpidata9);  
                        worldBankRepo.insert(worldBankData);
                	   }  
                  
                   
                   if(row.getCell(56)!=null) {
                       String hcpidata10;
                       worldBankData=new WorldBankData();

                	  worldBankData.setCountry(country);
                      worldBankData.setCountrycode(countrycode);
                      worldBankData.setImfcountrycode(imfcountrycode);
                      worldBankData.setIndicatortype(indicatortype);
                      worldBankData.setSeriesname(seriesname);   
                      
                       worldBankData.setYear(listYear.get(9)); 
                        //hcpidata=row.getCell(56).toString();
                      	hcpidata10=row.getCell(56).toString();
                        worldBankData.setHcpidata(hcpidata10);  
                        worldBankRepo.insert(worldBankData);
                	   }  
                                  
            }


            
                 
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);       
        }

        return "Saved all data";

    }
    
    
    
    
    @GetMapping("/getall/{country}/{year}")
    @ResponseBody
    public List<WorldBankData> getAllDataByNameAndAccount(@PathVariable String country, @PathVariable String year) {
        //public List<Employee> getAllDataByNameAndAccount() {
        return worldBankService.getAllByCountryAndYear(country,year);
        //return employeeRepo.findAll();
    }
    
    

}





