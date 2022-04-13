package kz.iitu.itse_1904.naga.database;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MessageDto {

    private LocalDateTime sentDate;

    private String message;

}
