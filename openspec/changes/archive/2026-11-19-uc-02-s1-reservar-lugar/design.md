# Design

## Context

Slice do 1.º épico. Arquitetura e stack em docs/adr/.

## Goals / Non-Goals

**Goals:** comportamento descrito nos scenarios da spec delta.

**Non-Goals:** fluxos de outros slices.

## Decisions

- Pacotes: `dominio` (Reserva, Periodo), `servico` (ReservaService), `api` (ReservaApi), `web` (ReservaWeb).
- BR-01 validada no construtor de `Periodo` (valor sempre válido).
- Violações de regras na API: 422 com `{regra, mensagem}`.
- ADR aplicáveis: ADR-001, ADR-002.

## Risks / Trade-offs

Nenhum relevante para o slice.
