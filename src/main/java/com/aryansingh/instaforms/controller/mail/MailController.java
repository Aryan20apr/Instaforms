package com.aryansingh.instaforms.controller.mail;

import com.aryansingh.instaforms.models.dtos.mail.SimpleMailDTO;
import com.aryansingh.instaforms.utils.AppConstants;
import com.aryansingh.instaforms.utils.api.ApiResponse;
import com.aryansingh.instaforms.utils.email.AccountVerificationEmailContext;
import com.aryansingh.instaforms.utils.email.MailService;
import com.aryansingh.instaforms.utils.exceptions.ApiException;
import com.aryansingh.instaforms.utils.rabbitmq.EmailConsumer;
import com.aryansingh.instaforms.utils.rabbitmq.EmailProducer;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/instaform/api/v1/mail")
@AllArgsConstructor
public class MailController {


//    private final MailService mailService;

   private final EmailProducer emailProducer;

//    @PostMapping("/simple")
//    public ResponseEntity<ApiResponse<String>> sendMail(@RequestBody SimpleMailDTO simpleMailDTO){
//
//
//        mailService.sendEmail(simpleMailDTO.getTo(),simpleMailDTO.getSubject(), simpleMailDTO.getMessage());
//
//        return ResponseEntity.ok(new ApiResponse<>(AppConstants.SUCCESS_CODE,"Email sent successfully",AppConstants.SUCCESS_MESSAGE));
//    }
//
//    @PostMapping("/attach")
//    public ResponseEntity<ApiResponse<String>> sendEmailWithAttachment(@RequestPart SimpleMailDTO simpleMailDTO, @RequestPart MultipartFile file){
//        try {
//            mailService.sendEmailWithFile(simpleMailDTO.getTo(),simpleMailDTO.getSubject(), simpleMailDTO.getMessage(),file.getInputStream());
//        } catch (IOException e) {
//           throw new ApiException("Error sending email");
//        }
//        return ResponseEntity.ok(new ApiResponse<>(AppConstants.SUCCESS_CODE,"Email sent successfully",AppConstants.SUCCESS_MESSAGE));
//    }

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<String>> sendEmail(@RequestBody SimpleMailDTO simpleMailDTO){


        emailProducer.sendToQueue(simpleMailDTO);

        return ResponseEntity.ok(new ApiResponse<>(AppConstants.SUCCESS_CODE,"Email sent successfully",AppConstants.SUCCESS_MESSAGE));
    }


}
