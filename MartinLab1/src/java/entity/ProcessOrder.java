package entity;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author jonah
 */
@WebServlet(name = "ProcessOrder", urlPatterns = {"/ProcessOrder"})
public class ProcessOrder extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Martin A1 - Shopping Cart</title>");            
            out.println("</head>");
            out.println("<body>");
            
            //get session level orders arrayList
            HttpSession session = request.getSession();
            ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("orders");
            
            ArrayList<Order> newOrders = new ArrayList();
            

            //loop through orders and get the ones with a quantity > 0
            //remove elements with quantity of 0
            int quantity = 0;
            for (int i = 0; i < orders.size(); i++) {
            
            String stringQty = String.valueOf(request.getParameter("quantity" + i));
            if (stringQty == null || stringQty.equals("")) {
            stringQty = "0";
            }
            int qty = Integer.parseInt(stringQty);
            
                if (qty > 0) {
                    orders.get(i).setQuantity(qty);
                    quantity += qty;
                    newOrders.add(orders.get(i));
                } 
            }

            //go to next page if items have been added to cart
            //otherwise stay on this page
            if (quantity != 0) {
                session.setAttribute("newOrders", newOrders);

            // forward to checkout.jsp
            String path = "/checkout.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
            } else {
            
            request.setAttribute("zeroInput", "something");
            String path = "/showOrders.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
            
                
            }

            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
