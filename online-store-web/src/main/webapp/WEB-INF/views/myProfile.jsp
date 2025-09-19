<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="shop" tagdir="/WEB-INF/tags/shop" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Profile</title>
        <shop:css-imports-main />
    </head>

    <body>
        <shop:header />
        <shop:profile-banner />
        <div class="product-profile-box">
            <div class="container">
                <div class="row">
                    <div class="col-md-2 col-sm-4 pr">
                        <shop:profile-picture />
                    </div>
                    <div class="col-md-10 col-sm-8">
                        <div class="profile-pro-right">
                            <div class="panel with-nav-tabs panel-default">
                                <div class="panel-heading clearfix">
                                    <ul class="nav nav-tabs pull-left">
                                        <li class="active"><a href="#tab1default" data-toggle="tab">Personal Info</a></li>
                                    </ul>
                                </div>
                                <div class="panel-body">
                                    <div class="tab-content">
                                        <shop:profile-personal-info />
                                        <shop:profile-referrals />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <shop:footer />
        <shop:js-imports-main />
    </body>
</html>
