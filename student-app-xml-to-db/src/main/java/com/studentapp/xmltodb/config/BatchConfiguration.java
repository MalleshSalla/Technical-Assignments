package com.studentapp.xmltodb.config;

import java.io.File;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.studentapp.xmltodb.entity.Student;
import com.studentapp.xmltodb.repo.StudentRepository;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	 @Autowired
	    private JobBuilderFactory jobBuilderFactory;
	    @Autowired
	    private StepBuilderFactory stepBuilderFactory;
	    @Autowired
	    private StudentRepository studentRepository;
	   
	    
	    @Bean
	    @StepScope
	    public StaxEventItemReader<Student> itemReader(@Value("#{jobParameters[fullPathFileName]}") String pathToFIle) {
	        StaxEventItemReader<Student> xmlFileReader = new StaxEventItemReader<>();
	        xmlFileReader.setResource(new FileSystemResource(new File(pathToFIle)));
	        xmlFileReader.setFragmentRootElementName("student");
	        xmlFileReader.setUnmarshaller(studentUnmarshaller());
	        return xmlFileReader;
	    }

	    @Bean
	    public Jaxb2Marshaller studentUnmarshaller() {
	        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	        marshaller.setClassesToBeBound(Student.class);
	        return marshaller;
	    }

	    @Bean
	    public ItemProcessor<Student, Student> processor() {
	        return student -> {
	            if (student.getMarks() > 70) {
	                return student; // Process the student
	            } else {
	                return null; // Skip this students
	            }
	        };
	    }
	    
//	    @Bean
//	    public ItemWriter<Student> writer() {
//	        return list -> {
//	            System.out.println("Writer Thread " + Thread.currentThread().getName());
//	            studentRepository.saveAll(list);
//	        };
//	    }

	    @Bean
	    public RepositoryItemWriter<Student> writer() {
	        RepositoryItemWriter<Student> writer = new RepositoryItemWriter<>();
	        writer.setRepository(studentRepository);
	        writer.setMethodName("save");
	        return writer;
	    }


	    @Bean
	    public Step step1(StaxEventItemReader<Student> itemReader) {
	        return stepBuilderFactory.get("slaveStep").<Student, Student>chunk(10)
	                .reader(itemReader)
	                .processor(processor())
	                .writer(writer())
	                .build();
	    }

	    @Bean
	    public Job runJob(StaxEventItemReader<Student> itemReader) {
	        return jobBuilderFactory.get("importCustomer").flow(step1(itemReader)).end().build();
	    }

}
