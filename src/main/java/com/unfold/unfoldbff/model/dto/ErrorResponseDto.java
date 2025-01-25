package com.unfold.unfoldbff.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class ErrorResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3110805429618028703L;

    private String type;
    private String title;
    private Integer status;
    private String instance;
    private String detail;
}
