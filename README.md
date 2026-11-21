# Lezzat — Local Food Delivery Android Application

**Lezzat** is a native Android food delivery application built in **Java** using **MVVM architecture** and **Firebase** (`FirebaseAuth` & `Firebase Realtime Database`). It connects local restaurants with nearby diners through fast dish discovery, multi-criteria filtering, and real-time shopping cart calculations.

---

## 📱 App Screenshots

| Onboarding (`IntroActivity`) | Authentication (`LoginActivity`) | Home Dashboard (`MainActivity`) | Cart & Checkout (`CartActivity`) |
| :---: | :---: | :---: | :---: |
| <img src="https://res.cloudinary.com/dqbv45r78/image/upload/v1795302071/intro_z7fhnz.png" width="200" alt="Intro Screen"/> | <img src="https://res.cloudinary.com/dqbv45r78/image/upload/v1795302175/sign_up_kgb1pw.png" width="200" alt="Login Screen"/> | <img src="https://res.cloudinary.com/dqbv45r78/image/upload/v1795302070/homepage_jsrqz5.png" width="200" alt="Home Dashboard"/> | <img src="https://res.cloudinary.com/dqbv45r78/image/upload/v1795302070/cart_ixzrom.png" width="200" alt="Cart Checkout"/> |

---

## ✨ Key Features

- **Firebase Authentication:** Email & password sign-up, login, and session sign-out via `FirebaseAuth`.
- **Home Discovery Dashboard:** Horizontal **Today's Best Foods** carousel (`RecyclerView`) and a 4-column **Category Grid** (`Pizza`, `Burger`, `Chicken`, `Sushi`, etc.).
- **Real-Time Search & Multi-Criteria Filtering:** Instant keyword search by dish title plus top-level filter spinners for **Location**, **Delivery Time**, and **Price Range**.
- **Interactive Shopping Cart:** Increment/decrement item quantities with automatic calculation of **Subtotal**, **2% Tax**, **Delivery Fee**, and **Grand Total**, plus promo code input.
- **Lifecycle-Safe MVVM Architecture:** Clean separation across `Activity` (View + View Binding), `MyViewModel` (`ViewModel` + `LiveData`), and `Repository` (Firebase queries).

---

## 🏗️ Project Architecture & Structure

```text
app/src/main/java/com/bahmanexample/lezzat/
├── Activity/
│   ├── IntroActivity.java       # Onboarding & entry navigation
│   ├── LoginActivity.java       # User authentication
│   ├── SignupActivity.java      # New user registration
│   ├── MainActivity.java        # Dashboard, spinners, search, best foods & categories
│   ├── ListFoodActivity.java    # Filtered & searched dish listings
│   ├── DetailActivity.java      # Dish details & add-to-cart
│   └── CartActivity.java        # Cart items, quantity controls & order summary
├── Adapter/
│   ├── BestFoodsAdapter.java    # Horizontal featured foods carousel adapter
│   ├── CategoryAdapter.java     # 4-column category grid adapter
│   └── CartAdapter.java         # Cart item list with +/- quantity callbacks
├── Domain/
│   ├── Foods.java               # Dish model (Title, Price, Time, Rating, CategoryId, BestFood)
│   ├── Category.java            # Food category model
│   ├── Location.java            # Delivery location filter model
│   ├── Time.java                # Estimated delivery time filter model
│   └── Price.java               # Price range filter model
├── Helper/
│   └── ManagmentCart.java       # Local cart persistence & fee aggregation
├── Repository/
│   └── Repository.java          # Centralized FirebaseAuth & Realtime Database queries
└── viewModel/
    └── MyViewModel.java         # Lifecycle-aware LiveData streams for UI