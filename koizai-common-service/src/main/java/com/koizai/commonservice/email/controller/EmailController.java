package com.koizai.commonservice.email.controller;


import com.koizai.commonservice.common.BaseController;
import com.koizai.commonservice.common.BaseMessageDto;
import com.koizai.commonservice.common.Constants;
import com.koizai.commonservice.common.KoizaiExceptionHandler;
import com.koizai.commonservice.email.model.EmailDto;
import com.koizai.commonservice.email.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@Api(value = "End point for email service", tags = {"Email"})
@RequestMapping("/email")
public class EmailController extends BaseController implements Constants {
    private final EmailService emailService;

    @PostMapping("/send-test")
    public ResponseEntity<BaseMessageDto> testNotificationEmail(@RequestParam(required = false, defaultValue = "Test email notification succeed.") String message) {
        BaseMessageDto dto = new BaseMessageDto();
        try {
            emailService.sendTestNotification(message);
        } catch (Exception ex) {
            KoizaiExceptionHandler.handleException(dto, ex);
        }
        return doResponse(dto);
    }



    @PostMapping(value="/send")
    @ResponseBody
    @ApiOperation(value = "send email")
    public ResponseEntity<BaseMessageDto> sendEmail(@ModelAttribute("emailDto") EmailDto emailDto) {
        BaseMessageDto dto = new BaseMessageDto();
        try {
            emailService.sendEmailNew(emailDto);
        } catch (Exception ex) {
            KoizaiExceptionHandler.handleException(dto, ex);
        }
        return doResponse(dto);
    }


}
