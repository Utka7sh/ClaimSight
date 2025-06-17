package com.utkarsh.claimsight;

import org.opencv.core.Core;

public class OpenCVTest {
    public static void main(String[] args) {
        System.load("C:\\Users\\utkar\\Desktop\\Utkarsh\\Projects\\ClaimSight\\OpenCV\\opencv\\build\\java\\x64\\opencv_java4110.dll");
        System.out.println("OpenCV Loaded Successfully!");

        // Optional: check version
        System.out.println("OpenCV Version: " + Core.VERSION);
    }
}
