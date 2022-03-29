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
kept away from UI components and moved to **ViewModel**. Data is observed
using **Kotlin Flows** and the **Data Binding Library**.

User location data comes from a fake location provider, venues data is fetched from
backend and stored in memory, and user preferences are stored in **DataStore**.
Repository components are responsible for handling all data operations and abstracting
the data sources from the rest of the app. 

**Dependency Injection** is implemented with **Hilt**.

## Roadmap

City Eats app is a work in progress, and the following non-feature requirements are due
to be implemented before it is ready for publishing:

- navigation (e.g. **Navigation Component**)
- warning/error log aggregation (e.g. **Firebase Crashlytics**)
- analytics events aggregation
- UI tests and unit tests
- launcher icon
- certificate and signing config

It's also good to have early:

- automated code style check (e.g. **Spotless**)
- performance tests
- theme and design components 
- accessibility labels for UI elements
