from flask import Flask, request, jsonify
import pytesseract
from PIL import Image
import io

app = Flask(__name__)

# Tesseract 실행 경로 설정 (Windows)
pytesseract.pytesseract.tesseract_cmd = r"C:\Users\samsung\AppData\Local\Programs\Tesseract-OCR\tesseract.exe"

@app.route("/ocr", methods=["POST"])
def ocr():
    if "file" not in request.files:
        return jsonify({"error": "No file provided"}), 400

    file = request.files["file"]
    
    try:
        # 파일을 PIL 이미지로 변환
        image = Image.open(io.BytesIO(file.read()))

        # OCR 실행 (언어 설정 가능: "eng", "kor" 등)
        extracted_text = pytesseract.image_to_string(image, lang="eng")

        return jsonify({"text": extracted_text})
    
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5001, debug=True)