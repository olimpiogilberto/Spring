package br.gov.caixa.sidce.demo;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RegistroDataTasklet implements Tasklet {
	
	private final JdbcTemplate jdbcTemplate;

    public RegistroDataTasklet(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        jdbcTemplate.update("INSERT INTO [dbo].[RegistroData] ([DataRegistro])    VALUES    (GETDATE())");
        return RepeatStatus.FINISHED;
    }
}
