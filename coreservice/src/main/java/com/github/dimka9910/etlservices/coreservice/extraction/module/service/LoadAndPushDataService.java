package com.github.dimka9910.etlservices.coreservice.extraction.module.service;

import com.github.dimka9910.etlservices.coreservice.extraction.module.service.rabbit.CoreMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class LoadAndPushDataService {

    @Autowired
    FileService fileService;

    @Autowired
    CoreMqService coreMqService;

    @Value("${coreservice.folder-path}")
    private String folderPath;

    @Value("${coreservice.global-data-dirrectory}")
    private String globalPath;

    public void loadAndPushInRabbit(){
        List<String> list = fileService.getPathsFromFolder(globalPath + folderPath);

        for (var el: list) {
            coreMqService.sendMessage(el);
            log.info(el + " sent");
        }
    }


}
