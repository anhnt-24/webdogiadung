package com.example.webdogiadung.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FireBaseConfiguration {
    @Bean
    public FirebaseApp firebaseApp() throws IOException, IOException {
        if(FirebaseApp.getApps().isEmpty()) {
            FileInputStream serviceAccount = new FileInputStream(new ClassPathResource("./ecomerce-40e32-firebase-adminsdk-fbsvc-40ae5eac96.json").getFile());
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            return FirebaseApp.initializeApp(options);
        }
       return FirebaseApp.getInstance();

    }
}
