<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cadastro Realizado</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
<title>Paises</title>
</head>
<body>
    <!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp"/>
	<div class="container">
		
		<h3 class="page-header">País Cadastrado ${paises.id} </h3>
			<div class="row">
				<div class="col-md-12">
					<p><strong>Nome</strong></p>
					<p>${paises.nome}</p>
				</div>
			</div>

			<div class="row">
				<div class="col-md-6">
					<p><strong>População</strong></p>
					<p>${paises.populacao}</p>
				</div>
			</div>

			<div class="row">
				<div class="col-md-6">
					<p><strong>Area</strong></p>
					<p>${paises.area}</p>
				</div>
			</div>
			<hr />
			<div id="actions" class="row justify-content-md-center">
				<div class="col-md-11">
					<a href="index.jsp">
					<button type="button" class="btn btn-info btn-block">Voltar</button>
					</a>
				</div>
			</div>
	</div>
</body>
</html>