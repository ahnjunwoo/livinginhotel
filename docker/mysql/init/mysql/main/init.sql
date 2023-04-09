use mysql;

CREATE USER IF NOT EXISTS 'repl_user'@'%' IDENTIFIED WITH mysql_native_password BY 'repl_password';
GRANT REPLICATION SLAVE ON *.* TO 'repl_user'@'%';

CREATE USER IF NOT EXISTS 'general_user'@'%' IDENTIFIED WITH mysql_native_password BY 'test1234';
GRANT INSERT, UPDATE, DELETE, SELECT ON *.* TO 'general_user'@'%';

FLUSH PRIVILEGES;
