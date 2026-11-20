import { test, expect } from '@playwright/test';
import { PARQUE_CAMPUS, criarReserva, reporDados } from '../apoio';

// UC-01 Consultar disponibilidade
test.beforeEach(async ({ request }) => {
  await reporDados(request);
});

test('CT-01.1 Parque sem reservas tem todos os lugares livres', async ({ request }) => {
  const resposta = await request.get(`/api/parques/${PARQUE_CAMPUS}/disponibilidade`, {
    params: { inicio: '2026-12-02T10:00', fim: '2026-12-02T12:00' },
  });

  expect(resposta.status()).toBe(200);
  expect(await resposta.json()).toMatchObject({ lotacao: 2, livres: 2 });
});

test('CT-01.2 Reserva consecutiva não ocupa o período seguinte (fim exclusivo)', async ({ request }) => {
  await criarReserva(request, 'AA-00-AA', '2026-12-02T10:00', '2026-12-02T12:00');

  const sobreposto = await request.get(`/api/parques/${PARQUE_CAMPUS}/disponibilidade`, {
    params: { inicio: '2026-12-02T11:00', fim: '2026-12-02T13:00' },
  });
  const seguinte = await request.get(`/api/parques/${PARQUE_CAMPUS}/disponibilidade`, {
    params: { inicio: '2026-12-02T12:00', fim: '2026-12-02T13:00' },
  });

  expect((await sobreposto.json()).livres).toBe(1);
  expect((await seguinte.json()).livres).toBe(2);
});
