<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Artikel toevoegen' />
</head>
<body>
	<v:menu />
	<h1>Artikel toevoegen</h1>
	<form method='post' id='toevoegform'>
		<label>Naam:<span>${fouten.naam}</span> <input name='naam'
			value='${param.naam}' autofocus required></label> <label>Aankoopprijs:<span>${fouten.aankoopprijs}</span>
			<input name='aankoopprijs' value='${param.aankoopprijs}' required
			type='number' min='0' step='0.01'></label> <label>Verkoopprijs:<span>${fouten.verkoopprijs}</span>
			<input name='verkoopprijs' value='${param.verkoopprijs}' required
			type='number' min='0' step='0.01'></label> <input type="radio"
			id="food" name="foodOrNonFood" value="Food" checked>Food <br>
		Houdbaarheid: <input name='houdbaarheid' id='houdbaarheid'> <input
			id="nonfood" type="radio" name="foodOrNonFood" value="NonFood">Non-Food
		Garantie: <input name='garantie' id='garantie'>
		<c:if test='${not empty fouten.prijzen}'>
		${fouten.prijzen}
	</c:if>
		<label>Artikelgroep: <span>${fouten.artikelgroepen}</span> <select
			name='artikelgroepen' size='${artikelgroepen.size()}' required>
				<c:forEach items='${artikelgroepen}' var='artikelgroep'>
					<option value='${artikelgroep.id}'
						${artikelgroep.id == param.artikelgroepen ? 'selected' : ''}>
						${artikelgroep.
						naam}</option>
				</c:forEach>
		</select>
		</label> <input type='submit' value='Toevoegen' id='toevoegknop'>
	</form>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
		document.getElementById('food').onclick = enableDisableInputs;
		document.getElementById('nonfood').onclick = enableDisableInputs;
		enableDisableInputs();
		function enableDisableInputs() {
			document.getElementById('houdbaarheid').disabled = !document
					.getElementById('food').checked;
			document.getElementById('garantie').disabled = !document
					.getElementById('nonfood').checked;
		}
	</script>
</body>
</html>