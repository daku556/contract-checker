package com.example.contract_checker.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }

    // @PostMapping("/upload-image")
    // public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
    //     if (file.isEmpty()) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    //                 .body("No file uploaded.");
    //     }

    //     try {
    //         // 이미지 파일인지 확인
    //         BufferedImage img = ImageIO.read(file.getInputStream());

    //         if (img == null) {
    //             return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    //                     .body("Uploaded file is not a valid image.");
    //         }

    //         return ResponseEntity.ok("Image uploaded successfully: " + file.getOriginalFilename());
    //     } catch (IOException e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body("Error processing the image.");
    //     }
    // }

    // @GetMapping("/upload")
    // public String uploadPage() {
    //     return "upload"; // upload.html 템플릿을 반환
    // }

}
