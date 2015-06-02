package be.vdab.servlets.artikels;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Artikel;
import be.vdab.services.ArtikelService;

@WebServlet("/artikels/zoekenopnaam.htm")
public class ZoekenOpNaam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/zoekenopnaam.jsp";
	private final transient ArtikelService artikelService = new ArtikelService();
	private static final int AANTAL_RIJEN = 20;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getQueryString() != null) {
			Map<String, String> fouten = new HashMap<>();
			String naam = request.getParameter("naam");
			if (!Artikel.isNaamValid(naam)) {
				fouten.put("naam", "Tik een deel van een naam in");
			}
			int vanafRij = request.getParameter("vanafRij") == null ? 0
					: Integer.parseInt(request.getParameter("vanafRij"));
			request.setAttribute("vanafRij", vanafRij);
			request.setAttribute("aantalRijen", AANTAL_RIJEN);
			List<Artikel> artikels = artikelService.findByNameLike(naam,
					vanafRij, AANTAL_RIJEN + 1);
			if (artikels.size() <= AANTAL_RIJEN) {
				request.setAttribute("laatstePagina", true);
			} else {
				artikels.remove(AANTAL_RIJEN);
			}
			request.setAttribute("artikels", artikels);

			request.setAttribute("fouten", fouten);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
