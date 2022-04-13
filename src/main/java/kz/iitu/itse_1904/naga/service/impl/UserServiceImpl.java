package kz.iitu.itse_1904.naga.service.impl;

import kz.iitu.itse_1904.naga.database.User;
import kz.iitu.itse_1904.naga.repository.UserRepository;
import kz.iitu.itse_1904.naga.service.UserService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@NoArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Value("${email.sender}")
    private String SENDER_EMAIL;

    @Value("${email.sender.password}")
    private String SENDER_EMAIL_PASSWORD;

    @PostConstruct
    public void init(){
        log.info("{} was created with params: Sender - {}. Password - {}", this, SENDER_EMAIL, SENDER_EMAIL_PASSWORD);
    }

    @PreDestroy
    public void destroy(){
        log.info("{} is being destroyed with params: Sender - {}. Password - {}", this, SENDER_EMAIL, SENDER_EMAIL_PASSWORD);
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    @Cacheable("users")
    @Transactional( propagation = Propagation.SUPPORTS,readOnly = true )
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional( propagation = Propagation.REQUIRES_NEW,readOnly = true )
    public User findByUsernameAndPassword(String username, String password){
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    @Transactional( propagation = Propagation.SUPPORTS,readOnly = true )
    public Page<User> findAllOrderById(Pageable pageable) {
        return userRepository.findAllByOrderById(pageable);
    }

    @Transactional( propagation = Propagation.SUPPORTS,readOnly = true )
    public Page<User> findAllByName(String firstName, String lastName, Pageable pageable
    ) {
        return userRepository.findAllByFirstNameAndLastNameOrderByIdAsc(firstName, lastName, pageable);
    }

    @Cacheable("users")
    @Transactional( propagation = Propagation.REQUIRES_NEW,readOnly = true )
    public User findById(Long id){
        return userRepository.findUserById(id);
    }

//    @Scheduled(cron = "${cron.everyNewYear}")// Sends every 1st January
    public void sendNotification(Pageable pageable){
        for(User user:findAll( pageable)){
            sendMail("Happy New Year !", user);
        }
    }

    public void sendMail(String messageText, User user) {

        String to = user.getEmail();
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {


                return new PasswordAuthentication(SENDER_EMAIL, SENDER_EMAIL_PASSWORD);

            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Subject");
            message.setText(messageText);
            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        session.setDebug(false);
    }





}
