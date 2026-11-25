# Proposal

## Why

No 1.º incremento, o registo de reservas assumia que havia lugar: um parque podia ficar com mais reservas do que lugares. O UC-02.S2 (2.º épico) acrescenta o passo 3 do fluxo básico e a exceção E1 Sem lugar disponível (BR-02).

## What Changes

- O registo de uma reserva passa a verificar a lotação do parque no período pedido (BR-02).
- Sem lugar, o pedido é recusado: mensagem na página e resposta 422 com a regra BR-02 na API.

## Capabilities

### New Capabilities
<!-- nenhuma -->

### Modified Capabilities
- `reservas`: o requirement UC-02 Reservar lugar passa a incluir a regra de lotação (BR-02)

## Impact

`dominio` (regra de lotação), `servico` (ReservaService). Sem alterações na API nem nas páginas: a recusa usa o tratamento de erros existente.
