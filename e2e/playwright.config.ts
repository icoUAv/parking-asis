import { defineConfig, devices } from '@playwright/test';

/**
 * Testes de aceitação: um teste por caso de teste da SRS, com o ID no título (CT-nn.k).
 *   npx playwright test --grep "CT-02"   -> corre os testes de UC-02
 *
 * A aplicação arranca com o perfil "e2e" (ativa a reposição de dados).
 * Localmente usa o Maven; no pipeline, APP_CMD aponta para o jar já construído.
 */
export default defineConfig({
  testDir: './tests',
  // Uma base de dados partilhada: os testes correm em série.
  workers: 1,
  fullyParallel: false,
  forbidOnly: !!process.env.CI,
  retries: 0,
  reporter: [['list'], ['html', { open: 'never' }]],
  use: {
    baseURL: 'http://localhost:8080',
    trace: 'retain-on-failure',
  },
  projects: [
    { name: 'api', testDir: './tests/api' },
    { name: 'ui', testDir: './tests/ui', use: { ...devices['Desktop Chrome'] } },
  ],
  webServer: {
    command: process.env.APP_CMD ?? 'mvn -q -f ../pom.xml spring-boot:run -Dspring-boot.run.profiles=e2e',
    url: 'http://localhost:8080/',
    reuseExistingServer: !process.env.CI,
    timeout: 180_000,
  },
});
