package br.gov.caixa.sidce.demo;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    Step stepRegistroData(JobRepository jobRepository, PlatformTransactionManager transactionManager, RegistroDataTasklet registroDataTasklet) {
    			return new StepBuilder("stepRegistroData", jobRepository)
    			.tasklet(registroDataTasklet, transactionManager)
    			.build();
    			}


    @Bean
    Job jobRegistroData(JobRepository jobRepository, Step stepRegistroData) {
       return new JobBuilder("jobRegistroData", jobRepository)
               .start(stepRegistroData)
               .build();
   }


}
