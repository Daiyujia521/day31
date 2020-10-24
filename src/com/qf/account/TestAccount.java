package com.qf.account;

import com.qf.account.dao.impl.AccountDaoImpl;
import com.qf.account.entry.Account;

import java.util.List;

public class TestAccount {
    public static void main(String[] args) {
        AccountDaoImpl accountDao = new AccountDaoImpl();
        Account account = new Account("10086","zhangsan",10000);
        Account account1 = new Account(4,"10011","sanzhang",10000);
        //插入数据
        accountDao.insert(account);
        //删除数据
        accountDao.delete("10086");
        //修改数据
        accountDao.upDate(account1);
        //查询某个账户信息
        Account a = accountDao.select("10086");
        System.out.println(a);
        //查询所有账户信息
        List<Account> list = accountDao.selectAll();
        System.out.println(list);
    }
}
