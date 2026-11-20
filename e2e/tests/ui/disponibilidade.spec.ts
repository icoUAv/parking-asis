import { test, expect } from '@playwright/test';
import { criarReserva, reporDados } from '../apoio';

// UC-01 Consultar disponibilidade (interface)
test.beforeEach(async ({ request }) => {
  await reporDados(request);
});

test('CT-01.3 Condutor consulta os lugares livres de um parque', async ({ page, request }) => {
  await criarReserva(request, 'AA-00-AA', '2026-12-02T10:00', '2026-12-02T12:00');

  await page.goto('/disponibilidade');
  await page.getByLabel('Parque').selectOption({ label: 'Parque do Campus' });
  await page.getByLabel('Início').fill('2026-12-02T10:00');
  await page.getByLabel('Fim').fill('2026-12-02T12:00');
  await page.getByRole('button', { name: 'Consultar' }).click();

  await expect(page.getByTestId('lugares-livres')).toContainText('1 de 2 lugares livres');
});
