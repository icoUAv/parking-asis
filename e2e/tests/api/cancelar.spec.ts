import { test, expect } from '@playwright/test';
import { PARQUE_CAMPUS, criarReserva, reporDados } from '../apoio';

// UC-03 Cancelar reserva
test.beforeEach(async ({ request }) => {
  await reporDados(request);
});

test('CT-03.1 Cancelar uma reserva ativa liberta o lugar', async ({ request }) => {
  const reserva = await criarReserva(request, 'AA-00-AA', '2026-12-02T10:00', '2026-12-02T12:00');

  const resposta = await request.post(`/api/reservas/${reserva.id}/cancelamento`);
  const disponibilidade = await request.get(`/api/parques/${PARQUE_CAMPUS}/disponibilidade`, {
    params: { inicio: '2026-12-02T10:00', fim: '2026-12-02T12:00' },
  });

  expect(resposta.status()).toBe(200);
  expect(await resposta.json()).toMatchObject({ estado: 'CANCELADA' });
  expect((await disponibilidade.json()).livres).toBe(2);
});

test('CT-03.2 Cancelar uma reserva já cancelada é recusado (BR-03)', async ({ request }) => {
  const reserva = await criarReserva(request, 'AA-00-AA', '2026-12-02T10:00', '2026-12-02T12:00');
  await request.post(`/api/reservas/${reserva.id}/cancelamento`);

  const resposta = await request.post(`/api/reservas/${reserva.id}/cancelamento`);

  expect(resposta.status()).toBe(422);
  expect(await resposta.json()).toMatchObject({ regra: 'BR-03' });
});
