package com.github.dimka9910.etlservices.elasticservice.tranformer.module.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.dimka9910.etlservices.elasticservice.tranformer.module.mapper.UnixTimestampDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
