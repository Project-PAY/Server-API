package com.pay.web.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class IncomeRequest {

    @NotNull
    String content;

    @NotNull
    Long price;

}
