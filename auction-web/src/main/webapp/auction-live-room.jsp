<%--
  Created by IntelliJ IDEA.
  User: dulanjaya
  Date: 6/3/2025
  Time: 7:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Live - ${requestScope.auctionDTO.productName}- Online Auction System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-12 d-flex flex-row justify-content-between p-3 align-items-center bg-light border border-1">
            <h3>Auction Live Room</h3>
            <a class="btn btn-light" href="home" role="button">Go Back</a>
        </div>
        <div class="col-12">
            <div class="row">
                <%-- user detail section start --%>
                <div class="col-12 col-sm-12 col-md-12 col-lg-3 col-xl-3 d-none d-sm-none d-md-none d-lg-flex d-xl-flex border-end border-1">
                    <div class="d-flex flex-column justify-content-start ps-3 pt-3 pe-2 pb-2">
                        <div class="col-12">
                            <h5 class="ps-0">Auction</h5>
                        </div>
                        <div class="col-12 pt-2">
                            <table>
                                <tbody>
                                <tr>
                                    <td>Product Name : </td>
                                    <td>${requestScope.auctionDTO.productName}</td>
                                </tr>
                                <tr>
                                    <td>Est. Price : </td>
                                    <td>${requestScope.auctionDTO.minEstimatePrice} - ${requestScope.auctionDTO.maxEstimatePrice}</td>
                                </tr>
                                <tr>
                                    <td>Start : </td>
                                    <td>${requestScope.auctionDTO.startDateTime}</td>
                                </tr>
                                <tr>
                                    <td>End : </td>
                                    <td>${requestScope.auctionDTO.endDateTime}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-12 mt-4">
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
                                    <td>Bidder Id : </td>
                                    <td>${requestScope.bidderId}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <%-- user detail section end --%>
                <%-- auction section start --%>
                <div class="col-12 col-sm-12 col-md-12 col-lg-9 col-xl-9">
                    <div class="row ps-3 pt-3 pe-2 pb-2">
                        <div class="col-12">
                            <h5 class="ps-0">All Bids</h5>
                        </div>
                        <div class="col-12">
                            <div class="p-3 border border-1 rounded mt-2">
                                <div class="ps-0 pt-2 pe-2 pb-2" id="bid-card-loader" style="position: relative;overflow-x: hidden;overflow-y: scroll;height: calc((100dvh / 2) - 40px);">
                                    <%-- bid card loading area start --%>
                                    <%-- bid-clone-card --%>
                                        <div class="card mb-3" id="bid-card">
                                            <div class="card-body d-flex justify-content-between">
                                                <span class="tf-3 fw-bold" id="bid-card-price"></span>
                                                <span id="bid-card-bidder-id"></span>
                                            </div>
                                        </div>
                                    <%-- bid-clone-card --%>
                                    <c:forEach var="bidRecordDTO" items="${requestScope.bidRecordDTOs}">
                                        <div class="card mb-3">
                                            <div class="card-body d-flex justify-content-between">
                                                <span class="tf-3 fw-bold">${bidRecordDTO.price}</span>
                                                <span>${bidRecordDTO.bidderId}</span>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    <%-- bid card loading area end --%>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 mt-4">
                            <div class="row">
                                <div class="col-12">
                                    <table>
                                        <tbody>
                                        <tr>
                                            <td>Starting Price : </td>
                                            <td id="starting-price">${requestScope.startingPrice}</td>
                                        </tr>
                                        <tr>
                                            <td>Next Bid Increment : </td>
                                            <td id="next-bid-increment">${requestScope.nextBidIncrement}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-12 mt-2">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="input-group mb-3">
                                                <span class="input-group-text" id="basic-addon1">LKR</span>
                                                <input type="number" id="bid-price" class="form-control" aria-label="Recipient’s username" aria-describedby="button-addon2"/>
                                                <button class="btn btn-primary" type="button" onclick="placeBid()">Place a Bid</button>
                                            </div>
                                            <div class="form-text text-capitalize" id="basic-addon4"><sup class="form-text">*</sup> Your bid must be equal to or higher than the next bid increment</div>
                                        </div>
                                    </div>
                                </div>
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

<script>
    const bidNotificationWebsocket = new WebSocket("ws://localhost:8080/online-auction/bidNotificationWebSocket/${requestScope.auctionDTO.auctionId}");

    const bidPrice = document.getElementById("bid-price");
    const bidCardLoader = document.getElementById("bid-card-loader");
    const bidCard = document.getElementById("bid-card");
    const nextBidIncrement = document.getElementById("next-bid-increment");

    // remove the clone bid card from bid card loader
    bidCardLoader.removeChild(bidCard);

    // bid card loader automatically scroll down to bottom when all the DOM content loaded
    document.addEventListener("readystatechange", event => {
        if (event.target.readyState === "complete") {
            adjustBidScroll();
        }
    });

    function adjustBidScroll(){
        bidCardLoader.scrollTop = bidCardLoader.scrollHeight;
    }

    function addNewBidCard(bidObject){
        // construct the bid price
        const formattedBidPrice = new Intl.NumberFormat("en-Us").format(bidObject.amount) + "LKR";

        // clone the bid-card
        const bidCardClone = bidCard.cloneNode(true);
        bidCardClone.querySelector("#bid-card-price").innerHTML = formattedBidPrice;
        bidCardClone.querySelector("#bid-card-bidder-id").innerHTML = bidObject.bidderId;

        // add new bid-card to the bid card loader
        bidCardLoader.appendChild(bidCardClone);

        // bid card loader automatically scroll down to bottom
        adjustBidScroll();
    }

    function placeBid() {

        if(bidPrice.value.length > 0){
            // valid bid price
            const bid = {
                auctionId: "${requestScope.auctionDTO.auctionId}",
                amount: bidPrice.value,
                bidderId: "${requestScope.bidderId}"
            };

            // send bid object to the BidNotificationWebSocket
            bidNotificationWebsocket.send(JSON.stringify(bid));

        }else {
            // invalid bid price and reset the value
            bidPrice.value = 100;
        }
    }

    bidNotificationWebsocket.onmessage = (event) => {
        const bid = JSON.parse(event.data);
        addNewBidCard(bid);

        // calculate the next bid increment
        const nextBidIncrementPrice = new Intl.NumberFormat("en-Us").format(parseFloat(bid.amount) + (parseFloat(bid.amount) * 0.1)) + "LKR";
        // update the next bid increment
        nextBidIncrement.innerHTML = nextBidIncrementPrice;
        // clear the current bid price
        bidPrice.value = "";
    };

</script>

</body>
</html>
