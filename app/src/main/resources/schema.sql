CREATE TABLE IF NOT EXISTS url (
  id                            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name                          VARCHAR(255),
  created_at                    TIMESTAMP NOT NULL,
  CONSTRAINT pk_url PRIMARY KEY (id)
);