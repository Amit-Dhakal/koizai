package com.koizai.commonservice.sharepoint.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.koizai.commonservice.sharepoint.service.SharepointFileService;
import com.koizai.commonservice.sharepoint.util.ConstantUtil;
import com.koizai.commonservice.sharepoint.util.FolderList;
import com.koizai.commonservice.sharepoint.util.FolderList.Node;

@RestController
@RequestMapping("/sharepoint")
public class SharePointController {

    @Autowired
    SharepointFileService sharepointFileService;

    @GetMapping("/view")
    public ResponseEntity<?> listFilesAndFolder() throws IOException {
        String filePath = ConstantUtil.getFilesDirectory();
        Node node = FolderList.getNode(new File(filePath));
        return new ResponseEntity<>(node, HttpStatus.OK);

    }

    
    @GetMapping("/download")
    public ResponseEntity<?> downloadFile(@RequestParam String fileName, @RequestParam String relativeUrl, HttpServletResponse response) throws IOException {
        sharepointFileService.init();
        //relativeUrlString url = "Shared Documents";
        InputStream is = sharepointFileService.downloadFile(relativeUrl, fileName.replaceAll("\\s", "%20"));
        IOUtils.copy(is, response.getOutputStream());

        response.setHeader(HttpHeaders.CONTENT_TYPE, getContentTypeForAttachment(fileName));
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        response.flushBuffer();
        return ResponseEntity.ok().build();
    }

    public String getContentTypeForAttachment(String fileName) {
        String fileExtension = com.google.common.io.Files.getFileExtension(fileName);
        if (fileExtension.equals("pdf"))
            return "application/pdf";
        else if (fileExtension.equals("xlsx"))
            return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        else if (fileExtension.equals("doc"))
            return "application/msword";
        else if (fileExtension.equals("jpeg"))
            return "image/jpeg";
        return "application/octet-stream";

    }

}
