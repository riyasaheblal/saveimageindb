package com.example.ImageUploaded.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ImageUploaded.Model.Image;
import com.example.ImageUploaded.Service.ImageService;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/")
    public String index(Model model) {
        List<Image> images = imageService.getAllImages();
        model.addAttribute("images", images);
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile file, Model model) {
        try {
            imageService.saveImage(file);
            model.addAttribute("message", "Image uploaded successfully!");
        } catch (IOException e) {
            model.addAttribute("message", "Failed to upload image!");
        }
        return "redirect:/"; // Use redirect to avoid form resubmission issues
    }
    
    @GetMapping("/image/{id}")
    public void getImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Image image = imageService.getImageById(id);
        if (image != null) {
            response.setContentType("image/jpeg"); // Adjust MIME type as needed
            response.getOutputStream().write(image.getContent());
        }
    }
}
