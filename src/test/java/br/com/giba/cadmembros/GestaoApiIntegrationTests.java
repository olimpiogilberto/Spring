package br.com.giba.cadmembros;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GestaoApiIntegrationTests {
    @Autowired MockMvc mvc;

    @Test
    void disponibilizaDocumentacaoOpenApi() throws Exception {
        mvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.info.title").value("API de Gestão de Empresas e Trabalhadores"))
                .andExpect(jsonPath("$.paths['/api/empresas'].post.summary").value("Cadastrar empresa"))
                .andExpect(jsonPath("$.paths['/api/consultas/salario/{cpf}'].get.summary")
                        .value("Consultar salário por CPF"))
                .andExpect(jsonPath("$.components.schemas.Empresa.properties.cnpj.description")
                        .value("CNPJ válido; pode ser enviado com pontuação ou somente dígitos."));
    }

    @Test
    void gerenciaEmpresaPessoaVinculoEConsultasEAplicaErrosHttp() throws Exception {
        String empresa = """
                {"cnpj":"11.222.333/0001-81","razaoSocial":"Acme Ltda","situacao":"ATIVA",
                 "endereco":{"logradouro":"Rua Um","cidade":"São Paulo","estado":"SP","cep":"01000-000"}}
                """;
        mvc.perform(post("/api/empresas").contentType(MediaType.APPLICATION_JSON).content(empresa))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.cnpj").value("11222333000181"));
        mvc.perform(post("/api/empresas").contentType(MediaType.APPLICATION_JSON).content(empresa))
                .andExpect(status().isConflict());
        String pessoa = """
                {"cpf":"529.982.247-25","nome":"Ana Souza","dataNascimento":"1990-01-02","situacao":"ATIVA"}
                """;
        mvc.perform(post("/api/pessoas").contentType(MediaType.APPLICATION_JSON).content(pessoa))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.cpf").value("52998224725"));
        mvc.perform(post("/api/vinculos").contentType(MediaType.APPLICATION_JSON).content("""
                {"pessoaId":1,"empresaId":1,"cargo":"EMPREGADO","salario":5250.50,
                 "dataInicio":"2023-01-01","status":"ATIVO"}
                """))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.salario").value(5250.5));
        mvc.perform(get("/api/consultas/empresa-por-cpf/52998224725"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.cnpj").value("11222333000181"));
        mvc.perform(get("/api/consultas/empregados/11222333000181/contagem"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.empregadosAtivos").value(1));
        mvc.perform(get("/api/consultas/salario/52998224725"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.salario").value(5250.5));
        String faturamento = mvc.perform(post("/api/empresas/11222333000181/faturamentos").contentType(MediaType.APPLICATION_JSON)
                .content("{\"ano\":2025,\"valor\":1200000.00}"))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        mvc.perform(post("/api/empresas/11222333000181/faturamentos").contentType(MediaType.APPLICATION_JSON)
                .content("{\"ano\":2025,\"valor\":100.00}"))
                .andExpect(status().isConflict());
        Number faturamentoId = com.jayway.jsonpath.JsonPath.read(faturamento, "$.id");
        mvc.perform(delete("/api/empresas/11222333000182/faturamentos/registro/" + faturamentoId))
                .andExpect(status().isNotFound());
        mvc.perform(get("/api/consultas/faturamento/11222333000181").param("ano", "2025"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.valor").value(1200000.0));
        mvc.perform(post("/api/pessoas").contentType(MediaType.APPLICATION_JSON)
                .content("{\"cpf\":\"11111111111\",\"nome\":\"Inválido\",\"dataNascimento\":\"1990-01-01\",\"situacao\":\"ATIVA\"}"))
                .andExpect(status().isBadRequest());
        mvc.perform(get("/api/empresas/9999")).andExpect(status().isNotFound());
    }
}
