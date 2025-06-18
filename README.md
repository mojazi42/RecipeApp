# RecipeApp

RecipeApp is an Android application developed with **Jetpack Compose**, designed to browse and search food recipes using the **Food2Fork API**. It features a paginated recipe list, keyword-based search, and detailed views for each recipe. Built as part of a **technical interview project**, the app follows clean architecture principles using **MVVM**, **Retrofit**, and **StateFlow**.


---


## Features

| Feature                   | Description                                                                 |
|---------------------------|-----------------------------------------------------------------------------|
| **Search Recipes**         | Search for recipes by name using the Food2Fork API.                        |
| **Recipe Details**         | View full details of a selected recipe including ingredients and source URL.|
| **Pagination Support**     | Supports loading additional recipes across multiple pages.                 |
| **Jetpack Compose UI**     | Built with a modern declarative UI approach using Jetpack Compose.         |
| **Image Loading with Coil**| Efficient image loading and caching using the Coil library.                |
| **Error Handling**         | Graceful handling of network errors and edge cases with proper UI feedback.|
| **MVVM Architecture**      | Clean architecture with ViewModel, Repository, and StateFlow.              |
| **Reactive State Management** | Uses Kotlin Coroutines and StateFlow to manage UI state.                |


---

## Screenshots







| Home Screen | Search Results | Recipe Detail |
|-------------|----------------|----------------|
| ![Screenshot_20250618_045452_Recipe App](https://github.com/user-attachments/assets/6b5a97ef-6d74-4816-bc6b-a4b4f1428904) | ![Screenshot_20250618_043527_Recipe App](https://github.com/user-attachments/assets/7ee5d403-ad87-43c9-b8b3-77717feb38f3) | ![Screenshot_20250618_050208_Recipe App](https://github.com/user-attachments/assets/c6222b33-9991-4203-8198-eb0e52dda7d7)

| Displays a list of recipes with pagination support. | Shows filtered recipes based on user input. | Displays complete recipe details including image, ingredients, and source link. |

---

## Tech Stack

| Layer             | Tools / Libraries                          |
|------------------|--------------------------------------------|
| **Language**      | Kotlin                                     |
| **UI**            | Jetpack Compose                            |
| **Architecture**  | MVVM, StateFlow                            |
| **Networking**    | Retrofit, OkHttp                           |
| **Image Loading** | Coil                                       |
| **Async Handling**| Kotlin Coroutines                          |
| **API**           | [Food2Fork API](https://www.food2fork.ca/) |
| **Error Handling**| Sealed `Resource<T>` wrapper               |

---

