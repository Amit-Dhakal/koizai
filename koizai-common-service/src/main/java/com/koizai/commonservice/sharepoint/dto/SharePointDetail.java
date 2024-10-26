package com.koizai.commonservice.sharepoint.dto;

import lombok.Data;

@Data
public class SharePointDetail {
    private String siteName="/sites/sharepointteam";
    private String downloadDirectory="D:\\result";
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getDownloadDirectory() {
		return downloadDirectory;
	}
	public void setDownloadDirectory(String downloadDirectory) {
		this.downloadDirectory = downloadDirectory;
	}
    
    
    
}