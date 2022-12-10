package com.github.dimka9910.etlservices.coreservice.interaction.module;

import com.github.dimka9910.etlservices.coreservice.extraction.module.service.LoadAndPushDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rest/app")
@RequiredArgsConstructor
public class AppRest {

    @Autowired
    LoadAndPushDataService loadAndPushDataService;

    @GetMapping (value = "/loaddata", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public String readAndWriteFiles() {
        loadAndPushDataService.loadAndPushInRabbit();
        return "success";
    }


}
