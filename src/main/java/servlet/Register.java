package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.AccountLogic;
import model.Account;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "";
		// 送られてきたリクエストパラメーターを取得する
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		// 入力値が妥当か検査する
		AccountLogic logic = new AccountLogic();
		boolean isCheck = logic.inputCheck(name, pass);
		if (isCheck) {
			// 入力値が正常なので、データベースに登録されているか確認する
			Account account = new Account(name, pass);
			boolean isSelect = logic.accountCheck(account);
			if (isSelect) {
				// データベースに登録されているのでログイン画面へ遷移させ、メッセージを表示させる
				// データベース接続時にIDを取得しているので参照を破棄する
				account = null;
				request.setAttribute("errorMsg", "登録されています。ログインしてください");
				url = "WEB-INF/jsp/login.jsp";
			} else {
				// データベースに登録されていなかったので登録処理をする
				boolean isRegister = logic.accountRegister(account);
				if (isRegister) {
					// セッションスコープに保存して、メイン画面へ遷移させ、メッセージを表示させる
					HttpSession session = request.getSession();
					session.setAttribute("account", account);
					request.setAttribute("successMsg", "登録が完了しました");
					url = "WEB-INF/jsp/main.jsp";
				} else {
					// 登録が失敗したのでもう一度登録画面へ遷移させ、メッセージを表示させる
					request.setAttribute("errorMsg", "登録できません<br>再度登録してください");
					url = "WEB-INF/jsp/register.jsp";
				}
			}
		} else {
			// 入力値が異常
			request.setAttribute("errorMsg", "入力された値が異常です");
			url = "WEB-INF/jsp/register.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
