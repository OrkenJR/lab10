package kz.iitu.itse_1904.naga.service;

import kz.iitu.itse_1904.naga.database.MessageDto;

public interface KafkaService {

    void send(MessageDto messageDto);

    void consume(MessageDto messageDto);

}
