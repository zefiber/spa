# Start up back-end app with debug mode
~/spa-git$ mvn spring-boot:run -Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5008

# Access back-end swagger-ui
http://localhost:9090/swagger-ui.html

# MYSQL DB Install and start up properly.
# Please check mysql root account user/password. make sure the
# the root user/password is same as the one in the application.properties under
# src/main/resources/application.properties.


# Run front-end app
~/spa-git/material-kit-react-master$ npm run install:clean

# Access the front-end ui
http://localhost:3000/