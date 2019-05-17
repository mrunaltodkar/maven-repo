package com.capgemini.bankapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.*;
import org.springframework.dao.*;
import org.springframework.jdbc.core.JdbcTemplate;
import com.capgemini.bankapp.client.BankAccount;
import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.exception.AccountNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class BankAccountDaoImpl implements BankAccountDao {
	
	JdbcTemplate jdbcTemplate;
	public BankAccountDaoImpl(JdbcTemplate jdbcTemplate){
		 this.jdbcTemplate = jdbcTemplate; 
	}


	@Override
	public double getBalance(long accountId) throws AccountNotFoundException {
		String query = "SELECT account_balance FROM bankaccounts WHERE account_id=" + accountId;
		try{
			double balance = jdbcTemplate.queryForObject(query, Double.class);
			return balance;
		}catch(Exception e){
			throw new AccountNotFoundException("Bank account does not exist...");
		}
		
	}

	@Override
	public void updateBalance(long accountId, double newBalance) {
		String query = "UPDATE bankaccounts SET account_balance='"+newBalance+"' WHERE account_id='"+accountId+"' ";
		jdbcTemplate.update(query);
		
	}

	@Override
	public boolean deleteBankAccount(long accountId) {
		String query = "DELETE FROM bankaccounts WHERE account_id=" + accountId;
		int result=jdbcTemplate.update(query);
		if(result>0){
			return true;
		}else{
			return false;
		}
	}


	@Override
	public boolean addNewBankAccount(BankAccount account) {

		String query = "INSERT INTO bankaccounts (customer_name,account_type,account_balance) VALUES ('"+account.getAccountHolderName()+"','"+account.getAccountType()+"','"+account.getAccountBalance()+"')";
		int result=jdbcTemplate.update(query);
		if(result>0){
			return true;
		}else{
			return false;
		}
	}


	@Override
	public List<BankAccount> findAllBankAccountsDetails() {
		return jdbcTemplate.query("SELECT * FROM bankaccounts", (rs, rowNum) -> {return getBankAccount(rs);});
		
	}

	@Override
	public BankAccount searchAccountDetails(long accountId) {
		BankAccount account = null;
		try{
			account = jdbcTemplate.queryForObject("SELECT * FROM bankaccounts WHERE account_id=" + accountId, (rs, rowNum) -> {return getBankAccount(rs);});
		}catch(EmptyResultDataAccessException e){
			e.printStackTrace();
		}		
		return account;
	}

	@Override
	public boolean updateBankAccountDetails(long accountId, String accountHolderName, String accountType) throws AccountNotFoundException {

		String query = "UPDATE bankaccounts SET customer_name='"+accountHolderName+"',account_type='"+accountType+"' WHERE account_id='"+accountId+"' ";
		int result=jdbcTemplate.update(query);
		if(result>0){
			return true;
		}else{
			throw new AccountNotFoundException("Bank Account does not exist...");
		}
	}

	public BankAccount getBankAccount(ResultSet rs) throws SQLException{
		BankAccount account = new BankAccount();
		try{
			account.setAccountId(rs.getLong(1));
			account.setAccountHolderName(rs.getString(2));
			account.setAccountType(rs.getString(3));
			account.setAccountBalance(rs.getDouble(4));
	
		}catch(SQLException e){e.printStackTrace();}
		
		return account;	

	}
}
