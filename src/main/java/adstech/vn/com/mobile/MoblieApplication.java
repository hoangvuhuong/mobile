package adstech.vn.com.mobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import adstech.vn.com.mobile.util.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class MoblieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoblieApplication.class, args);
	}

}
