<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="scripts/main.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>Tervetuloa</title>
</head>
<body>
<!-- <span id="etunimi"></span><br> -->
<!-- <span id="sukunimi"></span><br> -->
<!-- <span id="sposti"></span><br> -->
<!-- <span id="puh"></span><br> -->
<!-- <span id="vuosi"></span><br> -->
<%@ page import="java.util.Calendar" %>
<%
	Calendar kalenteri = Calendar.getInstance(); 
	int kuluvaVuosi=kalenteri.get(Calendar.YEAR);
	String etunimi=request.getParameter("etunimi");
	String sukunimi=request.getParameter("sukunimi");
	String sposti=request.getParameter("sposti");
	String puh=request.getParameter("puh");
	int vuosi=Integer.parseInt(request.getParameter("vuosi"));
	if (kuluvaVuosi-vuosi<18){
		out.print("Alaik�inen");
	    String redirectURL = "kysy.jsp";
	    response.setHeader("Refresh", "5;url=kysy.jsp");	}
	else{
		out.print("Tervetuloa "+etunimi+"!<br><br>");
		out.print("Tietosi:<br>");
		out.print("Nimi: "+etunimi+" "+sukunimi+"<br>");
		out.print("S�hk�posti: "+sposti+"<br>");
		out.print("Puhelin: "+puh+"<br>");
		out.print("Ik�: "+ (kuluvaVuosi-vuosi));	
	}

%>
<!-- <script>
// $(document).ready(function(){
	
// 	var vuosi = getParam("vuosi");

// 	if (2021-vuosi<18){
// 		console.log("Vuosi "+vuosi);
// 		// Simulate an HTTP redirect:
// 		$("#etunimi").text("Alaik�inen");
// 		setTimeout(function(){ window.location.href= 'kysy.jsp';}, 5000);
// 	}else{
// 		$("#etunimi").text("Etunimi: " + getParam("etunimi"));
// 		$("#sukunimi").text("Sukunimi: " + requestURLParam("sukunimi"));
// 		$("#sposti").text("S�hk�posti: " + requestURLParam("sposti"));
// 		$("#puh").text("Ik�: " + requestURLParam("puh"));
// 		$("#vuosi").text("Syntym�vuosi: " + vuosi);
// 	}
	
// });
</script> -->
</body>
</html>