
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/GetServlet")
public class GetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetServlet() {
        super();
          }
/*
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  
        response.setContentType("text/html");
          
        PrintWriter out = response.getWriter();
          
        out.print("<html><body>");
        out.print("<h2>Welcome to GeeksForGeeks</h2>");
        out.print("</body></html>");
      
    }*/

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String HtmlResponse = "<html><h1>HelloTEST</h1></html>";
		PrintWriter writer = response.getWriter();
		writer.write(HtmlResponse);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
