package com.utkarsh.claimsight.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class ImageComparisonService {

    public double compare(MultipartFile before, MultipartFile after) throws IOException {
        BufferedImage img1 = ImageIO.read(before.getInputStream());
        BufferedImage img2 = ImageIO.read(after.getInputStream());

        int width = Math.min(img1.getWidth(), img2.getWidth());
        int height = Math.min(img1.getHeight(), img2.getHeight());

        int totalPixels = width * height;
        int damagedPixels = 0;
        int threshold = 65;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = img1.getRGB(x, y);
                int rgb2 = img2.getRGB(x, y);

                int r1 = (rgb1 >> 16) & 0xff;
                int g1 = (rgb1 >> 8) & 0xff;
                int b1 = rgb1 & 0xff;

                int r2 = (rgb2 >> 16) & 0xff;
                int g2 = (rgb2 >> 8) & 0xff;
                int b2 = rgb2 & 0xff;

                double brightness1 = 0.3 * r1 + 0.59 * g1 + 0.11 * b1;
                double brightness2 = 0.3 * r2 + 0.59 * g2 + 0.11 * b2;

                if (Math.abs(brightness1 - brightness2) > threshold) {
                    damagedPixels++;
                }
            }
        }

        double damagePercentage = ((double) damagedPixels / totalPixels) * 100.0;
        return Math.min(100.0, damagePercentage);
    }
    public double calculateClaimAmount(double damagePercent, double purchasePrice) {
        double recoveryRate;

        if (damagePercent <= 10) recoveryRate = 0.90;
        else if (damagePercent <= 25) recoveryRate = 0.60;
        else if (damagePercent <= 50) recoveryRate = 0.40;
        else if (damagePercent <= 75) recoveryRate = 0.20;
        else recoveryRate = 0.0;

        return purchasePrice * recoveryRate;
    }

}
