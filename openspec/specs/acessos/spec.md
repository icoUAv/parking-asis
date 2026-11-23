# acessos Specification

## Purpose

Decisão de entrada no parque a partir da matrícula lida pelo leitor à entrada.

## Requirements

### Requirement: UC-04 Registar entrada no parque
O sistema SHALL autorizar a entrada de uma matrícula num parque se existir uma reserva ativa dessa matrícula nesse parque cujo período inclui o instante da leitura; caso contrário SHALL recusar a entrada (BR-04).

#### Scenario: CT-04.1 Entrada autorizada para matrícula com reserva ativa
- **WHEN** AA-00-AA tem uma reserva ativa de 02/12 10:00 a 12:00 e o leitor lê a matrícula às 10:05
- **THEN** o sistema responde AUTORIZADA

#### Scenario: CT-04.2 Entrada recusada fora do período reservado
- **WHEN** AA-00-AA tem uma reserva ativa de 02/12 10:00 a 12:00 e o leitor lê a matrícula às 12:00
- **THEN** o sistema responde RECUSADA
