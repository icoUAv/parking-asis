# Spec Delta

## ADDED Requirements

### Requirement: UC-03 Cancelar reserva
O sistema SHALL cancelar uma reserva ativa a pedido do condutor, deixando de a contar na disponibilidade. O sistema SHALL recusar o cancelamento de uma reserva que não esteja ativa (BR-03).

#### Scenario: CT-03.1 Cancelar uma reserva ativa liberta o lugar
- **WHEN** o condutor cancela a sua reserva ativa de 02/12 10:00 a 12:00 no Parque do Campus
- **THEN** a reserva fica CANCELADA e o parque passa a ter 2 lugares livres nesse período

#### Scenario: CT-03.2 Cancelar uma reserva já cancelada é recusado
- **WHEN** o condutor tenta cancelar uma reserva já cancelada
- **THEN** o sistema recusa o pedido indicando a regra BR-03
