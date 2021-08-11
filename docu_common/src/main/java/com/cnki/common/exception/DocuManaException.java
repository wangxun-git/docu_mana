package com.cnki.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocuManaException extends RuntimeException{

    private Integer code;

    private String msg;

}
