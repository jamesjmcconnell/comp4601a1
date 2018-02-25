$(document).ready(function(){
   $(".search-icon").click(function() {
	  if($(".form__input").val() != ""){
		  var input = $(".form__input").val();
		  var dq = input.split(" ");
		  var cq = "";
		  dq.forEach(function(q){
			 cq += q + "+"; 
		  });
		  cq = cq.substring(0, cq.length - 1);
		  console.log(cq);
		  
		  var url = "/COMP4601-SDA/rest/sda/searchFormatted/" + cq;
		  var jqxhr = $.get(url)
			.done(function(data) {
			    console.log(data);
			    $(".results_area").html(data);
			    $(".results_area").removeClass("hide");
			})
			.fail(function(data) {
			   console.log(data);
			});
	  }
   });
});