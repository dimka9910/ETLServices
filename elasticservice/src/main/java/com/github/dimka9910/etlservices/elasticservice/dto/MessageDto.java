package com.github.dimka9910.etlservices.elasticservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.dimka9910.etlservices.elasticservice.mapper.UnixTimestampDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {

    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    Date date;
    @JsonProperty("from_id")
    Integer fromId;
    Integer id;
    String text;

}
