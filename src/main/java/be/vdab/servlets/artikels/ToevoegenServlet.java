package be.vdab.servlets.artikels;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Artikel;
import be.vdab.entities.Artikelgroep;
import be.vdab.entities.FoodArtikel;
import be.vdab.entities.NonFoodArtikel;
import be.vdab.services.ArtikelService;
import be.vdab.services.ArtikelgroepService;

@WebServlet("/artikels/toevoegen.htm")
public class ToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/toevoegen.jsp";
	private static final String REDIRECT_URL = "%s/artikels/zoekenopnummer.htm?id=%d";
	private final transient ArtikelService artikelService = new ArtikelService();
	private final transient ArtikelgroepService artikelgroepService = new ArtikelgroepService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("artikelgroepen", artikelgroepService.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String naam = request.getParameter("naam");
		if (!Artikel.isNaamValid(naam)) {
			fouten.put("naam", "verplict");
		}
		BigDecimal aankoopprijs = null;
		try {
			aankoopprijs = new BigDecimal(request.getParameter("aankoopprijs"));
			if (!Artikel.isAankoopprijsValid(aankoopprijs)) {
				fouten.put("aankoopprijs", "tik een positief getal of 0");
			}
		} catch (NumberFormatException ex) {
			fouten.put("aankoopprijs", "tik een positief getal of 0");
		}
		BigDecimal verkoopprijs = null;
		try {
			verkoopprijs = new BigDecimal(request.getParameter("verkoopprijs"));
			if (!Artikel.isVerkoopprijsValid(verkoopprijs)) {
				fouten.put("verkoopprijs", "tik een positief getal of 0");
			}
		} catch (NumberFormatException ex) {
			fouten.put("verkoopprijs", "tik een positief getal of 0");
		}
		if (!Artikel.isPrijzenValid(aankoopprijs, verkoopprijs)) {
			fouten.put("prijzen",
					"Aankoopprijs MOET lager zijn dan verkoopprijs");
		}
		String groepid = request.getParameter("artikelgroepen");
		Artikelgroep artikelgroep = artikelgroepService.read(Long
				.parseLong(groepid));
		if (groepid == null) {
			fouten.put("artikelgroepen", "verplicht");
		}
		if (fouten.isEmpty()) {
			if (request.getParameter("foodOrNonFood").equals("Food")) {
				int houdbaarheid = Integer.parseInt(request
						.getParameter("houdbaarheid"));
				Artikel artikel = new FoodArtikel(naam, aankoopprijs,
						verkoopprijs, houdbaarheid, artikelgroep);
				artikel.setArtikelgroep(artikelgroepService.read(Long
						.parseLong(groepid)));
				artikelService.create(artikel);
				response.sendRedirect(response.encodeRedirectURL(String.format(
						REDIRECT_URL, request.getContextPath(), artikel.getId())));
			} else {
				int garantie = Integer.parseInt(request
						.getParameter("garantie"));
				Artikel artikel = new NonFoodArtikel(naam, aankoopprijs,
						verkoopprijs, garantie, artikelgroep);
				artikel.setArtikelgroep(artikelgroepService.read(Long
						.parseLong(groepid)));
				artikelService.create(artikel);
				response.sendRedirect(response.encodeRedirectURL(String.format(
						REDIRECT_URL, request.getContextPath(), artikel.getId())));
			}
		} else {
			request.setAttribute("fouten", fouten);
			request.setAttribute("artikelgroepen",
					artikelgroepService.findAll());
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
