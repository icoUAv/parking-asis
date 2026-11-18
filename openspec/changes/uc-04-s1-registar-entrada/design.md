# Design

## Context

Slice do 1.º épico. Arquitetura e stack em docs/adr/.

## Goals / Non-Goals

**Goals:** comportamento descrito nos scenarios da spec delta.

**Non-Goals:** fluxos de outros slices.

## Decisions

- Pacotes: `servico` (AcessoService), `api` (AcessoApi).
- Integração com o leitor de matrículas por API REST; nos testes, o leitor é simulado por pedidos à API (RT-2).
- ADR aplicáveis: ADR-001, ADR-002.

## Risks / Trade-offs

Nenhum relevante para o slice.
