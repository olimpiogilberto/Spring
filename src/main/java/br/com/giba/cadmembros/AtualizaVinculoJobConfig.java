package br.com.giba.cadmembros;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class AtualizaVinculoJobConfig {

    @Bean
    Job atualizaVinculoJob(JobRepository jobRepository, Step atualizaVinculoStep) {
        return new JobBuilder("atualiza_vinculo", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(atualizaVinculoStep)
                .build();
    }

    @Bean
    Step atualizaVinculoStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            DataSource dataSource) {
        Tasklet tasklet = new AtualizaVinculoTasklet(new JdbcTemplate(dataSource));
        return new StepBuilder("gera_vinculos_sem_empresa", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }
}
