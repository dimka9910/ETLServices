package com.github.dimka9910.etlservices.elasticservice.tranformer.module.service;

import com.github.dimka9910.etlservices.elasticservice.tranformer.module.dto.AdditionalDataDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Data
@Slf4j
@Component
public class AdditionalDataService {

    @Value("${coreservice.add-data-path}")
    private String exelPath;

    @Value("${coreservice.global-data-dirrectory}")
    private String globalPath;


    Map<Integer, AdditionalDataDto> additionalDataDtoMap = new HashMap<>();

    @PostConstruct
    public void loadData() {
        try {
            File file = new File(globalPath + exelPath);   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file

            itr.next();

            while (itr.hasNext()) {
                AdditionalDataDto additionalDataDto = new AdditionalDataDto();

                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column

                Cell cell = cellIterator.next();
                try {
                    additionalDataDto.setVkId(Integer.parseInt(cell.getStringCellValue()));
                } catch (Exception ignored) {}
                try {
                    additionalDataDto.setVkId((int) Math.round(cell.getNumericCellValue()));
                } catch (Exception ignored) {}

                additionalDataDto.setName(cellIterator.next().getStringCellValue());
                additionalDataDto.setSurname(cellIterator.next().getStringCellValue());
                additionalDataDto.setCity(cellIterator.next().getStringCellValue());

                additionalDataDtoMap.put(additionalDataDto.getVkId(), additionalDataDto);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


}
