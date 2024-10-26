package com.koizai.commonservice.email.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDto {
    private String sender;
    private String recipients;
    private String cc;
    private String bcc;
    private String subject;
    private String targetName;
    private String body;
    private boolean htmlFormat = true;
    private List<MultipartFile> attachments;


}
