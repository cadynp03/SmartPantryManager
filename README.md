# Smart Pantry Manager

Smart Pantry Manager is an Android application developed in Java using Android Studio. The application helps users manage ingredients stored in their pantry and suggests recipes that can be prepared using the ingredients currently available.

## Features

- Add pantry ingredients
- View stored pantry ingredients
- Edit existing ingredients
- Delete ingredients
- Store ingredient quantities and units
- Persistent pantry storage using SQLite
- 20 preloaded recipes
- Recipe suggestions based on available pantry ingredients
- Quantity-based recipe matching
- Recipe detail screen with ingredients and instructions
- Custom adapters for pantry items and recipes
- Settings using SharedPreferences
- Option to show or hide pantry quantities
- Option to enable or disable recipe suggestions

## Technologies Used

- Java
- Android Studio
- XML
- SQLite
- SharedPreferences
- Git
- GitHub

## Application Screens

The application contains the following main screens:

1. Smart Pantry / Home
2. Add or Edit Ingredient
3. Suggested Recipes
4. Recipe Details
5. Settings

## Recipe Matching

A recipe is suggested only when all of its required ingredients are available in the pantry with the required quantity and matching unit.

## Data Storage

SQLite is used to store pantry ingredients and recipe information. SharedPreferences is used to save application settings.

## Developer

Cadyn
