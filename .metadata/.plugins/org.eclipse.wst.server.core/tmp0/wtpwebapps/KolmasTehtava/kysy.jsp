<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="scripts/main.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>

body{
width:100%;
max-width:300px;
}
input[type=text], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

div {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<title>Kysely</title>
</head>
<body>
<form action="nayta.jsp" method="POST">
	Etunimi: <input type="text" name="etunimi"><br>
	Sukunimi: <input type="text" name="sukunimi"><br>
    S?hk?posti: <input type="text" name="sposti"><br>
	Puhelin: <input type="text" name="puh"><br>
	Syntym?vuosi: <input type="text" name="vuosi"><br>
	<input type="submit" value="L?het?">
</form>
</body>
</body>
</html>