package com.github.dimka9910.etlservices.elasticservice.service.elastic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.lucene.spatial3d.geom.GeoShape;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;
import java.sql.Timestamp;
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

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    Date time;

    String text;

    String city;
    String name;
    String surname;

    @GeoPointField
    GeoPoint cityLocation;


    public void setCompositeId(Integer dialogId, Integer messageId) {
        this.id = dialogId + "_" + messageId;
    }
}
