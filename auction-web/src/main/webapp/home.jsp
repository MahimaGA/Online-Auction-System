<%--
  Created by IntelliJ IDEA.
  User: dulanjaya
  Date: 6/1/2025
  Time: 12:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="lk.jiat.auction.core.constant.Params" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="lk.jiat.auction.core.model.Auction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home - Online Auction System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-12 d-flex flex-row justify-content-between p-3 align-items-center bg-light border border-1">
                <h5>Online Auction System</h5>
                <a class="btn btn-light" href="UserLogout" role="button">Logout</a>
            </div>
            <div class="col-12">
                <div class="row">
                    <%-- user detail section start --%>
                    <div class="col-12 col-sm-12 col-md-12 col-lg-3 col-xl-3 d-none d-sm-none d-md-none d-lg-flex d-xl-flex border-end border-1">
                        <div class="col-12 d-flex flex-column justify-content-start ps-3 pt-3 pe-2 pb-2">
                            <div class="col-12">
                                <h5 class="ps-0">User Details</h5>
                            </div>
                            <div class="col-12 pt-2">
                                <table>
                                    <tbody>
                                    <tr>
                                        <td>Username : </td>
                                        <td>${requestScope.user.username}</td>
                                    </tr>
                                    <tr>
                                        <td>First Name : </td>
                                        <td>${requestScope.user.firstName}</td>
                                    </tr>
                                    <tr>
                                        <td>Last Name : </td>
                                        <td>${requestScope.user.lastName}</td>
                                    </tr>
                                    <tr>
                                        <td>Email : </td>
                                        <td>${requestScope.user.email}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-12 pt-3">
                                <h5 class="ps-0">Auction Results</h5>
                            </div>
                            <div class="col-12 pt-2 d-grid">
                                <a class="btn btn-primary btn-sm" href="AuctionResultLoad" role="button">View Results</a>
                            </div>
                        </div>
                    </div>
                    <%-- user detail section end --%>
                    <%-- auction section start --%>
                    <div class="col-12 col-sm-12 col-md-12 col-lg-9 col-xl-9">
                        <div class="row ps-3 pt-3 pe-2 pb-2">
                            <div class="col-12">
                                <h5 class="ps-0">Available Auctions</h5>
                            </div>
                            <div class="col-12">
                                <div class="row">
                                    <%-- auction card loading area start --%>
                                    <c:forEach var="eligibleAuctionDTO" items="${requestScope.eligibleAuctionDTOs}">
                                        <div class="col-12 col-sm-12 col-md-6 col-lg-4 col-xl-4 ps-2 pt-3">
                                            <div class="card w-100">
                                                <div class="card-body">
                                                    <h5 class="card-title">${eligibleAuctionDTO.productName}</h5>
                                                    <h6 class="card-subtitle mb-2 text-body-secondary">Est: ${eligibleAuctionDTO.minEstimatePrice} - ${eligibleAuctionDTO.maxEstimatePrice}</h6>
                                                    <span class="card-text">Start : ${eligibleAuctionDTO.startDateTime}</span><br>
                                                    <span class="card-text">End : ${eligibleAuctionDTO.endDateTime}</span><br>
                                                    <div class="d-grid gap-2 mt-2">
                                                        <c:choose>
                                                            <c:when test="${eligibleAuctionDTO.registerStatus}">
                                                                <a class="btn btn-danger btn-sm" href="UserConnectLiveAuction?auctionId=${eligibleAuctionDTO.auctionId}" role="button">Enter Live Auction</a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a class="btn btn-primary btn-sm" href="UserJoinAuction?auctionId=${eligibleAuctionDTO.auctionId}" role="button">Join Auction</a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    <%-- auction card loading area end --%>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%-- auction section end --%>
                </div>
            </div>
        </div>
    </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</body>
</html>
