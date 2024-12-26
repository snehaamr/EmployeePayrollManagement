<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pay Slip</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
		<div class="alert alert-success clead">${error}</div>
		<div class="well clead" style="color: #4682B4; font-size: 21px;">Enter
			Year and Month to generate PaySlip.</div>
		<form:form method="POST" modelAttribute="paySlip"
			class="form-horizontal" action="generate-PaySlip">
			<%-- <div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-2 control-lable" for="user">User</label>
					<div class="col-md-7">
						<form:select path="user" items="${usersList}" multiple="false"
							itemValue="id" itemLabel="userName" class="form-control input-sm"
							id="designation" />
						<div class="has-error">
							<form:errors path="user" class="help-inline" />
						</div>
					</div>
				</div>
			</div> --%>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="year">Year</label>
					<div class="col-md-7">
						<form:input type="text" path="year" id="year"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="year" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="month">Month</label>
					<div class="col-md-7">
						<form:input type="text" path="month" id="month"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="month" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='view-wages' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Generate"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='view-wages' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>