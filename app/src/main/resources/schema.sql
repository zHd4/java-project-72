CREATE TABLE IF NOT EXISTS urls (
  id                            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name                          VARCHAR(255),
  created_at                    TIMESTAMP NOT NULL
);

ALTER TABLE urls DROP CONSTRAINT IF EXISTS pk_url;
ALTER TABLE urls ADD CONSTRAINT pk_url PRIMARY KEY (id);