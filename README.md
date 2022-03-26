City Eats Android App
=====================

## Description

A user is walking around Helsinki city centre looking for a place to eat.

## Features

The app displays a list of venues for the current location of the user.

Each venue also has “Favorite” action next to it. “Favorite” works
as a toggle (true/false) and changes the icon depending on the state.

## Development Environment

The app is written in **Kotlin**, asynchronous tasks are handled with **coroutines**, 
and all Gradle build scripts are written with the **Kotlin DSL**.

## Architecture

The architecture is built around **Android Architecture Components**. Logic is
kept away from Activity and Fragment and moved to **ViewModel**. Data is observed
using **Kotlin Flows** and the **Data Binding Library** binds UI components in layout
to the app's data sources.

The **Data layer** handles data operations. User location data comes from a fake
location provider, venues data is stored remotely and is fetched and stored in memory,
and user preferences are stored in **DataStore**. Repository components are responsible
for handling all data operations and abstracting the data sources from the rest of the app. 

A **Domain layer** sits between the data layer and the presentation layer, and handles
discrete pieces of business logic off the UI thread.

The **Navigation component** is used to implement navigation in the app, handling Fragment
transactions and providing a consistent user experience.

UI tests are written with **Espresso** and unit tests use **Junit4** with **Mockito**.

The **Jetpack Benchmark library** is used to benchmark code from within Android Studio.
This enables us to automate measuring and monitoring initial startup time.

**Dependency Injection** is implemented with **Hilt**.
