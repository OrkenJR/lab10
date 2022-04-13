package kz.iitu.itse_1904.naga.service.impl;

import kz.iitu.itse_1904.naga.database.MessageDto;
import kz.iitu.itse_1904.naga.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    @Autowired
    public KafkaServiceImpl(KafkaTemplate<String, MessageDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(MessageDto messageDto) {
        kafkaTemplate.send("topic1", MessageDto.builder().sentDate(LocalDateTime.now()).message("Hello world !!!").build());
    }

    @Override
    @KafkaListener(topics = "topic1")
    public void consume(MessageDto messageDto) {
        log.info("Received message from {}, message text: {}", messageDto.getSentDate().toString(), messageDto.getMessage());
    }
}
