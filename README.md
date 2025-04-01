# ECHO Sensor Dashboard

A Spring Boot application for monitoring and managing sensor data, particularly focused on gas sensors.

## Features

- Real-time sensor data monitoring
- Zone-based data organization
- User authentication and authorization
- Geographic data management (Cities and Zones)
- Alert system for high gas levels
- RESTful API endpoints

## Tech Stack

- Spring Boot 3.2.0
- Spring Data JPA
- Spring Security
- H2 Database
- Maven
- Lombok
- Thymeleaf

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Getting Started

1. Clone the repository:
```bash
git clone https://github.com/yourusername/echo-sensor-dashboard.git
```

2. Navigate to the project directory:
```bash
cd echo-sensor-dashboard
```

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

5. Access the application:
- Web Interface: https://sewer-dashboard.onrender.com/
- API Documentation: http://localhost:8080/swagger-ui.html

## API Endpoints

### Authentication
- POST `/api/auth/register` - Register new user
- PUT `/api/auth/profile/{userId}` - Update user profile

### Sensors
- POST `/api/sensors/reading` - Save sensor reading
- GET `/api/sensors/zone/{zoneId}` - Get zone readings
- GET `/api/sensors/zone/{zoneId}/range` - Get readings by time range
- GET `/api/sensors/alerts` - Get high gas readings
- POST `/api/sensors/zones` - Create new zone
- GET `/api/sensors/zones/city/{cityId}` - Get zones by city

## Project Structure

```
src/main/java/com/echo/sensordashboard/
├── controller/    # REST controllers
├── model/        # Entity classes
├── repository/   # Data access layer
├── service/      # Business logic
└── config/       # Configuration classes
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 
