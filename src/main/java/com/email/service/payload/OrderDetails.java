package com.email.service.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderDetails {

    private String custName;
    private String orderNo;
    private String orderStatus;
    
}
