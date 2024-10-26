package com.koizai.commonservice.email.service;


import com.koizai.commonservice.common.Constants;
import com.koizai.commonservice.common.Helper;
import com.koizai.commonservice.email.model.EmailDto;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmailService implements Constants {
    private final Environment environment;
    private final JavaMailSender emailSender;
    private final Configuration configuration;

    private String serverName;
    private String adminName = "Admin";
    private String adminAddress = "anton.nuansantio@koizai.com";
    private String defaultSender = "no-reply@koizai.com";
    private boolean testEmail = false;


    public EmailService(Environment environment, JavaMailSender emailSender, Configuration configuration) {
        this.environment = environment;
        this.emailSender = emailSender;
        this.configuration = configuration;
        serverName = environment.getProperty("server.name");
        adminName = StringUtils.isEmpty(environment.getProperty("email.admin.name")) ? adminName : environment.getProperty("email.admin.name");
        adminAddress = StringUtils.isEmpty(environment.getProperty("email.admin.address")) ? adminAddress : environment.getProperty("email.admin.address");
        defaultSender = StringUtils.isEmpty(environment.getProperty("email.sender")) ? defaultSender : environment.getProperty("email.sender");
        testEmail = StringUtils.isEmpty(environment.getProperty("email.test")) ? false : Boolean.parseBoolean(environment.getProperty("email.test"));
        if (testEmail) {
            StringBuilder sbRestartMsg =  new StringBuilder();
            sbRestartMsg.append(String.format("Hi <b>%s</b>,<br/><hr/><br/>", adminName));
            sbRestartMsg.append("If you are seeing this email that means the koizai-common-service is just restarted!<br/><br/>");
            sbRestartMsg.append("Sincerely,<br/>");
            sbRestartMsg.append(String.format("<b>%s</b>", serverName));
            sendTestNotification(sbRestartMsg.toString());
        }

    }

    public void sendTestNotification(String message){
        notifyToAdmin(adminAddress, message);
    }

    private void notifyToAdmin(String emailAddress, String message) {
        try {
            EmailDto reqDto = new EmailDto();
            reqDto.setSender(defaultSender);
            reqDto.setRecipients(emailAddress);
            reqDto.setCc("koizai.dev.access@gmail.com");
            reqDto.setSubject("KoiZai Server Notification");
            reqDto.setTargetName(adminName);
            reqDto.setBody(message);
            reqDto.setHtmlFormat(true);
            sendEmail(reqDto);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }


    }

    private String buildContent(String message){
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<body>");
        sb.append("<div style=\"border-radius:5px;background-color:rgba(0, 174, 243, 1);color:white;padding:5px\">");
        sb.append("<img src=\"https://koizai.com/img/koizai-logo-large.jpg\" alt=\"logo\" width=\"120px\" height=\"30px\">");
        sb.append("</div>");

        sb.append("<div>");
        sb.append(String.format("<p>%s</p><br/>", message));
        sb.append("</div>");
        sb.append("<div style=\"border-radius:5px;text-align:center;background-color:rgba(0, 174, 243, 1);color:white\">");
        sb.append("<p>© KoiZai Limited 2020. All Rights Reserved.&nbsp;&nbsp;<a href=\"https://www.facebook.com/KoiZai-Limited-116433510142196/?view_public_for=116433510142196\" target=\"_blank\">facebook</a> &nbsp;<a href=\"https://www.linkedin.com/company/koizai-limited\" target=\"_blank\">linkedin</a></p>");
        sb.append("</div>");

        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }




    private String getFilesDirectory() {
        String dirPath = APP_HOME + SLASH + WEB_APPS + SLASH + KOIZAI_FOLDER;
        File fileDir = new File(dirPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();

        }
        return dirPath;
    }




    public void sendEmail(EmailDto reqEmail) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(StringUtils.isEmpty(reqEmail.getSender())?defaultSender:reqEmail.getSender());
        helper.setTo(reqEmail.getRecipients());
        if(!StringUtils.isEmpty(reqEmail.getCc())){
            helper.setCc(reqEmail.getCc());
        }

        if(!StringUtils.isEmpty(reqEmail.getBcc())){
            helper.setBcc(reqEmail.getBcc());
        }

        helper.setSubject(reqEmail.getSubject());
        String targetName = StringUtils.isEmpty(reqEmail.getTargetName())?Helper.convertToName(reqEmail.getRecipients()): reqEmail.getTargetName();
        String body = "";
        if(reqEmail.isHtmlFormat()){
            body = buildContent(reqEmail.getBody());
        }else{
            body = reqEmail.getBody();
        }
        helper.setText(body, reqEmail.isHtmlFormat());

        List<File> sentFiles = null;
        if (Helper.isValid(reqEmail.getAttachments())) {
            sentFiles = new ArrayList<>();
            int len = reqEmail.getAttachments().size();
            for (int i = 0; i < len; i++) {
                MultipartFile m = reqEmail.getAttachments().get(i);
                String oriName = m.getOriginalFilename();

                String filePath = getFilesDirectory() + SLASH + oriName;
                File f = new File(filePath);
                m.transferTo(f);
                helper.addAttachment(f.getName(), f);
                sentFiles.add(f);
            }
        }
        emailSender.send(message);

        //remove attachment files after email sent
        if (Helper.isValid(sentFiles)) {
            int size = sentFiles.size();
            for (int i = 0; i < size; i++) {
                File f = sentFiles.get(i);
                f.delete();
            }
        }
    }
    
    
    public void sendEmailNew(EmailDto emailDto) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(StringUtils.isEmpty(emailDto.getSender())?defaultSender:emailDto.getSender());
        helper.setTo(emailDto.getRecipients().split(";"));
        if(!StringUtils.isEmpty(emailDto.getCc())){
            helper.setCc(emailDto.getCc());
        }

        if(!StringUtils.isEmpty(emailDto.getBcc())){
            helper.setBcc(emailDto.getBcc());
        }

        helper.setSubject(emailDto.getSubject());
        
        String emailContent = getEmailContent(emailDto.getBody(), emailDto.getSender());
        helper.setText(emailContent, true);
        
        List<File> sentFiles = new ArrayList<>();
        if (Helper.isValid(emailDto.getAttachments())) {
            int len = emailDto.getAttachments().size();
            for (int i = 0; i < len; i++) {
                MultipartFile m = emailDto.getAttachments().get(i);
                String oriName = m.getOriginalFilename();

                String filePath = getFilesDirectory() + SLASH + oriName;
                File f = new File(filePath);
                m.transferTo(f);
                helper.addAttachment(f.getName(), f);
                sentFiles.add(f);
            }
        }
        
        emailSender.send(mimeMessage);
        
        //remove attachment files after email sent
        if (Helper.isValid(sentFiles)) {
            int size = sentFiles.size();
            for (int i = 0; i < size; i++) {
                File f = sentFiles.get(i);
                f.delete();
            }
        }
    }

    String getEmailContent(String message, String sender) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("message", message);
        configuration.getTemplate("email-feedback.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    } 


}