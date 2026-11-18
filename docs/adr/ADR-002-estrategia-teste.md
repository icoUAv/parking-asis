# ADR-002 Estratégia de teste

**Estado:** aceite

## Contexto

Cada caso de teste da SRS (CT-nn.k) tem de ter um teste automático, rastreável até ao scenario do OpenSpec. Parte do código é gerada por um agente: os testes são a verificação independente do que foi pedido.

## Decisão

| Nível | Ferramenta | O que verifica | Onde |
|---|---|---|---|
| Unitário | JUnit 5 | Regras de negócio com valores de fronteira (BR-01, BR-02) | `src/test/java` |
| API | Playwright Test (`request`) | Casos de teste sem interface ou com fronteiras | `e2e/tests/api` |
| Interface | Playwright Test (`page`) | Fluxos do condutor, de ponta a ponta | `e2e/tests/ui` |

- O título de cada teste Playwright começa pelo ID do caso de teste; os testes unitários indicam a regra (BR-nn) no `@DisplayName`.
- Os dados são repostos antes de cada teste (`POST /api/testes/reset`, só no perfil `e2e`).
- O leitor de matrículas é simulado pelos testes de API.
- Testes escritos antes do código; todos correm no pipeline em cada pull request.

## Consequências

- A maior parte dos testes está no topo da pirâmide (aceitação), por causa da rastreabilidade aos casos de teste. Os testes unitários cobrem as regras com fronteiras, onde são mais rápidos e mais precisos a apontar a causa de uma falha.
