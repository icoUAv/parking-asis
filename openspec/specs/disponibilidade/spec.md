# disponibilidade Specification

## Purpose

Consulta dos lugares livres de um parque num período, para o condutor decidir onde reservar.

## Requirements

### Requirement: UC-01 Consultar disponibilidade
O sistema SHALL indicar, para um parque e um período [início, fim[, o número de lugares livres, igual à lotação menos o número de reservas ativas que se sobrepõem ao período.

#### Scenario: CT-01.1 Parque sem reservas tem todos os lugares livres
- **WHEN** o condutor consulta o Parque do Campus (lotação 2), sem reservas, de 02/12 10:00 a 12:00
- **THEN** o sistema indica 2 lugares livres

#### Scenario: CT-01.2 Reserva consecutiva não ocupa o período seguinte
- **WHEN** existe uma reserva ativa de 10:00 a 12:00 e o condutor consulta 11:00–13:00 e 12:00–13:00
- **THEN** o sistema indica 1 e 2 lugares livres, respetivamente

#### Scenario: CT-01.3 Condutor consulta os lugares livres de um parque
- **WHEN** existe uma reserva ativa de 10:00 a 12:00 e o condutor consulta esse período na página de disponibilidade
- **THEN** a página mostra "1 de 2 lugares livres"
