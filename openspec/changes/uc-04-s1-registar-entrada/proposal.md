# Proposal

## Why

A cancela só deve abrir para quem reservou (UC-04, slice S1, 1.º épico). O leitor precisa sempre de uma resposta, por isso o slice inclui a recusa (E1).

## What Changes

- Endpoint `POST /api/acessos/entradas`, chamado pelo leitor de matrículas, que responde AUTORIZADA ou RECUSADA (BR-04).

## Capabilities

### New Capabilities
- `acessos`: Decisão de entrada no parque a partir da matrícula lida pelo leitor à entrada.

### Modified Capabilities
<!-- nenhuma -->

## Impact

Código em `src/main/java`, testes em `src/test/java` e `e2e/tests`.
