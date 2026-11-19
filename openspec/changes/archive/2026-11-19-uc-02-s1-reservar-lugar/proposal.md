# Proposal

## Why

Reservar um lugar é o objetivo principal do condutor (UC-02, slice S1, 1.º épico). Neste slice assume-se que há lugar; a lotação (BR-02) fica para o UC-02.S2.

## What Changes

- Registo de uma reserva ativa para matrícula, parque e período, com validação do período (BR-01).
- Formulário web e endpoint `POST /api/reservas`.

## Capabilities

### New Capabilities
- `reservas`: Registo e cancelamento de reservas de lugares, por matrícula, parque e período.

### Modified Capabilities
<!-- nenhuma -->

## Impact

Código em `src/main/java`, testes em `src/test/java` e `e2e/tests`.
