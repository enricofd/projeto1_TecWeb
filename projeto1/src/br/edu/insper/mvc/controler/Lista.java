package br.edu.insper.mvc.controler;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.mvc.model.Arquivo;
import br.edu.insper.mvc.model.DAO;


/**
 * Servlet implementation class Lista
 */
@WebServlet("/Lista")
public class Lista extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Lista() {
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

        String category;
        String order;
        String search;

        category = request.getParameter("categorySent");
        order = request.getParameter("orderSent");
        search = request.getParameter("search");

        if (category == null) {
            category = "NR";
        }

        if (order == null) {
            order = "NR";
        }

        if (search == null) {
            search = "NR";
        }

        DAO dao;
        try {
            dao = new DAO();

            List<Arquivo> arquivos = dao.getLista(category, order, search);
            List<String> categories = dao.getCategories();
            request.setAttribute("categories", categories);
            request.setAttribute("arquivos", arquivos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Lista.jsp");
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

        String data = request.getParameter("data");
        String userId = request.getParameter("userId");
        String category = request.getParameter("category");

        Arquivo arquivo;
        arquivo = new Arquivo();
        arquivo.setData(data);
        arquivo.setCategory(category);
        arquivo.setUserId(Integer.parseInt(userId));

        DAO dao;
        try {
            dao = new DAO();

            dao.adiciona(arquivo);
            dao.close();
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        doGet(request, response);
    }

}
