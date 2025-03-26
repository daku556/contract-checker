package com.example.contract_checker.controller;
import com.example.contract_checker.service.OCRService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

@Controller
public class OCRController {
    private final OCRService ocrService;

    public OCRController(OCRService ocrService) {
        this.ocrService = ocrService;
    }

    @GetMapping("/ocr/upload")
    public String uploadPage() {
        return "upload";  // resources/templates/upload.html로 찾음
    }

    @PostMapping("/ocr/process")
    public String uploadAndExtractText(@RequestParam("file") MultipartFile file, Model model) {
        String extractedText = ocrService.extractTextFromImage(file);
        model.addAttribute("ocrResult", extractedText);
        return "uploadResult";
    }
}
