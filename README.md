# CouponSystem---Dynamic-Web-Project

Coupon System Project - core & server side (Dynamic Web Project)
This project is a coupon management system that allows companies to create coupons, customer to purchase coupons and admin to manages manages all of them.

This project defines a database to store and retrieve information about customers, companies and coupons. Above the database there is an insulating layer that allowes convenient work from JAVA to SQL for DB operations.

In addition, there are basic infrastructure services such as ConnectionPool and Daily Thread Which maintain the system and clean expired coupons.

There are three entry points in the system for each of its customers (admin, company or customer) who will login to execute a connect.

Each of the customers (admin, company and customer) is exposed as a service. There are three network services that will enable integration using REST with the system.

There is a UnitTest in which you can see that the system works, but first you have to pick up the Apache Derby Server for the DB.
If you want to send a request and get a response by the URL, you need to update the Apache Debbie project and also run it. The URL is localhost:8080/CouponSystem/name of the class/name of the method. 
