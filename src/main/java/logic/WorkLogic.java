package logic;

import java.util.ArrayList;
import java.util.List;

import dao.WorkDAO;
import model.Account;
import model.Work;

public class WorkLogic {
	
	WorkDAO dao = new WorkDAO();
	
	public List<Work> searchList(String search){
		// 引数の文字を含む作品を取得する
		return dao.searchList(search);
	}
	public List<Work> workList(){
		// 全作品を取得する
		return dao.workList();
	}
	public List<Work> myList(Account account){
		// ログインユーザーの作品を取得する
		return dao.myList(account.getAccountId());
	}
	public List<Work> myPost(Work work){
		// ユーザーの投稿を格納する
		boolean addPost = dao.myPost(work);
		// 登録されたか確認する
		if (addPost) {
			return dao.myList(work.getReviewerId());
		}
		return new ArrayList<Work>();
	}
	public boolean workDelete(Account a) {
		// ログインユーザーの作品を削除する
		return dao.workDelete(a);
	}
}
