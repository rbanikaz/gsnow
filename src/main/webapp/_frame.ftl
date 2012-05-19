<html>
<head>
	<script language='JavaScript'>
		function submitForm(form) {
			xhr =  new XMLHttpRequest();
			xhr.open("POST", "addEmployee.do", true);
			xhr.onreadystatechange = function() {
				if(xhr.readyState == 4 && xhr.status == 200) {
					alert("Saved!");
				}
			};
			xhr.send("firstName="+encodeURIComponent(form.firstName)+"&lastName="+encodeURIComponent(form.lastName));
		}
	</script>
</head>
<body>
	<div id="starterPage">
		
	  <h1>Congratulation</h1>
	  
	  <h2>You got GSnow working!</h2>
	  
	  <h2>Add Employee</h2>
	  <form action='javascript:submitForm(this)'>
	  	First Name: <input type='text' name='firstName'><br/>
	  	Last Name:  <input type='text' name='lastName'><br/>
	  	<input type='submit'>
	  </form>
	
	  <h2>Get Employee</h2>
	  <form action='employee.json' method='GET'>
	  	ID: <input type='text' name='employeeId'>
	  	<input type='submit'>
	  </form>
	  
	  <h2>List Employee</h2>
	  <form action='employees.json' method='GET'>
	  	<input type='submit'>
	  </form>
	</div>
</body>
</html>