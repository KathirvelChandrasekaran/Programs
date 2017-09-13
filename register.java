
import com.mysql.jdbc.Connection;
import com.sun.java.swing.plaf.windows.resources.windows;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String reg = request.getParameter("usr");
        String passwd = request.getParameter("passwd");
        String cpasswd = request.getParameter("cpasswd");
        MessageDigest alg = MessageDigest.getInstance("MD5");
        alg.reset();
        alg.update(passwd.getBytes());
        byte[] digest = alg.digest();
        StringBuffer hashedpasswd = new StringBuffer();
        String hx;
        for (int i = 0; i < digest.length; i++) {
            hx = Integer.toHexString(0xFF & digest[i]);
            if (hx.length() == 1) {
                hx = "0" + hx;
            }
            hashedpasswd.append(hx);
        }
        String passwd1 = hashedpasswd.toString();
        try {
            String query = "SELECT * FROM register";
            String sql = "INSERT INTO register (register_no,passwd) VALUES (?,?)";
            int updateQuery = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3308/project_db", "root", "");
            PreparedStatement ps = null;
            ResultSet rs = null;
            Statement st = null;

            st = con.createStatement();
            rs = st.executeQuery(query);
            int count = 0;
            while (rs.next()) {
                String r = rs.getString("register_no");
                String p = rs.getString("passwd");
                if (reg.equals(r)) {
                    out.println("<script>");
                    out.println("alert('Already registered');");
                    out.println("location='register.jsp'");
                    out.println("</script>");
                    count++;
                } else if (reg.equals(r) && !(passwd.equals(cpasswd))) {
                    out.println("<script>");
                    out.println("alert('Invalid Password');");
                    out.println("location='register.jsp'");
                    out.println("</script>");
                    break;
                } else {
                    count--;
                }
            }
//                    if(count>0){
//                    
//                    }
//
            if (count < 0) {
                ps = con.prepareStatement(sql);
                ps.setString(1, reg);
                ps.setString(2, passwd1);
                ps.executeUpdate();
                response.sendRedirect("login.jsp");
                if (passwd != cpasswd) {
                    response.sendRedirect("register.jsp");
                }
            }
        } catch (Exception e2) {
            System.out.println(e2);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
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