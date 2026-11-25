# SRS — Reservas de estacionamento em parques cobertos (versão resumida)

Exemplo condutor das aulas de ASis. Versão reduzida da SRS: só as secções usadas pelo OpenSpec e pelos testes.

## 1. Sumário

Um operador de parques cobertos permite aos condutores reservar um lugar por período. À entrada do parque, um leitor de matrículas consulta o sistema e a cancela abre só para as matrículas com reserva válida.

## 2. Atores

| Ator | Descrição |
|---|---|
| Condutor | Consulta a disponibilidade, reserva e cancela lugares (aplicação web). |
| Leitor de matrículas | Dispositivo à entrada do parque. Envia a matrícula lida e recebe a decisão (API). Nos testes é simulado. |

## 3. Casos de utilização

### 3.1 Lista e slices

| UC | Nome | Slice | Fluxos | Épico |
|---|---|---|---|---|
| UC-01 | Consultar disponibilidade | UC-01.S1 | FB | 1 |
| UC-02 | Reservar lugar | UC-02.S1 | FB, sem o passo 3 (assume lugar disponível) + E2 (BR-01) | 1 |
| UC-02 | Reservar lugar | UC-02.S2 | Passo 3 do FB + E1 Sem lugar disponível (BR-02) | 2 |
| UC-03 | Cancelar reserva | UC-03.S1 | FB + E1 Reserva não ativa (BR-03) | 1 |
| UC-04 | Registar entrada no parque | UC-04.S1 | FB + E1 Sem reserva válida (BR-04) | 1 |

Roadmap: o 1.º épico (tag v0.1) reúne os slices S1; o 2.º épico (tag v0.2) acrescenta UC-02.S2.

### 3.2 Narrativa de UC-02 Reservar lugar

- **Ator principal:** Condutor.
- **Pré-condição:** o parque existe.
- **Pós-condição (sucesso):** fica registada uma reserva ativa para a matrícula, no parque e no período pedidos.

**Fluxo básico (FB)**
1. O condutor indica o parque, a matrícula, o início e o fim.
2. O sistema valida o período (BR-01).
3. O sistema verifica que há lugar no período (BR-02).
4. O sistema regista a reserva e mostra o número da reserva.

**E1 Sem lugar disponível** (no passo 3)
1. O sistema informa que não há lugares disponíveis no período pedido.
2. Nenhuma reserva é registada. O caso de utilização termina.

**E2 Período inválido** (no passo 2)
1. O sistema indica a regra violada (BR-01). O caso de utilização termina.

## 4. Regras de negócio

| ID | Regra |
|---|---|
| BR-01 | O fim de uma reserva é posterior ao início e a duração não excede 12 horas. |
| BR-02 | Só se aceita uma reserva se o número de reservas ativas do parque que se sobrepõem ao período for inferior à lotação. Os períodos são intervalos [início, fim[: uma reserva que termina às 12:00 não ocupa o lugar às 12:00. |
| BR-03 | Só uma reserva ativa pode ser cancelada. |
| BR-04 | A entrada é autorizada se a matrícula tiver uma reserva ativa nesse parque cujo período inclui o instante da leitura. |

## 5. Casos de teste

Dados de base: o Parque do Campus (id 1) tem lotação 2.

| ID | Slice | Dado | Quando | Então | Nível |
|---|---|---|---|---|---|
| CT-01.1 | UC-01.S1 | Parque sem reservas | Consulta 02/12 10:00–12:00 | 2 lugares livres | API |
| CT-01.2 | UC-01.S1 | Reserva 10:00–12:00 | Consulta 11:00–13:00 e 12:00–13:00 | 1 e 2 lugares livres | API |
| CT-01.3 | UC-01.S1 | Reserva 10:00–12:00 | Condutor consulta 10:00–12:00 na página | "1 de 2 lugares livres" | Interface |
| CT-02.1 | UC-02.S1 | Parque sem reservas | Condutor reserva AA-00-AA, 10:00–12:00 | Reserva confirmada com número | Interface |
| CT-02.2 | UC-02.S1 | — | Pedido 08:00–20:01 (12 h 1 min) | Recusado, BR-01 | API |
| CT-02.3 | UC-02.S2 | Duas reservas 10:00–12:00 | Condutor reserva 10:00–12:00 na página para CC-22-CC | Mensagem "Não há lugares disponíveis no período pedido."; o formulário mantém os dados | Interface |
| CT-02.4 | UC-02.S2 | Duas reservas 10:00–12:00 | Pedidos 11:00–13:00 e 12:00–14:00 | O primeiro é recusado (BR-02); o segundo é aceite (fronteira) | API |
| CT-03.1 | UC-03.S1 | Reserva ativa 10:00–12:00 | Cancelar | Estado CANCELADA; 2 lugares livres | API |
| CT-03.2 | UC-03.S1 | Reserva já cancelada | Cancelar de novo | Recusado, BR-03 | API |
| CT-04.1 | UC-04.S1 | Reserva AA-00-AA 10:00–12:00 | Leitura às 10:05 | AUTORIZADA | API |
| CT-04.2 | UC-04.S1 | Reserva AA-00-AA 10:00–12:00 | Leitura às 12:00 | RECUSADA (fronteira) | API |

## 6. Restrições

- Aplicação web testável com Playwright (RT-1).
- O leitor de matrículas comunica pela API REST; nos testes é substituído por pedidos à API (RT-2).
