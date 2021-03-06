package org.sid.stage;

import org.sid.stage.storage.StorageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class StageApplication {
    @Resource
StorageService storageService;
    public static void main(String[] args) {
        SpringApplication.run(StageApplication.class, args);
    }
    public void run(String... arg) throws Exception {
        storageService.deleteAll();
        storageService.init();
    }

}
