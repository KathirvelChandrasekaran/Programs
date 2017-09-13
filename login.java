
import java.security.*;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String usr = request.getParameter("usr");
        String passwd = request.getParameter("passwd");
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
                String s = rs.getString("passwd");
                if (usr.equals(r) && (passwd1.equals(s))) {
                    count++;
                    break;
                } else if (usr.equals(r) && (passwd1 != s)) {
                    count--;
                }

            }
            if (count > 0) {
                Cookie cook = new Cookie("register", usr);
                response.addCookie(cook);
                HttpSession session=request.getSession();
                session.setAttribute("usrsession",usr);
                request.setAttribute("usr", usr);
                request.getRequestDispatcher("userHome").forward(request, response);
            } else if (count < 0) {
                out.println("<script>");
                out.println("window.alert('Username or password is incorrect');");
                out.println("</script>");
                response.sendRedirect("login.jsp");
            } else {
                out.println("<script>");
                out.println("alert('Not Registered');");
                out.println("location='register.jsp'");
                out.println("</script>");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
