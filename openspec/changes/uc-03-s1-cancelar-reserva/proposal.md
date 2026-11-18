# Proposal

## Why

O condutor tem de poder libertar um lugar que já não vai usar (UC-03, slice S1, 1.º épico).

## What Changes

- Cancelamento de uma reserva ativa; recusa para reservas não ativas (BR-03).
- Endpoint `POST /api/reservas/{id}/cancelamento`.

## Capabilities

### New Capabilities
<!-- nenhuma -->

### Modified Capabilities
- `reservas`: novo requirement UC-03

## Impact

Código em `src/main/java`, testes em `src/test/java` e `e2e/tests`.
