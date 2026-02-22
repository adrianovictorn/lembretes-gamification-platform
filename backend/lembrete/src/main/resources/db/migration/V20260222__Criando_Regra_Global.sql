INSERT INTO level (
  numero, xp_minimo, titulo, descricao, icone, cor_hex, ativo, criado_em, atualizado_em
) VALUES (
  1,
  0,
  'INICIANTE',
  'Você está começando a mudar sua vida com Gamificação',
  NULL,
  NULL,
  TRUE,
  NOW(),
  NULL
);

CREATE TABLE game_rules_config(
    id BIGSERIAL PRIMARY KEY,
    xp_max int NOT NULL DEFAULT 4000,
    streak_ativa BOOLEAN NOT NULL,
    level_id BIGINT NOT NULL,

    FOREIGN KEY (level_id) REFERENCES level(id)
);


INSERT INTO game_rules_config (xp_max, streak_ativa, level_id)
VALUES (
  4000,
  TRUE,
  (SELECT id FROM level WHERE numero = 1)
);

