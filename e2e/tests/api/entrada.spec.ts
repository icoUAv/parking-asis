import { test, expect } from '@playwright/test';
import { PARQUE_CAMPUS, criarReserva, reporDados } from '../apoio';

// UC-04 Registar entrada no parque.
// O teste faz o papel do leitor de matrículas (simulador do dispositivo IoT).
test.beforeEach(async ({ request }) => {
  await reporDados(request);
});

test('CT-04.1 Entrada autorizada para matrícula com reserva ativa (BR-04)', async ({ request }) => {
  await criarReserva(request, 'AA-00-AA', '2026-12-02T10:00', '2026-12-02T12:00');

  const resposta = await request.post('/api/acessos/entradas', {
    data: { parqueId: PARQUE_CAMPUS, matricula: 'AA-00-AA', instante: '2026-12-02T10:05' },
  });

  expect(resposta.status()).toBe(200);
  expect(await resposta.json()).toEqual({ decisao: 'AUTORIZADA' });
});

test('CT-04.2 Entrada recusada fora do período reservado (BR-04)', async ({ request }) => {
  await criarReserva(request, 'AA-00-AA', '2026-12-02T10:00', '2026-12-02T12:00');

  const resposta = await request.post('/api/acessos/entradas', {
    data: { parqueId: PARQUE_CAMPUS, matricula: 'AA-00-AA', instante: '2026-12-02T12:00' },
  });

  expect(resposta.status()).toBe(403);
  expect(await resposta.json()).toEqual({ decisao: 'RECUSADA' });
});
