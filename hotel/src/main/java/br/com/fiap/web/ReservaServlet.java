package br.com.fiap.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.hotel.business.ReservaBO;
import br.com.fiap.hotel.dao.ReservaDAO;
import br.com.fiap.hotel.model.entities.Reserva;

@WebServlet(urlPatterns = "/reservar")
public class ReservaServlet extends HttpServlet {

	private static final long serialVersionUID = 4954763554276449879L;
    private SimpleDateFormat sdf = new SimpleDateFormat();
    
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	  throws ServletException, IOException {
		resp.setContentType("text/html");
		
		try {	
		long quarto = Long.parseLong(req.getParameter("quarto"));
		sdf.applyPattern("yyyy-MM-dd");
		Date checkin = sdf.parse(req.getParameter("checkin"));
		Date checkout = sdf.parse(req.getParameter("checkout"));
		
		Reserva r = new Reserva(quarto, checkin, checkout);
		ReservaBO bo = new ReservaBO();
		
		PrintWriter out = resp.getWriter();
		String texto="";
		out.println("<html></html>");
		
		if(bo.validarCheckout(r) && bo.validarCheckinCheckout(r)) {
			new ReservaDAO().adicionar(r);
			texto = "Reserva para o quarto "+ quarto +" com entrada no dia "+ sdf.format(checkin)+
					" e saída no dia "+sdf.format(checkout)+ " realizada com sucesso.";
		} else {
			texto = "Datas inválidas! Favor verificar se a data de checkout é maior que a data de checkin"
					+ " e a data atual";
		}
		
		out.println("<h1>Hotel Fiap</h1>");
		out.println("<h2>Cadastro de Reserva</h2>");
		out.println("<p> " +texto+ " </p>");
		
		out.println("</body></html>");
		out.flush();
		out.close();
		
		} catch(ParseException e) {
			e.printStackTrace();
		}
	}
}
