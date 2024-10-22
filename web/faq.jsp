<%-- 
    Document   : faq
    Created on : Oct 21, 2024, 8:00:37 AM
    Author     : Nguyen Huu Khoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Us - Koi Kingdom</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">       
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/contact.css">
        <script src="js/load.js"></script>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <style>
        .faq-title {
            text-align: center;
            margin: 20px 0;
        }
        .faq-content {
            width: 50%;
            margin: 0 auto;
        }
        .accordion-button {
            background-color: #f8f9fa; /* Light background color */
            color: #343a40; /* Dark text color */
            font-weight: bold; /* Bold text for questions */
        }
        .accordion-button:not(.collapsed) {
            background-color: #007bff; /* Blue color for the active button */
            color: white; /* White text for the active button */
        }
        .accordion-body {
            font-style: italic; /* Italic style for answers */
            color: #555; /* Darker gray for the answer text */
        }
    </style>
    <body>

        <jsp:include page="headerForCustomer.jsp" flush="true"/>
        <div class="faq-title">
            <h1>Frequently Asked Questions</h1>
            </br>
            <a href="contact" class="btn btn-success">Press here if you want have our support</a>
        </div>
        <div class="faq-content">
            <div class="accordion" id="accordionExample">
                <!-- Question 1 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingOne">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                            How long does the koi fish farm tour last?
                        </button>
                    </h2>
                    <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne">
                        <div class="accordion-body">
                            The tour typically lasts between 2 to 4 hours, depending on the package and schedule.
                        </div>
                    </div>
                </div>

                <!-- Question 2 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingTwo">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            Is there a tour guide?
                        </button>
                    </h2>
                    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo">
                        <div class="accordion-body">
                            Yes, we provide local guides who speak both English and Japanese.
                        </div>
                    </div>
                </div>

                <!-- Question 3 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingThree">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            How much does the tour cost?
                        </button>
                    </h2>
                    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree">
                        <div class="accordion-body">
                            The price ranges from 3,000 yen to 10,000 yen, depending on the package and group size.
                        </div>
                    </div>
                </div>

                <!-- Question 4 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingFour">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                            What should I bring for the koi fish farm tour?
                        </button>
                    </h2>
                    <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour">
                        <div class="accordion-body">
                            We recommend bringing comfortable clothing, sunscreen, and a camera to capture the beautiful scenery.
                        </div>
                    </div>
                </div>

                <!-- Question 5 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingFive">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                            Is food provided during the tour?
                        </button>
                    </h2>
                    <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive">
                        <div class="accordion-body">
                            Snacks and beverages are provided during the tour, but we recommend eating a full meal before the trip.
                        </div>
                    </div>
                </div>

                <!-- Question 6 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingSix">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
                            Can I take photos during the tour?
                        </button>
                    </h2>
                    <div id="collapseSix" class="accordion-collapse collapse" aria-labelledby="headingSix">
                        <div class="accordion-body">
                            Yes, you are encouraged to take photos during the tour. Please be respectful of other guests and our farm rules.
                        </div>
                    </div>
                </div>

                <!-- Question 7 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingSeven">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
                            Are children allowed on the tour?
                        </button>
                    </h2>
                    <div id="collapseSeven" class="accordion-collapse collapse" aria-labelledby="headingSeven">
                        <div class="accordion-body">
                            Yes, children are welcome on the tour, but they must be accompanied by an adult at all times.
                        </div>
                    </div>
                </div>

                <!-- Question 8 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingEight">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseEight" aria-expanded="false" aria-controls="collapseEight">
                            What happens if it rains on the day of the tour?
                        </button>
                    </h2>
                    <div id="collapseEight" class="accordion-collapse collapse" aria-labelledby="headingEight">
                        <div class="accordion-body">
                            Tours will proceed rain or shine, but we may modify the itinerary for safety reasons. Please check the weather before your trip.
                        </div>
                    </div>
                </div>

                <!-- Question 9 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingNine">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseNine" aria-expanded="false" aria-controls="collapseNine">
                            Is there parking available at the farm?
                        </button>
                    </h2>
                    <div id="collapseNine" class="accordion-collapse collapse" aria-labelledby="headingNine">
                        <div class="accordion-body">
                            Yes, there is ample parking available for guests at the farm.
                        </div>
                    </div>
                </div>

                <!-- Question 10 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingTen">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTen" aria-expanded="false" aria-controls="collapseTen">
                            How do I make a reservation for the tour?
                        </button>
                    </h2>
                    <div id="collapseTen" class="accordion-collapse collapse" aria-labelledby="headingTen">
                        <div class="accordion-body">
                            Reservations can be made through our website or by contacting us directly via phone or email.
                        </div>
                    </div>
                </div>
            </div>
        </div>
        

        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>
