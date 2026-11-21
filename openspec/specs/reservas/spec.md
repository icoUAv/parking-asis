# reservas Specification

## Purpose

Registo e cancelamento de reservas de lugares, por matrícula, parque e período.

## Requirements

### Requirement: UC-02 Reservar lugar
O sistema SHALL registar uma reserva ativa para a matrícula, o parque e o período indicados pelo condutor, e mostrar o número da reserva. O sistema SHALL recusar períodos cujo fim não seja posterior ao início ou cuja duração exceda 12 horas (BR-01).

#### Scenario: CT-02.1 Condutor reserva um lugar num parque com lugares livres
- **WHEN** o condutor reserva no Parque do Campus, sem reservas, para AA-00-AA de 02/12 10:00 a 12:00
- **THEN** o sistema confirma a reserva para AA-00-AA e mostra o número da reserva

#### Scenario: CT-02.2 Reserva com mais de 12 horas é recusada
- **WHEN** o condutor pede uma reserva de 02/12 08:00 a 20:01
- **THEN** o sistema recusa o pedido indicando a regra BR-01

### Requirement: UC-03 Cancelar reserva
O sistema SHALL cancelar uma reserva ativa a pedido do condutor, deixando de a contar na disponibilidade. O sistema SHALL recusar o cancelamento de uma reserva que não esteja ativa (BR-03).

#### Scenario: CT-03.1 Cancelar uma reserva ativa liberta o lugar
- **WHEN** o condutor cancela a sua reserva ativa de 02/12 10:00 a 12:00 no Parque do Campus
- **THEN** a reserva fica CANCELADA e o parque passa a ter 2 lugares livres nesse período

#### Scenario: CT-03.2 Cancelar uma reserva já cancelada é recusado
- **WHEN** o condutor tenta cancelar uma reserva já cancelada
- **THEN** o sistema recusa o pedido indicando a regra BR-03
