package com.iesb.gab.raf.carona.api.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {

    private int status;
    private String error;
    private String message;
    private String path;
    private Object details;
    private String timestamp;
}
