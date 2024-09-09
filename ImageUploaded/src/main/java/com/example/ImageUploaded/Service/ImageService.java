package com.example.ImageUploaded.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ImageUploaded.Model.Image;
import com.example.ImageUploaded.Repository.ImageRepository;

import java.io.IOException;
import java.util.List;



@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void saveImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setContent(file.getBytes());
        imageRepository.save(image);
    }
    
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}

