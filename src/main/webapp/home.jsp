<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Twitter</title>
	<link rel="stylesheet" href="twitter.css">
</head>
<body>

	<div><a href="/Twitter">Home</a></div>

	<div>
		<form action="/Twitter/post" method=post>
			<input type="text" name="content"><input type=submit value="Tweet!">
		</form>
	</div>

	<c:forEach var="tweet" items="${tweets}">
		<div class="tweet">
			<div class="tweet-content">${tweet.content}</div>
			<div class="tweet-date">${tweet.dateTime}</div>
		</div>	
	</c:forEach>

</body>
</html>