package com.github.dimka9910.etlservices.elasticservice.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dimka9910.etlservices.elasticservice.dto.AdditionalDataDto;
import com.github.dimka9910.etlservices.elasticservice.dto.DialogMetaDto;
import com.github.dimka9910.etlservices.elasticservice.dto.MessageDto;
import com.github.dimka9910.etlservices.elasticservice.dto.geo.GeoJson;
import com.github.dimka9910.etlservices.elasticservice.service.AdditionalDataService;
import com.github.dimka9910.etlservices.elasticservice.service.CoordinatesService;
import com.github.dimka9910.etlservices.elasticservice.service.elastic.entity.MessageIndex;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageMapper {

    private final AdditionalDataService additionalDataService;
    private final CoordinatesService coordinatesService;
    ObjectMapper objectMapper = new ObjectMapper();

    public MessageIndex dtoToHash(MessageDto messageDto, DialogMetaDto dialogMetaDto){
        MessageIndex messageIndex = new MessageIndex();
        messageIndex.setCompositeId(dialogMetaDto.getPeer(), messageDto.getId());
        messageIndex.setDialogId(dialogMetaDto.getPeer());
        messageIndex.setMessageId(messageDto.getId());
        messageIndex.setText(messageDto.getText());
        messageIndex.setFromId(messageDto.getFromId());
        messageIndex.setTime(new Date(messageDto.getDate().getTime()));

        AdditionalDataDto additionalDataDto = additionalDataService.getAdditionalDataDtoMap().get(messageDto.getFromId());
        if (additionalDataDto != null) {
            messageIndex.setName(additionalDataDto.getName());
            messageIndex.setSurname(additionalDataDto.getSurname());
            messageIndex.setCity(additionalDataDto.getCity());
            messageIndex.setCityLocation(coordinatesService.getCoordinates(additionalDataDto.getCity()));
        }

        return messageIndex;
    }


}
