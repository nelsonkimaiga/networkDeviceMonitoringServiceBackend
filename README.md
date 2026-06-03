## BCS Network Device Monitoring Service
This is a back-end web service that will register a device, receive status updates and displaying their current operational state..

Stack used:

Back-end:
- Java 17 & SpringBoot Framework
- Mysql

# How to run:
Clone the repo
open the source code folder in your IntelliJ IDE
build the Maven dependencies and run the application using `mvn clean install`
The Back-end API application will run from port 8080 i.e: http://localhost:8080/

The endpoints defined in the DeviceController class are:

- `POST` - http://localhost:8080/api/devices/register -for device registration
- `POST` - http://localhost:8080/api/devices/{deviceId}/reports - for saving status reports and tying them to a device
- `GET` - http://localhost:8080/api/devices/dashboard - for fetching device details which populate the dashboard on the front-end
- `GET` - http://localhost:8080/api/devices/{deviceId} - for retrieving device details with its 20 most recent status reports

# Db Set-up:
Db set-up (hostname, port and credentials) will be configured by creating an `.env` file, whose contents can be retieved from the `.env.example` which only serves as a template.
