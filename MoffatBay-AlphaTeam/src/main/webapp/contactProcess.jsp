<!-- Alpha Team
	Created by: Reed Bunnell -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="beans.ContactBean" %>

<%
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String message = request.getParameter("message");

    boolean sent = false;

    if (name != null && email != null && message != null
            && !name.trim().isEmpty() && !email.trim().isEmpty() && !message.trim().isEmpty()) {
        ContactBean cb = new ContactBean();
        cb.setName(name.trim());
        cb.setEmail(email.trim());
        cb.setMessage(message.trim());
        sent = cb.saveMessage();
    }
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Contact Us - Moffat Bay Lodge</title>

    <link rel="stylesheet" href="css/styles.css">

    <style>
        body {
            font-family: 'Poppins', Arial, sans-serif;
            background-color: var(--light-bg);
        }

        h1 {
            text-align: center;
            margin-top: 120px;
            margin-bottom: 40px;
            color: var(--text);
        }

        .section {
            max-width: 600px;
            margin: 0 auto 30px auto;
            background: #fff;
            padding: 40px;
            border-radius: var(--radius-md);
            box-shadow: 0 4px 20px rgba(0,0,0,0.05);
            text-align: center;
        }

        .section h2 {
            color: var(--text);
            margin-bottom: 12px;
        }

        .section p {
            color: var(--muted);
            margin-bottom: 24px;
        }
    </style>
</head>

<body>

<%@ include file="components/header.jsp" %>

<h1>Contact Us</h1>

<div class="section">
    <% if (sent) { %>
        <h2>Message Sent!</h2>
        <p>Thanks, <%= name %>! We received your message and will get back to you at <%= email %> soon.</p>
        <div class="main-button"><a href="index.jsp">Back to Home</a></div>
    <% } else { %>
        <h2>Something Went Wrong</h2>
        <p>We weren't able to send your message. Please try again.</p>
        <div class="main-button"><a href="about.jsp">Try Again</a></div>
    <% } %>
</div>

<%@ include file="components/footer.jsp" %>

</body>
</html>
