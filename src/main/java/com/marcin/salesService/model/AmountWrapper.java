package com.marcin.salesService.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmountWrapper {

    float amount;

    public AmountWrapper(float amount) {
        this.amount = amount;
    }
}
