CREATE TABLE Todo(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(255),
    completed BOOLEAN,
    readonly BOOLEAN
) engine=InnoDB DEFAULT CHARSET = gbk;