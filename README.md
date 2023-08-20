# Bidly
Bidly is an innovative platform that connects homeowners directly with local trade persons, allowing them to bid on posted projects. 
This project was brought to life by [Vanessa Wingårdh](https://github.com/vwingardh) and [Ibrahim Iqbal](https://github.com/dIB59) during a two-week project at SALT.

## Preview
<img src="https://github.com/Dev-Loaders/Bidly/assets/101557392/773f8f72-6b18-4b96-af45-5c6f69f15c61" alt="iPhone Bidly" width="600px">

## Overview
Traditionally, when homeowners needed to install something as simple as recessed lights, they would have to engage with a commercial company. This often resulted in higher costs and less personalized service. We thought, "What if there was a way to connect with a local electrician for a quote?" Enter Bidly.

In contrast to platforms like Fiverr, which enable users to advertise their skills for hire, Bidly is designed to serve those needing tasks done. Our application fills a void in the market by serving as a conduit between homeowners seeking trade persons and professionals looking for additional work.

Whether you're looking to get a job done or you're a trade person looking for additional work, Bidly is your go-to platform.

## Features
* <strong>Account Creation:</strong> Users can create an account that undergoes an authentication and validation process.
* <strong>Project Browsing:</strong> Once logged in, users are redirected to their workspace where they can explore and bid on an array of posted projects.
* <strong>Publish Project:</strong> Users have the opportunity to post their own projects, specifying project details like title, image, description, location, and necessary materials.
* <strong>Your Projects & Bids:</strong> These dedicated tabs allow users to track their posted projects and the bids they've made.

## Built With
* Next.js
* React.js
* Java
* Spring
* PostgreSQL
* Google OAuth2
* Azure
* Vercel

## Getting Started 
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
* You have installed the latest version of Node.js and npm
* You have installed the latest version of Java JDK
* You have installed the latest version of Spring Boot

#### Installing
Follow these steps to get a copy of the project up and running on your local machine for development and testing purposes:

1. Clone the repository: 
```java
git@github.com:Dev-Loaders/Bidly.git
```

2. Navigate into the project directory:
```java
cd Bidly
```

3. Install the required npm packages:
```java
npm install
```

4. Once the packages have been installed, you can start the application by running:
```java
npm run dev
```

The application will be available on localhost:3000.

#### Setting Up the Development Environment

To set up the development environment for Bidly:

1. Make sure all the prerequisite software is installed and correctly set up.

2. Clone the repository and navigate into the project directory as shown above.

3. Set up a `.env` file in the root of your project with all the necessary environment variables. (You'll need to fill this with your own keys and secrets):
```java
AZURE-STORAGE-ACCOUNT-KEY
AZURE-STORAGE-ACCOUNT-NAME
DATABASE-USER
GOOGLE-CLIENT-ID
GOOGLE-CLIENT-SECRET
PGSQL_DB
PGSQL_PWD
```

4. Install the required packages and start the development server as outlined above.

5. Now, you can make changes in the code and the server will automatically reload the application as it uses hot-reloading.
