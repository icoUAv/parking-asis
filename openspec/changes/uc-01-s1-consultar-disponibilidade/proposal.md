# Proposal

## Why

O condutor precisa de saber se há lugar antes de reservar (UC-01, slice S1, 1.º épico).

## What Changes

- Consulta, por parque e período, do número de lugares livres (lotação menos reservas ativas sobrepostas).
- Página web de consulta e endpoint `GET /api/parques/{id}/disponibilidade`.

## Capabilities

### New Capabilities
- `disponibilidade`: Consulta dos lugares livres de um parque num período, para o condutor decidir onde reservar.

### Modified Capabilities
<!-- nenhuma -->

## Impact

Código em `src/main/java`, testes em `src/test/java` e `e2e/tests`.
