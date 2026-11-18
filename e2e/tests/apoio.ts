import { APIRequestContext, expect } from '@playwright/test';

/** Parque de exemplo com lotação 2 (ver src/main/resources/data.sql). */
export const PARQUE_CAMPUS = 1;

/** Repõe os dados antes de cada teste: apaga todas as reservas. */
export async function reporDados(request: APIRequestContext) {
  const resposta = await request.post('/api/testes/reset');
  expect(resposta.status()).toBe(204);
}

/** Cria uma reserva pela API, como pré-condição de um teste. */
export async function criarReserva(
  request: APIRequestContext,
  matricula: string,
  inicio: string,
  fim: string,
  parqueId = PARQUE_CAMPUS,
) {
  const resposta = await request.post('/api/reservas', {
    data: { parqueId, matricula, inicio, fim },
  });
  expect(resposta.status(), await resposta.text()).toBe(201);
  return resposta.json();
}
