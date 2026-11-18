## Slice

- Slice: UC-nn.Sk — <!-- nome -->
- JIRA: <!-- ligação -->
- Change: `openspec/changes/<nome-da-change>`

## Casos de teste

<!-- Um por linha: ID, ficheiro do teste. -->
- CT-nn.k — `e2e/tests/...`

## Autor

- [ ] Os testes foram escritos antes da implementação e começaram por falhar
- [ ] `openspec validate --strict` sem erros
- [ ] Só há alterações no âmbito do slice
- [ ] Depois da aprovação: `/opsx:archive` como último commit do ramo

## Revisor

- [ ] Cada teste usa os valores e o resultado esperado do caso de teste da SRS
- [ ] O código fica no âmbito do slice e reutiliza o que já existe
- [ ] Não há alertas da análise estática por resolver
- [ ] A spec delta corresponde à SRS (MODIFIED com o bloco completo, se aplicável)
