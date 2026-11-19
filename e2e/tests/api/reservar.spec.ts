import { test, expect } from '@playwright/test';
import { PARQUE_CAMPUS, reporDados } from '../apoio';

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
