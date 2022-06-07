package com.amil.databaseproject.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {

    private String message;
    private String messageCode;
}
