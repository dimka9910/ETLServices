package com.github.dimka9910.etlservices.elasticservice.service.elastic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "message")
public class MessageIndex {


    @Id
    public String id;
    Integer dialogId;
    Integer messageId;
    Integer fromId;
    Date time;
    String text;

    String city;
    String name;
    String surname;

    Double latitude;
    Double longitude;


    public void setCompositeId(Integer dialogId, Integer messageId) {
        this.id = dialogId + "_" + messageId;
    }
}
