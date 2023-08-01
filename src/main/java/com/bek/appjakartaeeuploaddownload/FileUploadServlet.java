package com.bek.appjakartaeeuploaddownload;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@WebServlet("/upload")
@MultipartConfig
public class FileUploadServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Part filePart = request.getPart("file");
            String fileName = UUID.randomUUID().toString() + "-" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = "C:/uploads/"; // Set your desired upload directory here

            File file = new File(uploadPath + fileName);
            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            request.setAttribute("message", "File uploaded successfully!");
        } catch (Exception ex) {
            request.setAttribute("message", "File upload failed: " + ex.getMessage());
        }

        request.getRequestDispatcher("/gallery.jsp").forward(request, response);
    }
}
