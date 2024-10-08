# Strava App

This project is an Android application that integrates with the Strava API to allow users to authenticate via Strava, view their activity statistics, and interact with their Strava data. Built using Jetpack Compose for the UI and a clean architecture approach, this app provides a seamless experience for fetching and displaying authenticated user data from Strava.

## Features

- **Strava OAuth Integration**: Users can authenticate with Strava to grant the app access to their activity data.
- **Display Activity Stats**: After authentication, the app displays detailed statistics such as total rides, total distance, and elevation gain.
- **Asynchronous Data Fetching**: Data is fetched asynchronously using coroutines to ensure smooth performance.
  
## Tech Stack

- **Kotlin**: Primary language for development.
- **Jetpack Compose**: Modern Android UI toolkit for building native interfaces.
- **Retrofit and OkHttp**: For making network requests to the Strava API.
- **LiveData and ViewModel**: For managing UI-related data and lifecycle.

## How to Build and Run

### Prerequisites

1. **Strava API Credentials**:
   - Use the Credentials provided or...
   - You need to create a Strava API application at [Strava Developers](https://developers.strava.com/). After creating the app, you will get a `client_id`, `client_secret`, and define a `redirect_uri`.

3. **Android Development Environment**: 
   - Ensure that Android Studio is installed with the latest SDK and tools for Kotlin development.

### Steps

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Ethan-Way/Strava-App.git
   cd Strava-App
   ```

2. **Set Up Strava Credentials**:
   - Open the `StravaRepository` class, and replace the placeholders for `client_id`, `client_secret`, and `redirect_uri` with your actual values.

3. **Run the App**:
   - Open the project in Android Studio.
   - Connect your Android device or start an emulator.
   - Hit the **Run** button to build and deploy the app.

### Authentication Flow

The app uses the Strava OAuth flow for authentication:

1. When the user presses "Authorize with Strava", they are redirected to Strava’s login page.
2. After successful login, Strava redirects back to the app with an OAuth code.
3. The app exchanges this code for an access token, which is used to fetch the user’s activity data.

## Future Improvements

- **Local Caching**: Implement a Room database to store the user’s stats locally, reducing the need for constant API requests and authentication.
- **Additional Statistics**: Expand the displayed data to include running and swimming statistics.
- **Enhanced UI**: Implement pagination or lazy loading for activity data to enhance performance for users with a lot of data. Aswell as better overall data display formatting and themes.
