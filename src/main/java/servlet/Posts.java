package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.WorkLogic;
import model.Account;
import model.Work;

/**
 * Servlet implementation class Posts
 */
public class Posts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Posts() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		
		List <Work> myPostList = new WorkLogic().myList(account);
		if (myPostList.isEmpty()) {
			request.setAttribute("errorMsg", "投稿されていません");
		} 
		request.setAttribute("myPostList", myPostList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/posts.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String creator = request.getParameter("creator");
		String genre = request.getParameter("genre");
		String price1 = request.getParameter("price");
		String plot = request.getParameter("plot");
		String rating1 = request.getParameter("rating");
		
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		int count = 0;
		StringBuilder sb = new StringBuilder();
		
		// genreに送られてきた値がデフォルトのままならnull
		if (genre.equals("選択してください")) {
			genre = null;
			sb.append("ジャンルが選択されていません<br>");
		} 
		
		// priceに数値が入力されているか確認
		int price = 0;
		if (price1 != null && price1.matches("[0-9０-９]+")) {
		        price = Integer.parseInt(price1);
		} else {
			count++;
			sb.append("数字が入力されていません<br>");
		}
		
		// ratingを点数に変換する
		int rating = 0;
		if (rating1 != null && rating1.matches("[532]")) {
			rating = Integer.parseInt(rating1);
		} else {
			count++;
			sb.append("評価が選択されていません<br>");
		}
		
		// 作品を登録する
		if (count == 0) {
			Work postWork = new Work(title, creator, genre, price, plot, rating, account.getAccountId());
			List <Work> myPostList= new WorkLogic().myPost(postWork);
			if (myPostList.isEmpty()) {
				sb.append("投稿できませんでした<br>");
			} 
			request.setAttribute("myPostList", myPostList);
		}
		
		if (!sb.isEmpty()) {
			request.setAttribute("errorMsg", sb.toString());
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/posts.jsp");
		dispatcher.forward(request, response);
	}
	

}
