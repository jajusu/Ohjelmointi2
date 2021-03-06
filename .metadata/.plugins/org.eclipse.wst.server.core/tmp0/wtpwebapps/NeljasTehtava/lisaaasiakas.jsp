<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="scripts/main.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/tyylit.css">
<title>Lis?? uusi asiakas</title>
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

.error {
	color: red;
}
</style>
</head>

<body>

<a href="listaaasiakkaat.jsp">Takaisin</a><br><br>

<form id="tiedot">
	Etunimi: <input type="text" id="etunimi" name="etunimi"><br><br>
	Sukunimi: <input type="text" id="sukunimi"name="sukunimi"><br><br>
    Puhelin: <input type="text" id="puhelin"name="puhelin"><br><br>
	S?hk?posti: <input type="text" id="sposti" name="sposti"><br><br>
	<input type="submit" id="tallenna" value="Lis??"><br>
</form>
<span id="ilmo"></span>
</body>
<script>
$(document).ready(function(){
	$("#takaisin").click(function(){
		document.location="listaaasiakkaat.jsp";
	});
	$("#tiedot").validate({						
		rules: {
			etunimi:  {
				required: true,
				minlength: 2				
			},	
			sukunimi:  {
				required: true,
				minlength: 2				
			},
			puhelin:  {
				required: true,
				minlength: 7
			},	
			sposti:  {
				required: true,
				minlength: 6,
				email: true
			}	
		},
		messages: {
			etunimi: {     
				required: "Etunimi puuttuu",
				minlength: "Etunimi liian lyhyt"			
			},
			sukunimi: {
				required: "Sukunimi puuttuu",
				minlength: "Sukunimi liian lyhyt"
			},
			puhelin: {
				required: "Puhelinnumero puuttuu",
				minlength: "Puhelinnumero liian lyhyt"
			},
			sposti: {
				required: "S?hk?postiosoite puuttuu",
				minlength: "S?hk?postiosoite liian lyhyt",
				email: "S?hk?postiosoite v??r?ss? muodossa"
			}
		},			
		submitHandler: function(form) {	
			lisaaTiedot();
		}		
	}); 	
});
//funktio tietojen lis??mist? varten. Kutsutaan backin POST-metodia ja v?litet??n kutsun mukana uudet tiedot json-stringin?.
//POST /autot/
function lisaaTiedot(){	
	var formJsonStr = formDataJsonStr($("#tiedot").serializeArray()); //muutetaan lomakkeen tiedot json-stringiksi
	$.ajax({url:"asiakkaat", data:formJsonStr, type:"POST", dataType:"json", success:function(result) { //result on joko {"response:1"} tai {"response:0"}       
		if(result.response==0){
      	$("#ilmo").html("Asiakkaan lis??minen ep?onnistui.");
      }else if(result.response==1){			
      	$("#ilmo").html("Asiakkaan lis??minen onnistui.");
      	$("#etunimi", "#sukunimi", "#puhelin", "#sposti").val("");
		}
  }});	
}
</script>
</html>