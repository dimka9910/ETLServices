package com.github.dimka9910.etlservices.elasticservice.service;

import com.github.dimka9910.etlservices.elasticservice.dto.DialogDto;
import com.github.dimka9910.etlservices.elasticservice.mapper.MessageMapper;
import com.github.dimka9910.etlservices.elasticservice.service.elastic.entity.MessageIndex;
import com.github.dimka9910.etlservices.elasticservice.service.elastic.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ElasticService {

    private final ElasticsearchOperations elasticsearchTemplate;

    private final RestHighLevelClient restHighLevelClient;
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final FileService fileService;

    public void getFilesAndLoadInElastic(String path) {

        try {
            DialogDto dialogDto = fileService.openFile(path);

            for (var message: dialogDto.getData()) {
                MessageIndex messageIndex = messageMapper.dtoToHash(message, dialogDto.getMeta());
                messageRepository.save(messageIndex);
            }
        } catch (Exception e){
            log.error(e.getMessage(), e);
        }
    }


}
