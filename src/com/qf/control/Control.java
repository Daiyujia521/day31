package com.qf.control;

import com.qf.account.service.impl.AccountServiceImpl;

public class Control {
    public static void main(String[] args) {
        AccountServiceImpl accountService = new AccountServiceImpl();
        accountService.transFor("10086","10010","sili",1000);
    }
}
