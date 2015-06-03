<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='${empty artikel ? "Artikel zoeken" : artikel.naam}' />
</head>
<body>
	<v:menu />
	<h1>Kortingen</h1>
	<c:if test='${not empty artikels}'>
		<table>
			<thead>
				<tr>
					<th>Nummer</th>
					<th>Naam</th>
					<th>Aankoopprijs</th>
					<th>Verkoopprijs</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items='${artikels}' var='artikel'>
					<tr>
						<td>${artikel.id}</td>
						<c:url value='/artikels/kortingen.htm' var='detailURL'>
							<c:param name='id' value='${artikel.id}' />
						</c:url>
						<td><a href='${detailURL}'>${artikel.naam}</a> <c:if
								test='${not empty artikelKorting}'>

							</c:if></td>
						<td><fmt:formatNumber value='${artikel.aankoopprijs}'
								minFractionDigits='2' maxFractionDigits='2' /></td>
						<td><fmt:formatNumber value='${artikel.verkoopprijs}'
								minFractionDigits='2' maxFractionDigits='2' /></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test='${vanafRij != 0}'>
			<c:url value='' var='vorigePaginaURL'>
				<c:param name='naam' value='${param.naam}' />
				<c:param name='vanafRij' value='${vanafRij - aantalRijen}' />
			</c:url>
			<a href="<c:out value='${vorigePaginaURL}'/>" title='vorige pagina'
				class='pagineren'>&larr;</a>
		</c:if>
		<c:if test='${empty laatstePagina}'>
			<c:url value='' var='volgendePaginaURL'>
				<c:param name='naam' value='${param.naam}' />
				<c:param name='vanafRij' value='${vanafRij + aantalRijen}' />
			</c:url>
			<a href="<c:out value='${volgendePaginaURL}'/>"
				title='volgende pagina' class='pagineren'>&rarr;</a>
		</c:if>
	</c:if>
	<c:if test='${not empty artikelKorting}'>
	<h1>Kortingen voor ${artikelKorting.naam}</h1>
		<table>
			<thead>
				<tr>
					<th>Vanaf Aantal</th>
					<th>Kortingspercentage</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items='${artikelKorting.kortingen}' var='korting'>
					<tr>
						<td>${korting.vanafAantal}</td>
						<td><fmt:formatNumber value='${korting.kortingspercentage}'
								minFractionDigits='2' maxFractionDigits='2' />%</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>