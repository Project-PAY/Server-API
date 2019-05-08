package com.pay.web.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class OutcomeRequest {

    @NotNull
    String content;

    @NotNull
    Long price;

    @NotNull
    @Size(max = 16)
    String method;

}
