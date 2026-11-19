import { test, expect } from '@playwright/test';
import { reporDados } from '../apoio';

// UC-02 Reservar lugar (interface)
test.beforeEach(async ({ request }) => {
  await reporDados(request);
});

test('CT-02.1 Condutor reserva um lugar num parque com lugares livres', async ({ page }) => {
  await page.goto('/reservas/nova');
  await page.getByLabel('Parque').selectOption({ label: 'Parque do Campus' });
  await page.getByLabel('Matrícula').fill('AA-00-AA');
  await page.getByLabel('Início').fill('2026-12-02T10:00');
  await page.getByLabel('Fim').fill('2026-12-02T12:00');
  await page.getByRole('button', { name: 'Reservar' }).click();

  await expect(page.getByTestId('mensagem-sucesso')).toContainText('confirmada para AA-00-AA');
  await expect(page.getByTestId('reserva-id')).toHaveText(/\d+/);
});
