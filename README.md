# Estacionamento — exemplo condutor de ASis

Reservas de lugares em parques cobertos. Repositório de demonstração da aula sobre o ciclo DevOps: teste contínuo, integração contínua e entrega contínua, com especificações OpenSpec.

## Correr localmente

Requisitos: Java 21, Maven 3.9+, Node 22.

```bash
mvn spring-boot:run            # aplicação em http://localhost:8080
mvn test                       # testes unitários (JUnit)

cd e2e
npm ci
npx playwright install chromium
npx playwright test            # arranca a aplicação (perfil e2e) e corre os testes de aceitação
npx playwright test --grep "CT-02"   # só os testes de UC-02
npx playwright show-report
```

## Estrutura

| Pasta | Conteúdo |
|---|---|
| `docs/srs/` | SRS resumida: casos de utilização, slices, regras (BR-nn) e casos de teste (CT-nn.k) |
| `docs/adr/` | Decisões de arquitetura (ADR-001 stack, ADR-002 estratégia de teste) |
| `openspec/specs/` | Comportamento atual do sistema, por capability |
| `openspec/changes/` | Changes em curso; `archive/` guarda as já integradas |
| `src/` | Aplicação Spring Boot e testes unitários |
| `e2e/` | Testes de aceitação com Playwright (API e interface) |
| `.github/` | Pipeline, template de pull request e ruleset do ramo `main` |

## Rastreabilidade

Caso de teste (SRS) → scenario (OpenSpec) → teste (título com o ID) → resultado no pipeline.

Exemplo: `CT-02.2` em `docs/srs/` → `#### Scenario: CT-02.2 …` em `openspec/specs/reservas/spec.md` → `test('CT-02.2 …')` em `e2e/tests/api/reservar.spec.ts`.

## Fluxo de trabalho (GitHub Flow)

1. Um ramo por change, com o nome da change (ex.: `uc-02-s2-reserva-sem-lugar`).
2. Testes primeiro; depois a implementação.
3. Pull request para `main`: o pipeline tem de passar, sem alertas da análise estática, e um colega aprova.
4. Depois da aprovação, `/opsx:archive` como último commit do ramo; depois o merge.
5. Cada merge em `main` publica a imagem no GHCR; cada tag `v*` cria um GitHub Release.

## Ferramentas

Agente e modelo: (a registar pelo grupo). OpenSpec 2.2.0. Playwright 1.65.1. Spring Boot 4.1.3.
