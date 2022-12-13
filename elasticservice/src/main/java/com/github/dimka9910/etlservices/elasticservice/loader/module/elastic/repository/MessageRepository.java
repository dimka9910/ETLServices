package com.github.dimka9910.etlservices.elasticservice.loader.module.elastic.repository;


import com.github.dimka9910.etlservices.elasticservice.loader.module.elastic.entity.MessageIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MessageRepository extends ElasticsearchRepository<MessageIndex, String> {

}
