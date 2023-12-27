

INSERT INTO employee (name, surname, username, email, password, user_role, date_of_creation, role_id)
VALUES
    ('John', 'Doe', 'johndoe', 'john.doe@example.com', 'password123', 'admin', '2022-01-01', 1),
    ('Jane', 'Smith', 'janesmith', 'jane.smith@example.com', 'password456', 'user', '2022-01-02', 2),
    ('Michael', 'Johnson', 'michaeljohnson', 'michael.johnson@example.com', 'password789', 'user', '2022-01-03', 2),
    ('Emily', 'Brown', 'emilybrown', 'emily.brown@example.com', 'passwordabc', 'user', '2022-01-04', 2),
    ('David', 'Williams', 'davidwilliams', 'david.williams@example.com', 'passworddef', 'user', '2022-01-05', 2),
    ('Sarah', 'Taylor', 'arahtaylor', 'sarah.taylor@example.com', 'passwordghi', 'user', '2022-01-06', 2),
    ('Chris', 'Anderson', 'chrisanderson', 'chris.anderson@example.com', 'passwordjkl', 'user', '2022-01-07', 2),
    ('Jessica', 'Martinez', 'jessicamartinez', 'jessica.martinez@example.com', 'passwordmno', 'user', '2022-01-08', 2),
    ('Daniel', 'Garcia', 'danielgarcia', 'daniel.garcia@example.com', 'passwordpqr', 'user', '2022-01-09', 2),
    ('Olivia', 'Lopez', 'olivialopez', 'olivia.lopez@example.com', 'passwordstu', 'user', '2022-01-10', 2);


INSERT INTO working_place (working_place_name,employee_id,task_id)
VALUES ('TAXIING') ,
       ('TOWER') ,
       ('RADAR') ,
       ('APPROACH') ,
       ('CONTROL') ,
       ('UNION SECTOR') ,
       ('MILITARY LINE');