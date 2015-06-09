<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<html lang='nl'>
<head>
<v:head title='Artikellijst' />
</head>
<body>
	<v:menu />
	<h1>Artikellijst</h1>
	<c:if test="${not empty artikels}">
		<table>
			<thead>
				<tr>
					<th>Naam</th>
					<th>Artikelgroep</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items='${artikels}' var='artikel'>
					<tr>
						<td>${artikel.naam}</td>
						<td>${artikel.artikelgroep.naam}</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>