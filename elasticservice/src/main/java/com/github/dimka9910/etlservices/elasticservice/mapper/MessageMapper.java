package com.github.dimka9910.etlservices.elasticservice.mapper;

import com.github.dimka9910.etlservices.elasticservice.dto.AdditionalDataDto;
import com.github.dimka9910.etlservices.elasticservice.dto.DialogMetaDto;
import com.github.dimka9910.etlservices.elasticservice.dto.MessageDto;
import com.github.dimka9910.etlservices.elasticservice.service.AdditionalDataService;
import com.github.dimka9910.etlservices.elasticservice.service.elastic.entity.MessageIndex;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class MessageMapper {

    private final AdditionalDataService additionalDataService;

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
            messageIndex.setLatitude(additionalDataDto.getLatitude());
            messageIndex.setLongitude(additionalDataDto.getLongitude());
        }

        return messageIndex;
    }


}
