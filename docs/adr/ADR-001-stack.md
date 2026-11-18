# ADR-001 Stack da aplicação web

**Estado:** aceite

## Contexto

RT-1 (aplicação web testável com Playwright) e RT-2 (leitor de matrículas pela API). Equipa com experiência em Java.

## Decisão

- Spring Boot 4 (Java 21), com páginas Thymeleaf para o condutor e API REST para o leitor de matrículas.
- Spring Data JPA com H2 em memória (dados repostos a cada arranque).
- Testes unitários com JUnit 5; testes de aceitação com Playwright Test (TypeScript), na pasta `e2e/`.

## Alternativas consideradas

- Frontend SPA separado + API: mais uma aplicação a construir e a testar; sem ganho para o 1.º incremento.
- Playwright para Java (JUnit): uma só linguagem, mas sem o executor do Playwright Test (filtro por ID, arranque da aplicação, relatório HTML) usado nas convenções do projeto.

## Consequências

- O pipeline precisa de Java (Maven) e de Node (Playwright).
- A interface não depende de JavaScript: os seletores dos testes usam rótulos e `data-testid`.
