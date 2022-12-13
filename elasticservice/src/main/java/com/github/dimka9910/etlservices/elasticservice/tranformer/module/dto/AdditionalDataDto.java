package com.github.dimka9910.etlservices.elasticservice.tranformer.module.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdditionalDataDto {

    Integer vkId;
    String name;
    String surname;

    String city;

    Double latitude;
    Double longitude;

}
