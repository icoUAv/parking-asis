# Design

## Context

Slice do 1.º épico. Arquitetura e stack em docs/adr/.

## Goals / Non-Goals

**Goals:** comportamento descrito nos scenarios da spec delta.

**Non-Goals:** fluxos de outros slices.

## Decisions

- Pacotes: `servico` (DisponibilidadeService), `api` (ParqueApi), `web` (DisponibilidadeWeb).
- Contagem de sobreposições numa consulta JPA parametrizada (`ReservaRepository.contarSobrepostas`), reutilizável pela regra de lotação (BR-02).
- ADR aplicáveis: ADR-001, ADR-002.

## Risks / Trade-offs

Nenhum relevante para o slice.
