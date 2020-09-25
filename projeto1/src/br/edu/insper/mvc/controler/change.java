package br.edu.insper.mvc.controler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.mvc.model.DAO;

/**
 * Servlet implementation class change
 */
@WebServlet("/change")
public class change extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public change() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        String data = request.getParameter("data");
        String id = request.getParameter("id");
        String category = request.getParameter("category");
        String userId = request.getParameter("userId");

        DAO dao;
        try {
            dao = new DAO();

            dao.change(data, id, category, userId);
            request.setAttribute("category", "NR");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Lista");
            dispatcher.forward(request, response);
            dao.close();
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        String id = request.getParameter("id");
        String data = request.getParameter("data");
        String category = request.getParameter("category");
        String userId = request.getParameter("userId");

        request.setAttribute("userId", userId);
        request.setAttribute("category", category);
        request.setAttribute("id", id);
        request.setAttribute("data", data);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/change.jsp");
        dispatcher.forward(request, response);
    }

}
