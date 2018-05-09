<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="guauguapp.Producto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>


<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://v40.pingendo.com/assets/4.0.0/default/theme.css" type="text/css"> </head>

<body>
  <nav class="navbar navbar-expand-md bg-primary navbar-dark">
    <div class="container">
      <a class="navbar-brand" href="#">
        <i class="fa d-inline fa-lg fa-cloud"></i>
        <b>GuauGuapp</b>
      </a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbar2SupportedContent" aria-controls="navbar2SupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse text-center justify-content-end" id="navbar2SupportedContent">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="#">
              <i class="fa d-inline fa-lg fa-envelope-o"></i> Contacts</a>
          </li>
        </ul>
        <a class="btn navbar-btn ml-2 text-white btn-secondary">
          <i class="fa d-inline fa-lg fa-user-circle-o"></i>Registrarse</a>
      </div>
    </div>
  </nav>
  
  <div class="p-5 bg-secondary">
    <div class="container">
      <div class="row">
      
     	<%
			if (request.getAttribute("txtresultado") != null) {
			ArrayList<Producto> itemsArray = (ArrayList<Producto>) request.getAttribute("txtresultado");
			
			 	for (int i=0; i < itemsArray.size()-4; i = i+3) {
			 		%>
			 			<div class="p-3 align-self-center col-md-4">
					          <div class="card">
					            <div class="card-block p-5">
					              <hr>
					              <p>Nombre : <% out.println(itemsArray.get(i).nombre); %></p>
					              <p>Descripcion : <% out.println(itemsArray.get(i).descripcion); %> </p>
					              <p>Precio :  <% out.println(itemsArray.get(i).precio); %></p>
					              <a href=" <% out.println(itemsArray.get(i).enlace); %>" class="btn btn-dark">Comprar</a>
					            </div>
					          </div>
					    </div>
					    <div class="p-3 align-self-center col-md-4">
					          <div class="card" >
					            <div class="card-block p-5">
					              <hr>
					              <p>Nombre : <% out.println(itemsArray.get(i+1).nombre); %></p>
					              <p>Descripcion : <% out.println(itemsArray.get(i+1).descripcion); %> </p>
					              <p>Precio :  <% out.println(itemsArray.get(i+1).precio); %></p>
					              <a href=" <% out.println(itemsArray.get(i+1).enlace); %>" class="btn btn-dark">Comprar</a>
					            </div>
					          </div>
					    </div>
					    <div class="p-3 align-self-center col-md-4">
					          <div class="card">
					            <div class="card-block p-5">
					              <hr>
					              <p>Nombre : <% out.println(itemsArray.get(i+2).nombre); %></p>
					              <p>Descripcion : <% out.println(itemsArray.get(i+2).descripcion); %> </p>
					              <p>Precio :  <% out.println(itemsArray.get(i+2).precio); %></p>
					              <a href=" <% out.println(itemsArray.get(i+2).enlace); %>" class="btn btn-dark">Comprar</a>
					            </div>
					          </div>
					    </div>
					<%
					 		}
						}
						else {
							out.println("no me han llegado datos de productos");
						}	
			  		%>
		</div>
	</div>
</div>
		
  		
  <div class="py-5 bg-dark text-white">
    <div class="container">
      <div class="row">
        <div class="col-md-9">
          <p class="lead">Regístrese para ver los mejores productos a mejor precio</p>
          <form class="form-inline">
            <div class="form-group">
              <input type="email" class="form-control" placeholder="Your e-mail here"> </div>
            <button type="submit" class="btn btn-primary ml-3">Suscribirse</button>
          </form>
        </div>
        <div class="col-4 col-md-1 align-self-center">
          <a href="https://www.facebook.com" target="_blank">
            <i class="fa fa-fw fa-facebook fa-3x text-white"></i>
          </a>
        </div>
        <div class="col-4 col-md-1 align-self-center">
          <a href="https://twitter.com" target="_blank">
            <i class="fa fa-fw fa-twitter fa-3x text-white"></i>
          </a>
        </div>
        <div class="col-4 col-md-1 align-self-center">
          <a href="https://www.instagram.com" target="_blank">
            <i class="fa fa-fw fa-instagram text-white fa-3x"></i>
          </a>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12 mt-3 text-center">
          <p>Ingeniería Sistemas de Información - Práctica&nbsp;</p>
        </div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>

</html>