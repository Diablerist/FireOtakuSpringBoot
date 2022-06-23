package com.kitsuapi.firedev.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DataResponse {
    private String id;
    private String type;
    private Links links;
    private Attributes attributes;
    
}
