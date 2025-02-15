# T-Shirt E-Commerce Website

## Overview
This is a fully functional e-commerce website that sells T-shirts. The platform allows users to browse, purchase, and manage orders efficiently. It includes an admin panel for managing products and orders.

## Features
- User authentication and registration (Firebase Authentication)
- Browse T-shirts with images and descriptions
- Add products to the cart and proceed to checkout
- Online payment integration (Razorpay)
- Order management
- Admin panel for adding, editing, and deleting products
- Responsive design for mobile and desktop

## Tech Stack
### Frontend:
- Angular (Standalone Application)
- Firebase Authentication
- HTML, CSS, TypeScript

### Backend:
- Spring Boot
- MySQL & Oracle (Dual Database Connection: `PDSSD001` and `PMGMD001`)
- RESTful APIs

## Installation
### Prerequisites:
- Node.js & npm
- Angular CLI
- Java 17
- MySQL & Oracle DB

### Steps:
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/ecommerce-tshirt.git
   ```
2. Navigate to the frontend directory and install dependencies:
   ```bash
   cd frontend
   npm install
   ```
3. Start the Angular frontend:
   ```bash
   ng serve
   ```
4. Navigate to the backend directory and build the Spring Boot application:
   ```bash
   cd backend
   mvn clean install
   ```
5. Start the backend server:
   ```bash
   java -jar target/ecommerce-backend.jar
   ```

## Deployment
### Frontend:
- Deployed using GoDaddy

### Backend:
- Hosted on a cloud server (e.g., AWS, DigitalOcean, or a dedicated hosting provider)

## Contribution
Feel free to fork the repository and contribute! Submit a pull request with your changes.

## License
This project is licensed under the MIT License.

## Contact
For any queries, reach out to [your-email@example.com](mailto:your-email@example.com).

