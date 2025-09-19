<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="shop" tagdir="/WEB-INF/tags/shop" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign In</title>
        <shop:css-imports-main />
        <shop:css-imports-signin />
    </head>
    <body>
        <shop:header />

        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100">
                    <form class="login100-form validate-form" action="signin-security-misconfiguration" method="POST">
                        <span class="login100-form-title p-b-26">
                            Welcome
                        </span>

                        <div class="wrap-input100 validate-input" data-validate = "Valid email is: a@b.c">
                            <input class="input100 has-val" type="text" name="email">
                            <span class="focus-input100" data-placeholder="Email"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate="Enter password">
                            <span class="btn-show-pass">
                                <i class="zmdi zmdi-eye"></i>
                            </span>
                            <input class="input100 has-val" type="password" name="password">
                            <span class="focus-input100" data-placeholder="Password"></span>
                        </div>

                        <div class="container-login100-form-btn">
                            <div class="wrap-login100-form-btn">
                                <div class="login100-form-bgbtn"></div>
                                <button id="sign-in-btn" class="login100-form-btn">Sign In</button>
                            </div>
                        </div>

                        <div class="text-center p-t-115">
                            <span class="txt1">
                                Don't have an account?
                            </span>

                            <a class="txt2" href="signup">Sign Up</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <shop:footer />
        <shop:js-imports-signin />
    </body>
</html>
