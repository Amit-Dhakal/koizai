package com.springrest.worldbank.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "worldbankdata")
public class WorldBankData {

    @Id
    private String id;
    private String countrycode;
    private String imfcountrycode;
    private String country;
    private String indicatortype;
    private String seriesname;
    private String year;

private String hcpidata;


    public String getHcpidata() {
	return hcpidata;
}
public void setHcpidata(String hcpidata) {
	this.hcpidata = hcpidata;
}
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCountrycode() {
        return countrycode;
    }
    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }
    public String getImfcountrycode() {
        return imfcountrycode;
    }
    public void setImfcountrycode(String imfcountrycode) {
        this.imfcountrycode = imfcountrycode;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getIndicatortype() {
        return indicatortype;
    }
    public void setIndicatortype(String indicatortype) {
        this.indicatortype = indicatortype;
    }
    public String getSeriesname() {
        return seriesname;
    }
    public void setSeriesname(String seriesname) {
        this.seriesname = seriesname;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }


}
