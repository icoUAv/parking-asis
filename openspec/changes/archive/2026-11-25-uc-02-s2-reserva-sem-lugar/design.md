# Design

## Context

Altera o UC-02 Reservar lugar, já implementado no 1.º incremento (UC-02.S1). A contagem de reservas sobrepostas já existe para o UC-01 (`ReservaRepository.contarSobrepostas`).

## Goals / Non-Goals

**Goals:** recusar reservas sem lugar (BR-02), com a fronteira do fim exclusivo.

**Non-Goals:** reservas concorrentes no mesmo instante (dois pedidos simultâneos para o último lugar); listas de espera.

## Decisions

- Pacotes: `dominio` (nova classe `Lotacao` com a regra BR-02), `servico` (ReservaService).
- Validar o período (BR-01) antes de qualquer consulta; contar as sobreposições com a consulta existente `ReservaRepository.contarSobrepostas`, sem SQL novo.
- A recusa é uma `RegraNegocioException("BR-02", …)`: a API devolve 422 e a página mostra a mensagem, com o tratamento já existente.
- ADR aplicáveis: ADR-001, ADR-002.

## Risks / Trade-offs

- Concorrência: dois pedidos simultâneos podem ocupar o último lugar duas vezes. Aceite neste incremento (fora do âmbito).
