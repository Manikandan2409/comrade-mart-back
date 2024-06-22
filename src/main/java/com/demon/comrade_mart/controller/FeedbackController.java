package com.demon.comrade_mart.controller;

import com.demon.comrade_mart.entity.FeedBack;
import com.demon.comrade_mart.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedBackService feedBackService;

    @PostMapping("/add-feedback")
    public ResponseEntity<Boolean> addFeedBack(@RequestBody FeedBack feedBack){
        feedBackService.addToFeedback(feedBack);
        return  ResponseEntity.ok(true);

    }

    @GetMapping("/get-all-feedbacks")
    public Map<String, List<FeedBack>> getFeedbacks(){
        return  feedBackService.getAllFeedbacks();
    }
    @PostMapping("/mail")
    public  ResponseEntity<String> sendResponseMail(@RequestBody  FeedBack feedBack){
        feedBackService.sendMail(feedBack);
        return  ResponseEntity.ok("Mail Sent Successful");
    }

    @Autowired
    private JavaMailSender mailer;
    @PostMapping("/send-mail")
    public  ResponseEntity<String> sendMail(){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("bmanikandan24092004@gmail.com");
        msg.setTo("bmanikandan24092004@gmail.com");
        msg.setText("Sample mail");
        mailer.send(msg);
       return ResponseEntity.ok("Mailed sent");
    }
}
