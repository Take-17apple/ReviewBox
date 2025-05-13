package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.AccountLogic;
import logic.WorkLogic;
import model.Account;
import model.Work;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
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
				// ログイン成功。セッションスコープに保存して、メイン画面へ遷移させ、メッセージを表示させる
				HttpSession session = request.getSession();
				session.setAttribute("account", account);
				request.setAttribute("mainMsg", "ログインしました");
				// データベースにある作品リストを前取得しリクエストスコープに保存
				List<Work> workList = new WorkLogic().workList();
				if (workList.isEmpty()) {
					request.setAttribute("errorMsg", "現在投稿している作品はありません");
				}
				request.setAttribute("workList", workList);
				url = "WEB-INF/jsp/main.jsp";
			} else {
				// ログインに失敗したのでもう一度ログイン画面へ遷移させ、メッセージを表示させる
				request.setAttribute("loginMsg", "登録されていません。登録を行なってください");
				url = "WEB-INF/jsp/login.jsp";
			}
		} else {
			// 入力値が異常
			request.setAttribute("loginMsg", "入力された値が異常です");
			url = "WEB-INF/jsp/login.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
