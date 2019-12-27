<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="header.jsp">
<c:param name="title" value="new email"></c:param>
</c:import>

<body>
<script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" ></script>
  <script>tinymce.init({selector:'textarea'});</script>

	
	<c:import url="navigator.jsp"></c:import>
	
	<div class="container mt-3 mb-3">
	<c:if test="${isSucess}"><h4 class="text-center text-info">Email sent</h4></c:if> 
		<div class="card p-2">
			<form accept-charset="utf-8" action="do?action=sendEmail" method="POST">
				<div class="form-group">
					<label for="to">TO</label> <input type="text"
						class="form-control" name="to" id="to"
						placeholder="Separate emails by comma " >
				</div>
				<div class="form-group">
					<label for="cc">CC: </label> <input type="text" name="cc"
						id="cc" class="form-control" placeholder="Separate emails by comma"
						>
				</div>
				<div class="form-group">
					<label for="subject">Tiêu đề: </label> <input type="text" name="subject"
						id="subject" class="form-control" placeholder="tiêu đề"
						>
				</div>

				<div class="form-group">
					<label for="content">Nội dung </label>
					<textarea class="form-control" name="content" id="content"
						rows="15"></textarea>
				</div> 
				
				
				<button type="submit" class="btn btn-success">Submit</button>

			</form>
		</div>
	</div>

	<c:import url="footer.jsp"></c:import>