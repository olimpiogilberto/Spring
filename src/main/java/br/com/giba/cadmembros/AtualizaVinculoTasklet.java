package br.com.giba.cadmembros;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
final class AtualizaVinculoTasklet implements Tasklet {

    private static final String SEQUENCE_SETUP_SQL = """
            DECLARE @next_vinculo BIGINT;
            DECLARE @sequence_sql NVARCHAR(300);

            IF OBJECT_ID(N'dbo.Seq_vinculo_membros', N'SO') IS NULL
            BEGIN
                SELECT @next_vinculo =
                    COALESCE(MAX(CONVERT(BIGINT, nu_vinculo)), 0) + 1
                FROM dbo.vinculo_membros;

                IF @next_vinculo > 999999999
                    THROW 50001, 'Não há valor disponível para nu_vinculo.', 1;

                SET @sequence_sql =
                    N'CREATE SEQUENCE dbo.Seq_vinculo_membros AS INT START WITH '
                    + CONVERT(NVARCHAR(20), @next_vinculo)
                    + N' INCREMENT BY 1 MINVALUE 1 MAXVALUE 999999999 NO CYCLE;';

                EXEC sys.sp_executesql @sequence_sql;
            END;
            """;

    private static final String INSERT_MISSING_LINKS_SQL = """
            INSERT INTO dbo.vinculo_membros
                (nu_vinculo, nu_empresa, nu_pessoa, nu_cargo)
            SELECT
                NEXT VALUE FOR dbo.Seq_vinculo_membros,
                1,
                p.nu_pessoa,
                ?
            FROM dbo.pessoa AS p
            WHERE NOT EXISTS (
                SELECT 1
                FROM dbo.vinculo_membros AS v WITH (UPDLOCK, HOLDLOCK)
                WHERE v.nu_pessoa = p.nu_pessoa
            );
            """;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        ensureCompanyExists();
        BigDecimal cargoOperario = findOperarioCargo();
        ensureVinculoSequenceExists();

        int inserted = jdbcTemplate.update(INSERT_MISSING_LINKS_SQL, cargoOperario);
        contribution.incrementWriteCount(inserted);
        log.info("Vínculos criados para pessoas sem vínculo: {}", inserted);
        return RepeatStatus.FINISHED;
    }

    private void ensureCompanyExists() {
        Integer companyCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM dbo.empresa WHERE nu_empresa = 1",
                Integer.class);
        if (companyCount == null || companyCount != 1) {
            throw new IllegalStateException("A empresa nu_empresa = 1 não existe.");
        }
    }

    private BigDecimal findOperarioCargo() {
        List<BigDecimal> cargos = jdbcTemplate.query(
                "SELECT nu_cargo FROM dbo.cargos WHERE ds_cargo = ?",
                (resultSet, rowNumber) -> resultSet.getBigDecimal("nu_cargo"),
                "Operario");

        if (cargos.size() != 1) {
            throw new IllegalStateException(
                    "Deve existir exatamente um cargo com ds_cargos = 'Operario'.");
        }
        return cargos.get(0);
    }

    private void ensureVinculoSequenceExists() {
        jdbcTemplate.execute(SEQUENCE_SETUP_SQL);
    }
}
