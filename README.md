# 🍽️ RecipeApp

RecipeApp is a modern Android application developed with **Jetpack Compose**, designed to browse and search food recipes using the **Food2Fork API**. It features a paginated recipe list, keyword-based search, and detailed views for each recipe. Built as part of a **technical interview project**, the app follows clean architecture principles using **MVVM**, **Retrofit**, and **StateFlow**.

---

## 🎥 Demo

Watch the full app demo here:  
📺 [YouTube Demo Video](https://www.youtube.com/watch?v=your_demo_video_link_here)

> Demonstrates searching, pagination, and navigating to detailed recipe information.

---

## 📸 Screenshots







| Home Screen | Search Results | Recipe Detail |
|-------------|----------------|----------------|
| ![Screenshot_20250618_045452_Recipe App](https://github.com/user-attachments/assets/6b5a97ef-6d74-4816-bc6b-a4b4f1428904) | ![Screenshot_20250618_043527_Recipe App](https://github.com/user-attachments/assets/7ee5d403-ad87-43c9-b8b3-77717feb38f3) | ![Screenshot_20250618_050208_Recipe App](https://github.com/user-attachments/assets/c6222b33-9991-4203-8198-eb0e52dda7d7)

| Displays a list of recipes with pagination support. | Shows filtered recipes based on user input. | Displays complete recipe details including image, ingredients, and source link. |

---

## 🧰 Tech Stack

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

## 🛠 Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/RecipeApp.git
