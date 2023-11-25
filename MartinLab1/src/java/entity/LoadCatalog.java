/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package entity;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jonah
 */
@WebServlet(name = "LoadCatalog", urlPatterns = {"/LoadCatalog"})
public class LoadCatalog extends HttpServlet {

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
            
        //determine if it's first load or not
        if (request.getAttribute("monitors") == null){
        
        //will be stored in session
        ArrayList<Monitor> monitors = new ArrayList();
        ArrayList<Order> orders = new ArrayList();
        
        String fileName = "/monitors.csv";
        ServletContext context = getServletContext();
        InputStream stream = context.getResourceAsStream(fileName);
        Scanner sc = new Scanner(stream);
        
        //loop through monitors CSV and build monitor objects (store in arrayList)
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            //arguements for constructor
            String[] tokens = line.split(",");
            int partNum = Integer.parseInt(tokens[0]);
            String description = tokens[1];
            String resolution = tokens[2];
            double size = Double.valueOf(tokens[3]);
            String refreshRate = tokens[4];
            //convert string value to bool
            boolean ips = false;
            if (tokens[5].toLowerCase().equals("yes")) {
                ips = true;
            }
            double price = Double.valueOf(tokens[6]);
            
            //build object
            Monitor m = new Monitor(partNum, description, resolution, size, refreshRate, ips, price);
            monitors.add(m);
            //add item to order with quantity of 0
            orders.add(new Order(m, 0));
            request.getSession().setAttribute("monitors", monitors);
            request.getSession().setAttribute("orders", orders);
        }
        
        // forward to showOrders.jsp
        String path = "/showOrders.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
        
        out.println("</body>");
        out.println("</html>");
    }}

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
