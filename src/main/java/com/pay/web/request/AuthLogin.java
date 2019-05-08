package com.pay.web.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AuthLogin {

    @NotNull
    @Size(max = 32)
    String identify;

    @NotNull
    @Size(max = 64)
    String password;

}
