package com.github.dimka9910.etlservices.elasticservice.geocashing.module.dto.geo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeaturesGeo {
    Geometry geometry;
}
