# 📰 Fake News Detection App

A full-stack **Fake News Detection system** that uses a **Machine Learning model** as the backend and an **Android application** as the frontend. The app allows users to input news content and get real-time predictions on whether the news is **fake or real**.

---

## 🚀 Features

* 📱 Android-based user interface
* 🤖 Machine Learning model for prediction
* 🔗 REST API integration between frontend and backend
* ⚡ Real-time news classification
* 🧠 NLP-based text processing

---

## 🏗️ Project Architecture

```
Android App (Frontend)
        ↓
   REST API (HTTP Requests)
        ↓
Backend Server (Flask/FastAPI)
        ↓
Machine Learning Model (NLP Classifier)
```

---

## 🛠️ Tech Stack

### Frontend (Android)

* Java / Kotlin
* Android Studio
* Retrofit / Volley (for API calls)

### Backend

* Python
* Flask / FastAPI
* REST API

### Machine Learning

* Scikit-learn / TensorFlow
* NLP techniques (TF-IDF, Tokenization)
* Classification algorithms (Logistic Regression / PassiveAggressive / etc.)

---

## 📂 Project Structure

```
Fake-News-Detection/
│
├── android-app/        # Android frontend
├── backend/            # API server
│   ├── app.py
│   ├── model.pkl
│   └── utils.py
├── model/              # ML model training files
│   └── training.ipynb
├── dataset/            # Dataset used
├── requirements.txt
└── README.md
```

---

## ⚙️ Installation & Setup

### 1️⃣ Clone the repository

```bash
git clone https://github.com/DeepakThakur10/Fake-News-Detection.git
cd Fake-News-Detection
```

### 2️⃣ Backend Setup

```bash
cd backend
pip install -r requirements.txt
python app.py
```

### 3️⃣ Run Android App

* Open `android-app` in **Android Studio**
* Connect device/emulator
* Run the app

---

## 🔌 API Endpoints

### POST `/predict`

**Request:**

```json
{
  "text": "Enter news content here"
}
```

**Response:**

```json
{
  "prediction": "Fake"
}
```

---

## 🧪 Model Details

The machine learning model is trained on labeled datasets of real and fake news articles. It uses NLP techniques to extract features from text and classify them.

Typical fake news detection systems rely on **text-based classification using labeled datasets and ML algorithms** ([GitHub][1]).

---

## 📊 Workflow

1. User enters news text in Android app
2. App sends request via REST API
3. Backend processes input
4. ML model predicts result
5. Response sent back to app

---

## 📸 Screenshots

*(Add your app screenshots here)*

---

## 🤝 Contribution

Contributions are welcome! Feel free to fork the repo and submit a pull request.

---

## 📄 License

This project is licensed under the MIT License.

---

## 👨‍💻 Author
**Deepak Kumar Thakur**

email:dk1816859@gmail.com

**Ayush Singh**

GitHub: https://github.com/AyushSingh2901

**Nikhil Singh**

Github: https://github.com/Nikhil19singh

---

## ⭐ Acknowledgements

* Open-source ML libraries
* Public fake news datasets
* NLP research community

---

[1]: https://github.com/jicsjitu/Fake_News_Detection_Using_ML?utm_source=chatgpt.com "Fake News Detection Using Machine Learning"
