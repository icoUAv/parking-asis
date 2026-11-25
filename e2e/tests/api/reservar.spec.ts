import { test, expect } from '@playwright/test';
import { PARQUE_CAMPUS, criarReserva, reporDados } from '../apoio';

// UC-02 Reservar lugar (API)
test.beforeEach(async ({ request }) => {
  await reporDados(request);
});

test('CT-02.2 Reserva com mais de 12 horas é recusada (BR-01)', async ({ request }) => {
  const resposta = await request.post('/api/reservas', {
    data: { parqueId: PARQUE_CAMPUS, matricula: 'AA-00-AA', inicio: '2026-12-02T08:00', fim: '2026-12-02T20:01' },
  });

  expect(resposta.status()).toBe(422);
  expect(await resposta.json()).toMatchObject({ regra: 'BR-01' });
});

test('CT-02.4 Lotação esgotada recusa o pedido sobreposto e aceita o consecutivo (BR-02)', async ({ request }) => {
  await criarReserva(request, 'AA-00-AA', '2026-12-02T10:00', '2026-12-02T12:00');
  await criarReserva(request, 'BB-11-BB', '2026-12-02T10:00', '2026-12-02T12:00');

  const sobreposto = await request.post('/api/reservas', {
    data: { parqueId: PARQUE_CAMPUS, matricula: 'CC-22-CC', inicio: '2026-12-02T11:00', fim: '2026-12-02T13:00' },
  });
  const consecutivo = await request.post('/api/reservas', {
    data: { parqueId: PARQUE_CAMPUS, matricula: 'CC-22-CC', inicio: '2026-12-02T12:00', fim: '2026-12-02T14:00' },
  });

  expect(sobreposto.status()).toBe(422);
  expect(await sobreposto.json()).toMatchObject({ regra: 'BR-02' });
  expect(consecutivo.status()).toBe(201);
});
