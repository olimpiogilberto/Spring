package br.gov.caixa.sidce.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JobScheduler implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job jobRegistroData;

    public JobScheduler(JobLauncher jobLauncher, @Qualifier("jobRegistroData") Job job) {
        this.jobLauncher = jobLauncher;
        this.jobRegistroData = job;
    }

    @Override
    public void run(String... args) throws Exception {
        jobLauncher.run(
        		jobRegistroData,
            new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis()) // garante que seja um job novo
                .toJobParameters()
        );
    }
}