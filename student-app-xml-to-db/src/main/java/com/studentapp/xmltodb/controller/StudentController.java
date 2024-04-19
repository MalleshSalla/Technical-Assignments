package com.studentapp.xmltodb.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class StudentController {
	

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    private final String TEMP_STORAGE = "C:\\Users\\smallesh\\Documents\\Files";

    @PostMapping(path = "/importData")
    public void startBatch(@RequestParam("file") MultipartFile multipartFile) {

      try {
         String originalFileName = multipartFile.getOriginalFilename();
         File fileToImport = new File(TEMP_STORAGE + originalFileName);
         multipartFile.transferTo(fileToImport);

         JobParameters jobParameters = new JobParametersBuilder()
                  .addString("fullPathFileName", TEMP_STORAGE + originalFileName)
                  .addLong("startAt", System.currentTimeMillis()).toJobParameters();

          jobLauncher.run(job, jobParameters);
     } 
     catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException | IOException e) {
            e.printStackTrace();
     }
   }

}
