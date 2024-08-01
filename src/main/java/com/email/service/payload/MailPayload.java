package com.email.service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class MailPayload {
    
    private String toEmail;
    private String subject;
    private OrderDetails orderDetails;

}
