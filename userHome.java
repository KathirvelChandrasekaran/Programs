
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author kathirvel
 */
public class userHome extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String usr = request.getParameter("usr");
            String query = "select * from student_details";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3308/project_db", "root", "");
            PreparedStatement ps = null;
            ResultSet rs = null;
            Statement st = null;
            st = con.createStatement();
            rs = st.executeQuery(query);
            String str = (String) request.getAttribute("usr");
            int count = 0;
            HttpSession session=request.getSession();
            session.getAttribute("usrsession");
            while (rs.next()) {
                String r = rs.getString("register_no");
                if (str.equals(r)) {
                    String name1 = rs.getString("name");
                    String reg = rs.getString("register_no");
                    String dob = rs.getString("dob");
                    String gender = rs.getString("gender");
                    String dept = rs.getString("department");
                    String branch = rs.getString("branch");
                    String course = rs.getString("course");
                    String sem = rs.getString("sem");
                    String acdm_from = rs.getString("acdm_yr_from");
                    String acdm_to = rs.getString("acdm_yr_to");
                    request.setAttribute("name", name1);
                    request.setAttribute("reg", reg);
                    request.setAttribute("dob", dob);
                    request.setAttribute("gender", gender);
                    request.setAttribute("dept", dept);
                    request.setAttribute("branch", branch);
                    request.setAttribute("course", course);
                    request.setAttribute("sem", sem);
                    request.setAttribute("from", acdm_from);
                    request.setAttribute("to", acdm_to);
                    request.getRequestDispatcher("userHome.jsp").forward(request, response);
                    count++;
                }
            }
            
        } catch (Exception e) {

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
