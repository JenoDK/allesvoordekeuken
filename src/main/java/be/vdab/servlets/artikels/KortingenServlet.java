package be.vdab.servlets.artikels;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Artikel;
import be.vdab.services.ArtikelService;

@WebServlet("/artikels/kortingen.htm")
public class KortingenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/korting.jsp";
	private final transient ArtikelService artikelService = new ArtikelService();
	private static final int AANTAL_RIJEN = 20;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null){
			long id = Long.parseLong(request.getParameter("id"));
			request.setAttribute("artikelKorting", artikelService.read(id));
			findAllInTable(request, response);
		}else {
			findAllInTable(request, response);
		}
	}

	private void findAllInTable(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		int vanafRij = request.getParameter("vanafRij") == null ? 0 : Integer
				.parseInt(request.getParameter("vanafRij"));
		request.setAttribute("vanafRij", vanafRij);
		request.setAttribute("aantalRijen", AANTAL_RIJEN);
		List<Artikel> artikels = artikelService.findAll(vanafRij,
				AANTAL_RIJEN + 1);
		if (artikels.size() <= AANTAL_RIJEN) {
			request.setAttribute("laatstePagina", true);
		} else {
			artikels.remove(AANTAL_RIJEN);
		}
		request.setAttribute("artikels", artikels);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
