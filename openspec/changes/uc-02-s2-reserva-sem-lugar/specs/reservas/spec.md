# Spec Delta

## MODIFIED Requirements

### Requirement: UC-02 Reservar lugar
O sistema SHALL registar uma reserva ativa para a matrícula, o parque e o período indicados pelo condutor, e mostrar o número da reserva. O sistema SHALL recusar períodos cujo fim não seja posterior ao início ou cuja duração exceda 12 horas (BR-01). O sistema SHALL recusar o pedido quando o número de reservas ativas do parque que se sobrepõem ao período for igual ou superior à lotação, considerando os períodos como intervalos [início, fim[ (BR-02).

#### Scenario: CT-02.1 Condutor reserva um lugar num parque com lugares livres
- **WHEN** o condutor reserva no Parque do Campus, sem reservas, para AA-00-AA de 02/12 10:00 a 12:00
- **THEN** o sistema confirma a reserva para AA-00-AA e mostra o número da reserva

#### Scenario: CT-02.2 Reserva com mais de 12 horas é recusada
- **WHEN** o condutor pede uma reserva de 02/12 08:00 a 20:01
- **THEN** o sistema recusa o pedido indicando a regra BR-01

#### Scenario: CT-02.3 Reserva recusada quando o parque está cheio
- **WHEN** o Parque do Campus (lotação 2) tem duas reservas ativas de 02/12 10:00 a 12:00 e o condutor reserva esse período na página para CC-22-CC
- **THEN** a página mostra "Não há lugares disponíveis no período pedido." e mantém os dados do pedido

#### Scenario: CT-02.4 Lotação esgotada recusa o pedido sobreposto e aceita o consecutivo
- **WHEN** o Parque do Campus tem duas reservas ativas de 02/12 10:00 a 12:00 e chegam pedidos de 11:00 a 13:00 e de 12:00 a 14:00
- **THEN** o primeiro é recusado indicando a regra BR-02 e o segundo é aceite
