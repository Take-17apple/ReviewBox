package logic;

import dao.AccountDAO;
import model.Account;

public class AccountLogic {
	
	AccountDAO dao = new AccountDAO();
	
	public boolean inputCheck(String name, String pass) {
		// 要件を満たした入力か確認する
		if (name == null || name.isBlank()) {
			return false;
		}
		if (pass == null || pass.isBlank()) {
			return false;
		}
		if (pass.length() < 4) {
			return false;
		}
		return true;
	}
	public boolean accountCheck(Account account) {
		// アカウント情報が登録されているか確認する
		return dao.accountCheck(account);
	}
	public boolean accountRegister(Account account) {
		// アカウント情報をデータベースに登録する
		return dao.accountRegister(account);
	}
	public boolean accountDelete(Account account) {
		// アカウント情報を削除する
		return dao.accountDelet(account);
	}
}
