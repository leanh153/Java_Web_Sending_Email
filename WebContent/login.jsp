<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="header.jsp">
<c:param name="title" value="Log in"></c:param>
</c:import>
<body class="bg-dark">

	<div class="container-fluid">
		<div class="row">
			<div class="card mx-auto mt-5">
				<div class="card-header">
					<h2 class="text-center">LOG IN</h2>
					<h5 class="text-center text-info"></h5>
				</div>
				<div class="card-body">
					<form method="POST" action="do?action=dologin">

						<div class="form-group">
							<label for="userName">Email</label> <input type="text"
								name="userName" id="userName" class="form-control"
								placeholder="Email" value="${userName}">

						</div>

						<div class="form-group">
							<label for="password">Password</label> <input type="password"
								name="password" id="password" class="form-control"
								placeholder="Password" value="${password}">
						</div>
						<small class="form-text text-danger"><c:out
								value="${errorMessage}"></c:out></small> <br />
						<button type="submit" class="btn col-12  btn-success">Log
							in</button>
					</form>
				</div>

			</div>

		</div>
	</div>
	<c:import url="footer.jsp"></c:import>