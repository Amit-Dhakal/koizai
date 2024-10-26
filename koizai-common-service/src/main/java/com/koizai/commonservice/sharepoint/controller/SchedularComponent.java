package com.koizai.commonservice.sharepoint.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.koizai.commonservice.sharepoint.service.SharepointFileService;
import com.koizai.commonservice.sharepoint.util.ConstantUtil;

@Component
public class SchedularComponent {

    private static final Logger LOG = LoggerFactory.getLogger(SchedularComponent.class);

    @Autowired
    SharepointFileService sharepointFileService;

    @Scheduled(fixedRate = 1 * 30 * 1000)
    public void synchSharePoint() {
        final LocalDateTime start = LocalDateTime.now();
        LOG.info("download start -" + start);
        String downloadDir = ConstantUtil.getFilesDirectory();
        String siteUrl = (ConstantUtil.site + "/" + ConstantUtil.shared_folder).replaceAll("\\s", "%20");
        sharepointFileService.init();
        sharepointFileService.download(downloadDir, siteUrl);
        final LocalDateTime end = LocalDateTime.now();
        LOG.info("download end -" + end);

    }

}
