SET MODE PostgreSql;

CREATE TABLE IF NOT EXISTS teams (
    id int PRIMARY KEY auto_increment,
    name VARCHAR
);

CREATE TABLE IF NOT EXISTS members (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    teamid INTEGER
);