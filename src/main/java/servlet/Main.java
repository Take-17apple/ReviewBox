package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.WorkLogic;
import model.Work;

/**
 * Servlet implementation class Main
 */
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// データベースにある作品リストを全取得しリクエストスコープに保存
		List<Work> workList = new WorkLogic().workList();
		request.setAttribute("workList", workList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 送られてきたリクエストパラメーターを取得する(検索)
		request.setCharacterEncoding("UTF-8");
		String search = request.getParameter("search");
		List<Work> workList = new ArrayList<>();
		WorkLogic logic = new WorkLogic();
		
		if (search != null && !search.isBlank()) {
			// 商品を検索して取得
			workList = logic.searchList(search);
			if (workList.isEmpty()) {
				request.setAttribute("errorMsg", "検索対象がありません");
			}
		} else {
			// 商品検索されていない
			request.setAttribute("errorMsg", "検索文字が入力されていません");
			workList = logic.workList();
		}
		request.setAttribute("workList", workList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

}
