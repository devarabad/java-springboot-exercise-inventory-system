CREATE TABLE TYPE(
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(32) NOT NULL,
  DESCRIPTION VARCHAR(64),
  COST DECIMAL NOT NULL,
  ACTIVE INT NOT NULL,
  CREATED_DATE DATE,
  MODIFIED_DATE DATE
);

CREATE TABLE ITEM(
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  TYPE_ID BIGINT NOT NULL,
  NAME VARCHAR(32) NOT NULL,
  DESCRIPTION VARCHAR(64),
  COST DECIMAL NOT NULL,
  ITEM_COUNT BIGINT NOT NULL,
  ACTIVE INT NOT NULL,
  CREATED_DATE DATE,
  MODIFIED_DATE DATE
);

ALTER TABLE ITEM ADD FOREIGN KEY (TYPE_ID) REFERENCES TYPE(ID);
CREATE INDEX IDX_CREATED_DATE_ ON ITEM(CREATED_DATE);