package com.example.contract_checker.service;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OCRService {
    private static final String PYTHON_OCR_URL = "http://localhost:5001/ocr";

    public String extractTextFromImage(MultipartFile file) {
        try {
            // 파일을 byte 배열로 변환
            byte[] bytes = file.getBytes();

            // Flask로 보낼 HTTP 요청을 만들기 위한 HttpEntity
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // RestTemplate 인스턴스 생성
            RestTemplate restTemplate = new RestTemplate();

            // MultipartBody로 파일 추가
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new ByteArrayResource(bytes) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            });

            // HttpEntity 생성
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            // RestTemplate을 사용하여 Flask로 POST 요청 보내기
            ResponseEntity<String> response = restTemplate.exchange(PYTHON_OCR_URL, HttpMethod.POST, requestEntity, String.class);

            // Flask에서 반환된 OCR 텍스트 추출
            String extractedText = response.getBody();
            
            return extractedText;
        } catch (Exception e) {
            return "Error communicating with Python OCR server: " + e.getMessage();
        }
    }
}