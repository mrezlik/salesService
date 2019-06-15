# salesService
This is simple salesService api

To run this app:
1. Run SQL script which is on sales_service.sql on MySql server
2. Insert PersistanceJPAConfig class valid date for: 
    a) username for user of database
    b) password for user of password
    c) host for database
    d) port of database
3. Install Tomcat and maven
4. Run mvn clean install
5. Run mvn tomcat7:run
6. Go to host:port/salesSerive/api/warehouse to see all product in warehouse
7. Go to host:port/salesSerive/api/cart to see all product in cart
8. Go to host:port/salesSerive/api/cart/amount to see sum of price of item in cart

Sample request to add/remove given quantity of item to cart:

{
  "productId": 1,
  "quantity": 10
}
