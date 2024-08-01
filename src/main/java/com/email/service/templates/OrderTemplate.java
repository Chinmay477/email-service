package com.email.service.templates;

import org.springframework.stereotype.Component;

import com.email.service.constants.OrderConstants;

@Component
public class OrderTemplate {

    public String createSuccessBody(String name, String orderNumber){

        String trackingUrl = String.format("%s{%s}", OrderConstants.SUCCESS_TRACKING, orderNumber);

        String body = "<html><body>" +
                        "<p>Hi " + name + ",</p>" +
                        "<p>Your order no: " + orderNumber + " has been successfully placed.</p>" +
                        "<p>You can track your order at <a href='" + trackingUrl + "'>" + trackingUrl + "</a></p>" +
                        "<br>" +
                        "<p>Best regards,<br>IBM</p>" +
                        "<img src='cid:logo' alt='Company Logo' style='width: 100px; height: auto;'/>" +
                    "</body></html>";

        return body;
    }


    public String createFailureBody(String name, String orderNumber){

        String body = "<html><body>" +
                "<p>Hi " + name + ",</p>" +
                "<p>We regret to inform you that your order no: " + orderNumber + " could not be processed due to a failure.</p>" +
                "<p>Please contact our support team for further assistance or try placing your order again.</p>" +
                "<p>If you need immediate help, you can reach out to us at <a href='mailto:support@ibm.com'>support@example.com</a> or call us at (123) 456-7890.</p>" +
                "<br>" +
                "<p>Best regards,<br>Your IBM</p>" +
                "<img src='cid:logo' alt='Company Logo' style='width: 100px; height: auto;'/>" +
                "</body></html>";

        return body;
    }

}
