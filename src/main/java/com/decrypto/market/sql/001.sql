
create table clients (
   id INT(11) NOT NULL AUTO_INCREMENT,
   description VARCHAR(250) NOT NULL UNIQUE,
   PRIMARY KEY (id)
);

CREATE TABLE countries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL UNIQUE,
    CHECK (name IN ('Argentina', 'Uruguay'))
);

create table markets (
   id INT(11) NOT NULL AUTO_INCREMENT,
   code VARCHAR(250) NOT NULL UNIQUE,
   description  VARCHAR(250),
   id_country INT(11) not null,
   PRIMARY KEY (id),
   FOREIGN KEY (id_country) REFERENCES countries(id)
);

CREATE TABLE clients_markets (
    id_client int NOT NULL,
    id_market int NOT NULL,
    primary key (id_client,id_market),
    FOREIGN KEY (id_client) REFERENCES clients(id),
    FOREIGN KEY (id_market) REFERENCES markets(id)
);