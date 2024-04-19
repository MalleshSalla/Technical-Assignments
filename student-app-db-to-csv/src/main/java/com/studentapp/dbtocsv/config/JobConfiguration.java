package com.studentapp.dbtocsv.config;


import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.studentapp.dbtocsv.model.Student;




@Configuration
@EnableBatchProcessing
public class JobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<Student> reader() {
        JdbcCursorItemReader<Student> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT id, name, marks, age FROM student_details");
        reader.setRowMapper(new BeanPropertyRowMapper<>(Student.class));
        return reader;
    }

    @Bean
    public ItemProcessor<Student, Student> processor() {
        return student -> {
            if (student.getMarks() > 75) {
                return student; // Process the student
            } else {
                return null; // Skip this students
            }
        };
    }


    @Bean
    public FlatFileItemWriter<Student> writer() {
        FlatFileItemWriter<Student> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("students.csv"));
        
        // Specify the header line
        writer.setHeaderCallback(writers -> writers.write("Id,Name,Marks,Age"));
        
        writer.setLineAggregator(new DelimitedLineAggregator<Student>() {{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<Student>() {{
                setNames(new String[]{"id", "name", "marks", "age"});
            }});
        }});
        return writer;
    }

  
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Student, Student>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job exportStudentJob() {
        return jobBuilderFactory.get("exportStudentJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }
}




















//package com.studentapp.dbtocsv.config;
//
//
//import javax.sql.DataSource;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.database.JdbcCursorItemReader;
//import org.springframework.batch.item.file.FlatFileItemWriter;
//import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
//import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//
//import com.studentapp.dbtocsv.model.Student;
//
//
//
//
//@Configuration
//@EnableBatchProcessing
//public class JobConfiguration {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public JdbcCursorItemReader<Student> reader() {
//    	
//        JdbcCursorItemReader<Student> reader = new JdbcCursorItemReader<>();
//        reader.setDataSource(dataSource);
//        reader.setSql("SELECT id, name, marks, age FROM student_details");
//        reader.setRowMapper(new BeanPropertyRowMapper<>(Student.class));
//        return reader;
//    }
//
//    @Bean
//    public ItemProcessor<Student, Student> processor() {
//        return student -> {
//            if (student.getMarks() > 75) {
//                return student; // Process the student
//            } else {
//                return null; // Skip this students
//            }
//        };
//    }
//
//
//    @Bean
//    public FlatFileItemWriter<Student> writer() {
//        FlatFileItemWriter<Student> writer = new FlatFileItemWriter<>();
//        writer.setResource(new ClassPathResource("students.csv"));
//        
//        // Specify the header line
//        writer.setHeaderCallback(writers -> writers.write("Id,Name,Marks,Age"));
//        
//        writer.setLineAggregator(new DelimitedLineAggregator<Student>() {{
//            setDelimiter(",");
//            setFieldExtractor(new BeanWrapperFieldExtractor<Student>() {{
//                setNames(new String[]{"id", "name", "marks", "age"});
//            }});
//        }});
//        return writer;
//    }
//
//  
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .<Student, Student>chunk(10)
//                .reader(reader())
//                .processor(processor())
//                .writer(writer())
//                .build();
//    }
//
//    @Bean
//    public Job exportStudentJob() {
//        return jobBuilderFactory.get("exportStudentJob")
//                .incrementer(new RunIdIncrementer())
//                .flow(step1())
//                .end()
//                .build();
//    }
//}
