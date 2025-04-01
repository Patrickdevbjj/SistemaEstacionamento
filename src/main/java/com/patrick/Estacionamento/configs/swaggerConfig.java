package com.patrick.Estacionamento.configs;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class swaggerConfig {

    @Bean
    public GroupedOpenApi veiculosApi() {
        return GroupedOpenApi.builder()
                .group("veiculos")
                .pathsToMatch("/veiculos/**")
                .addOpenApiCustomizer(openApi -> {
                    // Configuração do schema de resposta
                    Schema<Map<String, Object>> itemSchema = new Schema<Map<String, Object>>()
                            .addProperty("placa", new StringSchema().example("ABC1234"))
                            .addProperty("ultimaEntrada", new StringSchema().example("2024-05-20T10:30:00"));

                    // Configuração do MediaType
                    MediaType mediaType = new MediaType();
                    mediaType.setSchema(new ArraySchema().items(itemSchema));
                    mediaType.setExample("""
                            [
                              {
                                "placa": "ABC1234",
                                "ultimaEntrada": "2024-05-20T10:30:00"
                              },
                              {
                                "placa": "XYZ5678",
                                "ultimaEntrada": "Nunca entrou"
                              }
                            ]
                            """);

                    // Configuração da operação
                    Operation operation = new Operation()
                            .summary("Listar todos os veículos")
                            .description("Retorna a lista de veículos com suas placas e última entrada")
                            .responses(new ApiResponses()
                                    .addApiResponse("200", new ApiResponse()
                                            .description("Lista de veículos encontrada")
                                            .content(new Content()
                                                    .addMediaType("application/json", mediaType))));

                    openApi.path("/veiculos", new PathItem().get(operation));
                })
                .build();
    }

    @Bean
    public GroupedOpenApi entradaApi() {
        return GroupedOpenApi.builder()
                .group("entrada")
                .pathsToMatch("/entrada/**")
                .addOpenApiCustomizer(openApi -> {

                })
                .build();
    }

    @Bean
    public GroupedOpenApi saidaApi() {
        return GroupedOpenApi.builder()
                .group("saidas")
                .pathsToMatch("/saida/**")
                .addOpenApiCustomizer(openApi -> {
                    // 1. Configuração do schema de resposta
                    Schema<?> saidaSchema = new Schema<>()
                            .type("object")
                            .addProperty("placa", new StringSchema()
                                    .example("ABC-1234")
                                    .description("Placa do veículo"))
                            .addProperty("horaSaida", new StringSchema()
                                    .example("2024-05-20T15:30:00")
                                    .description("Data e hora da saída"));

                    // 2. Configuração da resposta
                    ApiResponses responses = new ApiResponses()
                            .addApiResponse("200", new ApiResponse()
                                    .description("Saída registrada com sucesso")
                                    .content(new Content()
                                            .addMediaType("application/json",
                                                    new MediaType()
                                                            .schema(saidaSchema)
                                                            .example("""
                                    {
                                      "placa": "ABC-1234",
                                      "horaSaida": "2024-05-20T15:30:00"
                                    }
                                    """))))
                            .addApiResponse("400", new ApiResponse()
                                    .description("Veículo não encontrado ou já saiu")
                                    .content(new Content()
                                            .addMediaType("text/plain",
                                                    new MediaType()
                                                            .example("Veículo não encontrado no estacionamento"))))
                            .addApiResponse("500", new ApiResponse()
                                    .description("Erro interno no servidor")
                                    .content(new Content()
                                            .addMediaType("text/plain",
                                                    new MediaType()
                                                            .example("Erro ao registrar saída: [mensagem de erro]"))));

                    // 3. Configuração da operação
                    Operation operation = new Operation()
                            .operationId("registrarSaida")
                            .summary("Registrar saída de veículo")
                            .description("Endpoint para registrar a saída de um veículo do estacionamento")
                            .responses(responses)
                            .addTagsItem("Saída de Veículos");

                    openApi.path("/saida", new PathItem().post(operation));
                })
                .build();
    }

    // 3. Outros endpoints
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("estacionamento-public")
                .pathsToMatch("/api/**")
                .build();
    }
}



    // ... outros grupos (saidaApi, comprovanteApi, etc)


