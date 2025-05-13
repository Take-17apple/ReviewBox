package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.AccountLogic;
import logic.WorkLogic;
import model.Account;

/**
 * Servlet implementation class AccountDelete
 */
public class AccountDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/accountDelete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "";
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		if (account == null) {
			// アカウントがnullならログイン画面に遷移
			session.invalidate();
			url = "WEB-INF/jsp/login.jsp";
		}
		// ユーザー投稿情報を削除
		boolean isDelWork = new WorkLogic().workDelete(account);
		if (isDelWork) {
			// ユーザー情報を削除
			boolean isDelAccount = new AccountLogic().accountDelete(account);
			if (isDelAccount) {
				// アカウント削除＋商品情報削除 OK
				session.invalidate();
				request.setAttribute("successMsg", "アカウントを削除しました");
				url = "index.jsp";
			} else {
				// アカウント削除失敗
				request.setAttribute("errorMsg", "アカウント削除に失敗しました");
				url = "WEB-INF/jsp/login.jsp";
			}
		} else {
			// 商品情報削除失敗 or 未登録
			request.setAttribute("errorMsg", "商品は登録されていません");
			url = "WEB-INF/jsp/accountDelete.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
