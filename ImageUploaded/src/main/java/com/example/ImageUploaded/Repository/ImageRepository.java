package com.example.ImageUploaded.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ImageUploaded.Model.Image;


public interface ImageRepository extends JpaRepository<Image, Long> {
}
