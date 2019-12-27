<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="header.jsp">
	<c:param name="title" value="Inbox"></c:param>
</c:import>

<body>
	<c:import url="navigator.jsp"></c:import>
	<div class="container mt-2">
		<c:choose>
			<c:when test="${not empty listEmail && listEmail != null}">
				<c:forEach var="email" items="${listEmail}">
					<div class="card mb-2">
						<div class="card-header">
							<h5 class="card-title font-weight-bold">
								<a class="text-dark card-title" href="do?action=detail&id="><c:out
										value="${email.subject}"></c:out> </a>
							</h5>

							<span class="small">From: <c:out value="${email.from}"></c:out>

							</span>
							<div class="float-right">
								 <span
									class="small d-inline-block">Received date: <fmt:formatDate
										pattern="yyyy-MM-dd" value="${email.receivedDate}" /></span>
							</div>

						</div>
						
						<div class="card-body">${email.content}</div>
					</div>
				</c:forEach>
			</c:when>

			<c:otherwise>
				<jsp:forward page="do?action=listReceived"></jsp:forward>
				
			</c:otherwise>

		</c:choose>
	</div>
	<c:import url="footer.jsp"></c:import>