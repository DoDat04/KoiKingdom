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
        .faq{
            max-width: 700px;
            margin-top: 2rem;
            padding-bottom: 1rem;
            border-bottom: 2px solid #fff;
            cursor: pointer;
        }
        .question{
            display:flex;
            justify-content: space-between;
            align-items: center;
        }
        .question h4{
            font-size: 1.8rem;
        }
        .answer{
            max-height: 0;
            overflow: hidden;
            transition: max-height;
        }

        .answer p {
            padding-top: 1rem;
            line-height: 1.6;
            font-size: 93%;
        }

        .faq.active .answer{
            max-height: 300px;
            animation: fade 1s ease-in-out;
        }

        .faq.active svg{
            transform: rotate(180deg);
        }
        .faq.active .question {
            animation: fade 1s ease-in-out;
            background-color: lightblue; /* Background color for the question when active */
        }
        svg{
            transition: transform 0.5 ease-in;
        }
        @keyframes fade{
            from{
                opacity:0;
                transform: translateY(-10px);
            }
            to{
                opacity: 1;
                transform: translateY(0px);
            }
        }
    </style>
    <body>
        <div class="colorlib-loader"></div>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>
        <div class="faq-title" style="justify-items: center;">
            <h1>Frequently Asked Questions</h1>
            </br>
            <a href="contact" class="btn btn-success">Press here if you want have our support</a>
        </div>
        <div class="faq-content" style="    justify-items: center">
            <div>
                <!-- Question 1 -->
                <div class="faq">
                    <div class="question">
                        <h4>How long does the koi fish farm tour last?</h4>
                        <svg width="15" height="10" viewBox="0 0 42 25">
                        <path d="M3 3L21 21L39 3" stroke-width="7" stroke-linecap="round" />
                        </svg>
                    </div>
                    <div class="answer">
                        <p>The tour typically lasts between 2 to 4 hours, depending on the package and schedule.</p>
                    </div>
                </div>

                <!-- Question 2 -->
                <div class="faq">
                    <div class="question">
                        <h4>
                            Is there a tour guide?
                        </h4>
                        <svg width="15" height="10" viewBox="0 0 42 25">
                        <path
                            d="M3 3L21 21L39 3"
                            stroke-width="7"
                            stroke-linecap="round"
                            />    
                        </svg>
                    </div>
                    <div class="answer">
                        <p>
                            Yes, we provide local guides who speak both English and Japanese.
                        </p>
                    </div>
                </div>

                <!-- Question 3 -->
                <div class="faq">
                    <div class="question">
                        <h4>
                            How much does the tour cost?
                        </h4>
                        <svg width="15" height="10" viewBox="0 0 42 25">
                        <path
                            d="M3 3L21 21L39 3"
                            stroke-width="7"
                            stroke-linecap="round"
                            />    
                        </svg>
                    </div>
                    <div class="answer">
                        <p>
                            The price ranges from 3,000 yen to 10,000 yen, depending on the package and group size.
                        </p>
                    </div>
                </div>

                <!-- Question 4 -->
                <div class="faq">
                    <div class="question">
                        <h4>
                            What should I bring for the koi fish farm tour?
                        </h4>
                        <svg width="15" height="10" viewBox="0 0 42 25">
                        <path
                            d="M3 3L21 21L39 3"
                            stroke-width="7"
                            stroke-linecap="round"
                            />    
                        </svg>
                    </div>
                    <div class="answer">
                        <p>
                            We recommend bringing comfortable clothing, sunscreen, and a camera to capture the beautiful scenery.
                        </p>
                    </div>
                </div>

                <!-- Question 5 -->
                <div class="faq">
                    <div class="question">
                        <h4>
                            Is food provided during the tour?
                        </h4>
                        <svg width="15" height="10" viewBox="0 0 42 25">
                        <path
                            d="M3 3L21 21L39 3"
                            stroke-width="7"
                            stroke-linecap="round"
                            />    
                        </svg>
                    </div>
                    <div class="answer">
                        <p>
                            Snacks and beverages are provided during the tour, but we recommend eating a full meal before the trip.
                        </p>
                    </div>
                </div>

                <!-- Question 6 -->
                <div class="faq">
                    <div class="question">
                        <h4>
                            Can I take photos during the tour?
                        </h4>
                        <svg width="15" height="10" viewBox="0 0 42 25">
                        <path
                            d="M3 3L21 21L39 3"
                            stroke-width="7"
                            stroke-linecap="round"
                            />    
                        </svg>
                    </div>
                    <div class="answer">
                        <p>
                            Yes, you are encouraged to take photos during the tour. Please be respectful of other guests and our farm rules.
                        </p>
                    </div>
                </div>

                <!-- Question 7 -->
                <div class="faq">
                    <div class="question">
                        <h4>
                            Are children allowed on the tour?
                        </h4>
                        <svg width="15" height="10" viewBox="0 0 42 25">
                        <path
                            d="M3 3L21 21L39 3"
                            stroke-width="7"
                            stroke-linecap="round"
                            />    
                        </svg>
                    </div>
                    <div class="answer">
                        <p>
                            Yes, children are welcome on the tour, but they must be accompanied by an adult at all times.
                        </p>
                    </div>
                </div>


                <!-- Question 8 -->
                <div class="faq">
                    <div class="question">
                        <h4>
                            What happens if it rains on the day of the tour?
                        </h4>
                        <svg width="15" height="10" viewBox="0 0 42 25">
                        <path
                            d="M3 3L21 21L39 3"
                            stroke-width="7"
                            stroke-linecap="round"
                            />    
                        </svg>
                    </div>
                    <div class="answer">
                        <p>
                            Tours will proceed rain or shine, but we may modify the itinerary for safety reasons. Please check the weather before your trip.
                        </p>
                    </div>
                </div>

                <!-- Question 9 -->
                <div class="faq">
                    <div class="question">
                        <h4>
                            Is there parking available at the farm?
                        </h4>
                        <svg width="15" height="10" viewBox="0 0 42 25">
                        <path
                            d="M3 3L21 21L39 3"
                            stroke-width="7"
                            stroke-linecap="round"
                            />    
                        </svg>
                    </div>
                    <div class="answer">
                        <p>
                            Yes, there is ample parking available for guests at the farm.
                        </p>
                    </div>
                </div>
                <!-- Question 10 -->
                <div class="faq">
                    <div class="question">
                        <h4>
                            How do I make a reservation for the tour?
                        </h4>
                        <svg width="15" height="10" viewBox="0 0 42 25">
                        <path
                            d="M3 3L21 21L39 3"
                            stroke-width="7"
                            stroke-linecap="round"
                            />    
                        </svg>
                    </div>
                    <div class="answer">
                        <p>
                            Reservations can be made through our website or by contacting us directly via phone or email.
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <script>
            const faqs = document.querySelectorAll(".faq");

            faqs.forEach(faq => {
                faq.addEventListener("click", () => {
                    faq.classList.toggle("active");
                });
            });
        </script>
        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>
