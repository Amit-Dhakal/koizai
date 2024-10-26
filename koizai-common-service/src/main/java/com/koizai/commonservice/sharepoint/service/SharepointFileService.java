package com.koizai.commonservice.sharepoint.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface SharepointFileService {

    public void download(String downloadFileDir, String siteFolderUrl);

    List<String> getListOfFolders(String siteURL, String siteFolderUrl) throws IOException;

    public void init();

    public InputStream downloadFile(String relativeUrl, String siteFolderUrl);

}
