package com.github.dimka9910.etlservices.elasticservice.app.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

@Slf4j
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.github.dimka9910.etlservices.elasticservice.service.elastic.repository")
public class ElasticConfig extends AbstractElasticsearchConfiguration {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration
                = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .withSocketTimeout(Duration.ofMinutes(10))
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Override
    public ElasticsearchCustomConversions elasticsearchCustomConversions() {
        return new ElasticsearchCustomConversions(Arrays.asList(DateToStringConverter.INSTANCE, StringToDateConverter.INSTANCE));
    }

    @WritingConverter
    enum DateToStringConverter implements Converter<Date, String> {
        INSTANCE;
        @Override
        public String convert(Date date) {
            return formatter.format(date);
        }
    }

    @ReadingConverter
    enum StringToDateConverter implements Converter<String, Date> {
        INSTANCE;
        @Override
        public Date convert(String s) {
            try {
                return formatter.parse(s);
            } catch (ParseException e) {
                return null;
            }
        }
    }
}
