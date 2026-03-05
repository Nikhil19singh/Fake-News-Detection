from fastapi import FastAPI
from pydantic import BaseModel
import joblib
import re
import string

# ==========================
# LOAD SAVED MODEL & VECTORIZER
# ==========================
import os

BASE_DIR = os.path.dirname(os.path.abspath(__file__))

model_path = os.path.join(BASE_DIR, "fake_news_model.pkl")
vectorizer_path = os.path.join(BASE_DIR, "tfidf_vectorizer.pkl")

model = joblib.load(model_path)
vectorizer = joblib.load(vectorizer_path)

# ==========================
# FASTAPI APP
# ==========================
app = FastAPI(title="Fake News Detection API")

# ==========================
# REQUEST FORMAT
# ==========================
class NewsRequest(BaseModel):
    text: str

# ==========================
# TEXT CLEANING (SAME AS NOTEBOOK)
# ==========================
def clean_text(text):
    text = text.lower()
    text = re.sub(r'\[.*?\]', '', text)
    text = re.sub(r'\W', ' ', text)
    text = re.sub(r'https?://\S+|www\.\S+', '', text)
    text = re.sub(r'<.*?>+', '', text)
    text = re.sub(r'[%s]' % re.escape(string.punctuation), '', text)
    text = re.sub(r'\n', '', text)
    text = re.sub(r'\w*\d\w*', '', text)
    return text

# ==========================
# HOME ROUTE
# ==========================
@app.get("/")
def home():
    return {"message": "Fake News Detection API is running ✅"}

# ==========================
# PREDICTION ROUTE
# ==========================
@app.post("/predict")
def predict_news(request: NewsRequest):
    cleaned_text = clean_text(request.text)
    vector = vectorizer.transform([cleaned_text])

    prediction = model.predict(vector)[0]
    probability = model.predict_proba(vector).max()

    result = "REAL NEWS ✅" if prediction == 1 else "FAKE NEWS ❌"

    return {
        "input_text": request.text,
        "prediction": result,
        "confidence": round(float(probability), 4)
    }
