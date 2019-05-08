package com.pay.web.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserRequest {

    @NotNull
    @Size(max = 32)
    String identify;

    @NotNull
    @Size(max = 64)
    String password;

    @NotNull @Size(max = 32) String name;

    @Pattern(regexp = "^[0-3][0-9]$")
    String cycleIncome = "00";

    Long fixedIncome = 0L;

    Long currentMoney = 0L;

}
