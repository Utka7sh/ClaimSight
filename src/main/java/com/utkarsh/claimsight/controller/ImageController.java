package com.utkarsh.claimsight.controller;

import com.utkarsh.claimsight.service.ImageComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageComparisonService service;

    @PostMapping("/compare")
    public ResponseEntity<String> compareImages(@RequestParam("before") MultipartFile before,
                                                @RequestParam("after") MultipartFile after,
                                                @RequestParam("purchasePrice") double purchasePrice) {
        try {
            double damage = service.compare(before, after);
            double claimableAmount = service.calculateClaimAmount(damage, purchasePrice);

            String result = String.format("Estimated Damage: %.2f%%\nClaimable Amount: ₹%.2f", damage, claimableAmount);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error comparing images: " + e.getMessage());
        }
    }
}