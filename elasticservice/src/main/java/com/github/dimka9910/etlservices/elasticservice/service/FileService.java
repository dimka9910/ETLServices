package com.github.dimka9910.etlservices.elasticservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dimka9910.etlservices.elasticservice.dto.DialogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
public class FileService {

    ObjectMapper mapper = new ObjectMapper();

    public DialogDto openFile(String path) {
        try {
            return mapper.readValue(new File(path), DialogDto.class);
        } catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }


}
