
function getRelatorio() {

	var dataInicial = $("#dataInicial").val();
	var dataFinal = $("#dataFinal").val();
		
	if (!isDate(dataInicial)) {
		alert("Data Inicial inválida!");
		return;
	} else if (!isDate(dataFinal)) {
        alert("Data Final inválida!");
        return;
    }
	
	var url = "http://localhost:8080/relatorio-cidade-gasto?"
	            + "data_inicial=" + dataInicial
	            + "&data_final=" + dataFinal;

	$.ajax({
		method : "GET",
		url : url
	}).done(
			function(data) {
			    document.getElementById("dataInicialRelatorio").textContent = data.dataInicial;
                document.getElementById("dataFinalRelatorio").textContent = data.dataFinal;
                document.getElementById("registros").innerHTML = "";

                $("#relatorio").css("visibility", "visible");
                $("#cabecalho").css("visibility", "visible");

				data.cidades.forEach(addRegistro);
			}).fail(
					function(data) {
						$("#relatorio").css("visibility", "hidden");
						$("#cabecalho").css("visibility", "hidden");
						alert("Não foi possível gerar o relatório." + data);
					});

}

function addRegistro(item, index) {
  document.getElementById("registros").innerHTML += "<tr>"
                                                        + "<td>"+item.nomeCidade+"</td>"
                                                        + "<td>"+item.valor.toFixed(2)+"</td>"
                                                  + "</tr>";
}

function isDate(txtDate) {
	  var currVal = txtDate;
	  if(currVal == '')
	  	return false;
	    
	  //Declare Regex 
	  var rxDatePattern = /^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$/;
	  var dtArray = currVal.match(rxDatePattern); // is format OK?
	  if (dtArray == null)
	     return false;
	     
	  //Checks for yyyy-mm-dd format.
	  dtDay= currVal.substring(0,2);
	  dtMonth = currVal.substring(3,5);
	  dtYear = currVal.substring(6,10);
	  if (dtMonth < 1 || dtMonth > 12)
	      return false;
	  else if (dtDay < 1 || dtDay> 31)
	      return false;
	  else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31)
	      return false;
	  else if (dtMonth == 2)
	  {
	     var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
	     if (dtDay> 29 || (dtDay ==29 && !isleap))
	          return false;
	  }
	  return true;
}