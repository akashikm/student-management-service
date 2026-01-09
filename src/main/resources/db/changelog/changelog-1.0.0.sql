CREATE TABLE student (
    id UUID PRIMARY KEY,
    student_code INTEGER NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    age INTEGER CHECK (age >= 0),
    course VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE
);
