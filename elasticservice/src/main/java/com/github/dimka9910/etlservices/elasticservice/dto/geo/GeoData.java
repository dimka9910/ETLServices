package com.github.dimka9910.etlservices.elasticservice.dto.geo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoData {
    String type;
    List<FeaturesGeo> features;
}