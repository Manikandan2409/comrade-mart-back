package com.demon.comrade_mart.service;
//package com.demon.comrade_mart.service;
//
//import com.demon.comrade_mart.entity.FeedBack;
//
//import com.demon.comrade_mart.repository.FeedBackRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//public class FeedBackService {
//    @Autowired
//    private FeedBackRepository feedBackRepo;
//
//    public void  addToFeedback(FeedBack feedBack){
//        feedBack.setViewed(false);
//        feedBackRepo.save(feedBack);
//    }
//    public Map<String,List<FeedBack>> getAllFeedbacks(){
//        List<FeedBack> feedBacks = feedBackRepo.findAll();
//        Map<String,List<FeedBack>> diffProducts = new HashMap<>();
//        List<FeedBack> viewed = feedBacks.stream()
//                        .filter(FeedBack::isViewed)
//                        .collect(Collectors.toList());
//
//        List<FeedBack> notviewed = feedBacks.stream()
//                        .filter(feedBack -> !feedBack.isViewed())
//                                .collect(Collectors.toList());
//
//        diffProducts.put("mailed",viewed);
//        diffProducts.put("unmailed",notviewed);
//
//        return  diffProducts;
//
//    }
//
//
//
//    @Autowired
//    private JavaMailSender mailSender;
//    public void sendMail(FeedBack feedBack) {
//        Optional<FeedBack> feed = feedBackRepo.findById(feedBack.getId());
//        if (feed.isPresent()) {
//            FeedBack existingFeedBack = feed.get();
//            System.out.println("Escisting ="+existingFeedBack+" reponse feed"+ feedBack.getResponse());
//
//            String from = "bmanikandan24092004@gmail.com";
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom(from);
//            message.setTo(existingFeedBack.getEmail());
//            message.setSubject("Response from Comrade Mart");
//            message.setSentDate(new Date());
//            message.setText(feedBack.getResponse());
//            System.out.println(message);
//
//            try {
//                mailSender.send(message);
//                System.out.println("Mail sent successfully");
//
//                existingFeedBack.setViewed(true);
//                existingFeedBack.setResponse(feedBack.getResponse());
//                feedBackRepo.save(existingFeedBack); // Save the updated feedback
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("Failed to send mail: " + e.getMessage());
//            }
//        } else {
//            System.out.println("Feedback not found");
//        }
//    }
//}
import com.demon.comrade_mart.entity.FeedBack;
import com.demon.comrade_mart.repository.FeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FeedBackService {
    @Autowired
    private FeedBackRepository feedBackRepo;

    @Autowired
    private JavaMailSender mailSender;

    public void addToFeedback(FeedBack feedBack) {
        feedBack.setViewed(false);
        feedBackRepo.save(feedBack);
    }

    public Map<String, List<FeedBack>> getAllFeedbacks() {
        List<FeedBack> feedBacks = feedBackRepo.findAll();
        Map<String, List<FeedBack>> diffProducts = new HashMap<>();
        List<FeedBack> viewed = feedBacks.stream()
                .filter(FeedBack::isViewed)
                .collect(Collectors.toList());

        List<FeedBack> notViewed = feedBacks.stream()
                .filter(feedBack -> !feedBack.isViewed())
                .collect(Collectors.toList());

        diffProducts.put("mailed", viewed);
        diffProducts.put("unmailed", notViewed);

        return diffProducts;
    }

    public void sendMail(FeedBack feedBack) {
        Optional<FeedBack> feed = feedBackRepo.findById(feedBack.getId());
        if (feed.isPresent()) {

            FeedBack existingFeedBack = feed.get();
            System.out.println("Existing = " + existingFeedBack + " response feed = " + feedBack.getResponse());

            String from = "bmanikandan24092004@gmail.com";
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(existingFeedBack.getEmail());
            message.setSubject("Response from Comrade Mart");
            message.setSentDate(new Date());
            message.setText(feedBack.getResponse());
           // System.out.println(message);

            mailSender.send(message);
            existingFeedBack.setViewed(true);
            existingFeedBack.setResponse(feedBack.getResponse());

            feedBackRepo.save(existingFeedBack); // Save the updated feedback

        } else {
            System.out.println("Feedback not found");
        }
    }
}
