package be.thomasmore.screeninfo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:google.properties")
public class GoogleService {
    @Autowired
    private ResourceLoader resourceLoader;
    @Value("${firebase.party.json}")
    private String jsonFile;
    @Value("${firebase.bucket.images}")
    private String imageBucket;

}

