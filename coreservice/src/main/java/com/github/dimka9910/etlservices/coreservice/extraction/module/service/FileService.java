package com.github.dimka9910.etlservices.coreservice.extraction.module.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
public class FileService {

    ObjectMapper mapper = new ObjectMapper();

    public List<String> getPathsFromFolder(String path) {
        File folder = new File(path);
        List<String> list = new LinkedList();
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                list.add(fileEntry.getAbsoluteFile().getAbsolutePath());
                log.debug(fileEntry.getAbsoluteFile().toString());
            }
        }
        return list;
    }




}
