# Design

## Context

Slice do 1.º épico. Arquitetura e stack em docs/adr/.

## Goals / Non-Goals

**Goals:** comportamento descrito nos scenarios da spec delta.

**Non-Goals:** fluxos de outros slices.

## Decisions

- Pacotes: `dominio` (Reserva.cancelar), `servico` (CancelamentoService), `api` (CancelamentoApi).
- BR-03 verificada na própria entidade.
- ADR aplicáveis: ADR-002.

## Risks / Trade-offs

Nenhum relevante para o slice.
