package com.qf.account.dao;

import com.qf.account.entry.Account;
import java.util.List;

public interface AccountDao {
    void insert(Account account);
    void delete(String no);
    void upDate(Account account);
    Account select(String no);
    List<Account> selectAll();
}
