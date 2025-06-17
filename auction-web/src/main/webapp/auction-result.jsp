<%--
  Created by IntelliJ IDEA.
  User: dulanjaya
  Date: 6/5/2025
  Time: 7:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Auction Result - Online Auction System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-12 d-flex flex-row justify-content-between p-3 align-items-center bg-light border border-1">
            <h3>Auction Results</h3>
            <a class="btn btn-light" href="home" role="button">Go Back</a>
        </div>
        <div class="col-12">
            <div class="row">
                <div class="col-12 p-3">
                    <table class="table table-bordered table-hover">
                        <tbody>
                        <tr>
                            <th>#.</th>
                            <th>AuctionId</th>
                            <th>Product Name</th>
                            <th>Start Time</th>
                            <th>End Time</th>
                            <th>Winner</th>
                        </tr>
                        <c:forEach var="auctionResultDTO" items="${requestScope.auctionResultDTOs}">
                            <tr>
                                <td>${auctionResultDTO.sortingNumber}</td>
                                <td>${auctionResultDTO.auctionId}</td>
                                <td>${auctionResultDTO.productName}</td>
                                <td>${auctionResultDTO.startDateTime}</td>
                                <td>${auctionResultDTO.endDateTime}</td>
                                <td>${auctionResultDTO.winner}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</body>
</html>
