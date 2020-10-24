package com.qf.account.entry;

import java.util.Objects;

public class Account {
    private int id;
    private String no;
    private String password;
    private int balance;

    public Account() {
    }

    public Account(String no, String password, int balance) {
        this.no = no;
        this.password = password;
        this.balance = balance;
    }

    public Account(int id, String no, String password, int balance) {
        this.id = id;
        this.no = no;
        this.password = password;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                balance == account.balance &&
                no.equals(account.no) &&
                password.equals(account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, no, password, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
