package com.hungerbet.billing.controllers.Requests;

import lombok.Data;

@Data
public class DebitingMoneyRequest {
    private String login;
    private double amount;
}
