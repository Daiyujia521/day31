package com.qf.account.service.impl;

import com.qf.account.dao.impl.AccountDaoImpl;
import com.qf.account.entry.Account;
import com.qf.account.service.AccountService;
import com.qf.account.util.DBUtil;

public class AccountServiceImpl implements AccountService {
    @Override
    public void transFor(String fromNo, String toNO, String password, int money) {
        //创建AccountDaoImpl对象
        AccountDaoImpl accountDao = new AccountDaoImpl();
        try {
            //开启事务
            DBUtil.begin();
            //查询转账账户
            Account fromAccount = accountDao.select(fromNo);
            //判断转账账户是否存在
            if (fromAccount == null) {
                throw new RuntimeException("账户不存在");
            }
            //判断转装账户密码是否正确
            if(!password.equals(fromAccount.getPassword())) {
                throw new RuntimeException("账户密码错误");
            }
            //判断转账账户余额是否充足
            if (fromAccount.getBalance() < money) {
                throw new RuntimeException("账户余额不足");
            }
            //判断收款账户是否存在
            Account toAccount = accountDao.select(toNO);
            System.out.println(toAccount);
            if (toAccount == null) {
                throw new RuntimeException("要转入的账户不存在");
            }
            //转出转账账户
            fromAccount.setBalance(fromAccount.getBalance() - money);
            accountDao.upDate(fromAccount);
            System.out.println(fromAccount.getBalance());
            System.out.println("转出成功，正在转入对方账户........");
            //转入收款账户
            toAccount.setBalance(toAccount.getBalance() + money);
            accountDao.upDate(toAccount);
            System.out.println("对方账户转入成功，转账成功..........");
            //提交转账事务
            DBUtil.commit();
        } catch (Exception e) {
            //转账失败时回滚
            System.err.println("转账失败，正在回滚....");
            DBUtil.rollback();
        }
    }
}
