package co.quisk.ibex.model;

import java.util.ArrayList;
import java.util.List;

import com.mobibucks.apcommon.valueobjects.AccountType;

public class AccountTypeList extends ArrayList<AccountType>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AccountType> list = this;

	public List<AccountType> getList() {
		return list;
	}

	public void setList(List<AccountType> list) {
		this.list = list;
	}
	
	
}
