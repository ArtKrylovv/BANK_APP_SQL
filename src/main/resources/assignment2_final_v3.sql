-- CREATING TABLES
CREATE TABLE IF NOT EXISTS States (Id INT PRIMARY KEY AUTO_INCREMENT, Abbreviation VARCHAR(45), Name Varchar(45) NOT NULL);

CREATE TABLE IF NOT EXISTS Addresses (Id INT PRIMARY KEY AUTO_INCREMENT, House_number INT NOT NULL, Street_name VARCHAR(45) NOT NULL, Apt_number INT, City VARCHAR(45) NOT NULL, State_Id INT,
FOREIGN KEY (State_Id) REFERENCES States(Id) ON DELETE SET NULL ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS Customers (SSN INT PRIMARY KEY, First_name VARCHAR(45) NOT NULL, Last_name VARCHAR(45) NOT NULL,
Addresses_Id INT, FOREIGN KEY (Addresses_Id) REFERENCES Addresses(Id) ON DELETE SET NULL);

CREATE TABLE IF NOT EXISTS Departments (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(45) NOT NULL);
CREATE TABLE IF NOT EXISTS Payrolls (Id INT PRIMARY KEY AUTO_INCREMENT, Pay_type VARCHAR(45) NOT NULL, Annual_salary INT, Hourly_rate INT);

-- setting FK unique to establish 1:1 Addresses - Branches relation (Branches)
CREATE TABLE IF NOT EXISTS Branches (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(45) NOT NULL, Addresses_Id INT UNIQUE,
FOREIGN KEY (Addresses_Id) REFERENCES Addresses(Id) ON DELETE SET NULL);

CREATE TABLE IF NOT EXISTS Employees (Id INT PRIMARY KEY AUTO_INCREMENT, First_name VARCHAR(45) NOT NULL, Last_name VARCHAR(45) NOT NULL, Role VARCHAR(45), Addresses_Id INT, Departments_Id INT, Branches_Id INT, Payrolls_Id INT,
FOREIGN KEY (Addresses_Id) REFERENCES Addresses(Id) ON DELETE SET NULL,
FOREIGN KEY (Departments_Id) REFERENCES Departments(Id) ON DELETE SET NULL,
FOREIGN KEY (Branches_Id) REFERENCES Branches(Id) ON DELETE SET NULL,
FOREIGN KEY (Payrolls_Id) REFERENCES Payrolls(Id) ON DELETE SET NULL);


-- setting FK unique to establish 1:1 Checking - Customer relation (Checking_accounts)
CREATE TABLE IF NOT EXISTS Checking_accounts (Account_number BIGINT(16) PRIMARY KEY AUTO_INCREMENT, Balance BIGINT(20) NOT NULL, Customers_Id INT UNIQUE,
FOREIGN KEY (Customers_Id) REFERENCES Customers(SSN) ON DELETE SET NULL ON UPDATE CASCADE) AUTO_INCREMENT =1000000000000000;
CREATE TABLE IF NOT EXISTS Saving_accounts (Account_number BIGINT(16) PRIMARY KEY AUTO_INCREMENT, Interest DECIMAL(2,2) NOT NULL, Balance BIGINT(20) NOT NULL, Customers_Id INT,
FOREIGN KEY (Customers_Id) REFERENCES Customers(SSN) ON DELETE SET NULL ON UPDATE CASCADE) AUTO_INCREMENT =2000000000000000;
CREATE TABLE IF NOT EXISTS Credit_card_accounts (Account_number BIGINT(16) PRIMARY KEY AUTO_INCREMENT, Interest DECIMAL(2,2) NOT NULL, Balance BIGINT(20) NOT NULL, Customers_Id INT,
FOREIGN KEY (Customers_Id) REFERENCES Customers(SSN) ON DELETE SET NULL ON UPDATE CASCADE) AUTO_INCREMENT =1111111111111111;

-- creating table to establish M:N relation between Customers and Employees (Customer_Relations)
CREATE TABLE IF NOT EXISTS Customer_Relations (Id INT PRIMARY KEY AUTO_INCREMENT, Notes VARCHAR(100), Employees_Id INT, Customers_Id INT,
FOREIGN KEY (Customers_Id) REFERENCES Customers(SSN) ON DELETE SET NULL ON UPDATE CASCADE,
FOREIGN KEY (Employees_Id) REFERENCES Employees(Id) ON DELETE SET NULL);

CREATE TABLE IF NOT EXISTS Appointments (Id INT PRIMARY KEY AUTO_INCREMENT, Reason VARCHAR(45) NOT NULL, Completed TINYINT NOT NULL, Employees_Id INT, Customers_Id INT,
FOREIGN KEY (Customers_Id) REFERENCES Customers(SSN) ON DELETE SET NULL ON UPDATE CASCADE,
FOREIGN KEY (Employees_Id) REFERENCES Employees(Id) ON DELETE SET NULL);

CREATE TABLE IF NOT EXISTS Loan_applications (Id INT PRIMARY KEY AUTO_INCREMENT, Object Varchar(45) NOT NULL, Amount BIGINT(20) NOT NULL, Interest DECIMAL(2,2) NOT NULL, Duration TINYINT NOT NULL, Created DATETIME NOT NULL, Employees_Id INT, Customers_Id INT,
FOREIGN KEY (Customers_Id) REFERENCES Customers(SSN) ON DELETE SET NULL ON UPDATE CASCADE,
FOREIGN KEY (Employees_Id) REFERENCES Employees(Id) ON DELETE SET NULL);

-- creates 1:1 table with loan_approvals
CREATE TABLE IF NOT EXISTS Loan_approvals(Id INT PRIMARY KEY AUTO_INCREMENT, Approval_status TINYINT NOT NULL, Loan_applications_Id INT UNIQUE, Employees_Id INT,
FOREIGN KEY (Loan_applications_Id) REFERENCES Loan_applications(Id) ON DELETE SET NULL,
FOREIGN KEY (Employees_Id) REFERENCES Employees(Id) ON DELETE SET NULL);

-- INSERT STATEMENTS

INSERT INTO States (Abbreviation, Name)
VALUES ("CA", "California"),
("FL", "Florida"),
("NY", "New York"),
("TX", "Texas"),
("TE", "Test");


INSERT INTO Addresses (House_number, Street_name, Apt_number,City, State_Id)
VALUES (100, "Main st", Null, "Los Angeles", 1),
(100, "Lincoln ave", 100, "Dallas", 4),
(100, "Washington ave", 100, "Orlando", 2),
(200, "Commerce ave", 100, "Los Angeles", 1),
(200, "Commerce ave", 101, "Los Angeles", 1),
(1, "Greed st", Null, "New York", 3),
(10, "Santa Monica blvd", Null, "Santa Monica", 1),
(1000, "Cosmos ave", Null, "Los Angeles", 1),
(1, "Test ave", 1, "Testville", 5),
(1730, "Sawtelle bld", Null, "West Los Angeles", 1);

INSERT INTO Customers (SSN, First_name, Last_name, Addresses_Id)
VALUES (100000000, "Elon", "Musk", 1),
(100000001, "Nikola", "Tesla", 2),
(100000002, "Marie", "Curie", 3),
(100000004, "Pierre", "Curie", 3),
(100000003, "Sergei", "Korolev", 8),
(111111111, "Joe", "Doe", 9);


INSERT INTO Payrolls (Pay_type, Annual_salary, Hourly_rate)
VALUES ("Full-time", 1000000, Null),
 ("Full-time", 500000, Null),
 ("Part-Time", Null, 0),
  ("Test", Null, 1);

INSERT Departments (Name)
VALUES ("Finance"),
("Retail"),
("Management"),
("IT"),
("Test");


INSERT Branches (Name, Addresses_id)
VALUES ("HQ",5),
("Santa_Monica", 6),
("Venice", 7),
("Test", 9);


INSERT INTO Employees (First_name, Last_name, Role, Addresses_Id, Departments_Id, Branches_Id, Payrolls_Id)
VALUES ("Charles" ,"Ponzie", "CEO", 4, 1, 1, 1),
("Scrooge", "McDuck", "CFO", 5, 1, 1, 2),
("Satoshi", "Nakamoto", "CTO", 10, 3, 1, 2),
("Jane", "Doe", "Boss", 9, 3, 1, 3);


INSERT INTO Checking_accounts(Balance, Customers_Id)
VALUES(1000000000, 100000000),
(500000, 100000001),
(2500000, 100000002),
(5000, 100000003);

INSERT INTO Saving_accounts(Balance, Interest, Customers_Id)
VALUES(1000000000, 0.05, 100000000),
(2000000000, 0.06, 100000000),
(500000, 0.05,100000001),
(100000, 0.06,100000001),
(2500000, 0.05,100000002),
(1000000, 0.07,100000002),
(100000, 0.05, 100000003);

INSERT INTO Credit_card_accounts(Balance, Interest, Customers_Id)
VALUES(10000, 0.15, 100000000),
(1000, 0.15,100000001),
(100, 0.15,100000002),
(0, 0.15,100000003);


INSERT INTO Customer_relations (Notes, Employees_Id, Customers_Id)
VALUES ("Wants to sell Paypall", 1, 100000000),
 ("Wants to transfer funds to Serbia", 1, 100000001),
 ("Complains about cards stop working due to radiation", 2, 100000002),
 ("Test", 4, 111111111);

INSERT INTO Appointments (Reason, Completed, Employees_Id, Customers_Id)
VALUES ("Loan to start electric car manufacturing", 1, 1, 100000000),
 ("Loan to build power plant", 1, 1, 100000001),
 ("Loan to buy Uranium ", 0, 2, 100000002),
 ("Test", 1, 4, 111111111);

INSERT INTO Loan_Applications (Object, Amount, Interest, Duration, Created, Employees_Id, Customers_Id)
VALUES ("Machinery for car manufacturing", 500000000, 0.06, 120, "2010-01-01 12:00:00", 1, 100000000),
("Antenna to transfer energy via air", 1000000, 0.20, 36, "1920-01-01 12:00:00", 1, 100000001),
("Uranium ore, 1lb", 10000, 0.15, 12, "1930-01-01 12:00:00", 1, 100000002),
 ("Test", 100, 0.01,12, "1991-03-12 07:00:00", 4, 111111111);


INSERT INTO Loan_approvals (Approval_status, Loan_applications_id, Employees_Id)
VALUES (1, 1, 1),
(1, 2, 2),
(0, 3, 2);

-- UPDATE QUERY
UPDATE States
SET Name = "My Test"
WHERE Id = 5;

UPDATE Addresses
SET Street_name = "My Test Ave"
WHERE Id = 9;

UPDATE Customers
SET Addresses_id = 10
WHERE SSN = 1111111;

UPDATE Employees
SET Role = "Director"
Where Id = 4;

UPDATE Customer_relations
SET Notes = "System check"
WHERE Id = 4;

UPDATE Appointments
SET Reason = "System check"
WHERE Id = 4;

UPDATE Loan_applications
SET Amount = 1000
WHERE Id = 4;

UPDATE Payrolls
SET Hourly_rate = 10
WHERE id =4;

UPDATE Departments
SET Name = "Test only"
WHERE id = 5;

UPDATE Branches
SET Name = "Test location"
WHERE id = 4;

-- DELETE QUERIES

DELETE FROM States
WHERE Id = 5;

DELETE FROM Addresses
WHERE Id = 9;

DELETE FROM Customers
WHERE SSN = 1111111;

DELETE FROM Employees
Where Id = 4;

DELETE FROM Customer_relations
WHERE Id = 4;

DELETE FROM Appointments
WHERE Id = 4;

DELETE FROM Loan_applications
WHERE Id = 4;

DELETE FROM Payrolls
WHERE id =4;

DELETE FROM Departments
WHERE id = 5;

DELETE FROM  Branches
WHERE id = 4;

-- ALTER TABLE QUERIES
ALTER TABLE Customers
MODIFY First_name VARCHAR(40),
MODIFY Last_name VARCHAR(40);

ALTER TABLE Employees
MODIFY First_name VARCHAR(40),
MODIFY Last_name VARCHAR(40);

-- Now interest can be Null
ALTER TABLE Saving_accounts
MODIFY Interest DECIMAL(2,2);

ALTER TABLE Saving_accounts
MODIFY Interest DECIMAL(2,2);

ALTER TABLE Loan_approvals
ADD Date_approved DATETIME;


-- 7 statements with aggregate functions and group by and with having.
SELECT Customers_Id, MAX(Balance) as Total_balance
FROM Saving_accounts
GROUP BY Customers_Id
HAVING Customers_Id!=100000000;

SELECT Customers_Id, MIN(Balance) as Total_balance
FROM Saving_accounts
GROUP BY Customers_Id
HAVING Customers_Id=100000000;

SELECT Customers_Id, AVG(Balance) as Avg_balance
FROM Checking_accounts
GROUP BY Customers_Id
HAVING Customers_Id!=100000001;

SELECT Customers_Id, MIN(Balance) as Min_balance
FROM Credit_card_accounts
GROUP BY Customers_Id
HAVING Customers_Id!=100000002;

SELECT Addresses_Id, COUNT(SSN)
FROM Customers
GROUP BY Addresses_Id
HAVING COUNT(SSN)>1;

SELECT Customers_Id, AVG(Duration)
FROM Loan_applications
GROUP BY Customers_Id
HAVING Customers_Id!=1000000;

SELECT Customers_Id, AVG(Duration)
FROM Loan_applications
GROUP BY Customers_Id
HAVING AVG(Duration)>12;

-- 7 statements with aggregate functions and group by and without having.
SELECT State_Id, COUNT(Id)
FROM Addresses
GROUP BY State_Id;

SELECT Customers_Id, COUNT(Account_number)
FROM Saving_accounts
GROUP BY Customers_Id;

SELECT Customers_Id, SUM(Amount)
FROM Loan_applications
GROUP BY Customers_id;

SELECT Employees_id, COUNT(ID)
FROM  Loan_approvals
GROUP BY Employees_id;

SELECT Pay_type, COUNT(ID)
FROM Payrolls
GROUP BY Pay_type;

SELECT Employees_ID, Count(ID)
FROM Appointments
GROUP BY Employees_ID;

SELECT Employees_Id, Max(Amount)
FROM Loan_applications
GROUP BY Employees_Id;

-- 5 statements with left, right, inner, outer joins.
Select c.First_name, c.Last_name, sa.Balance as Savings_Balance
FROM Customers c
JOIN Saving_accounts sa
ON c.SSN = sa.Customers_Id;

SELECT c.First_name, c.Last_name, ch.Balance as Checking_Balance
FROM Customers c
LEFT JOIN Checking_accounts ch
ON c.SSN = ch.Customers_Id;

SELECT e.First_name, e.Last_Name, d.Name
FROM Employees e
RIGHT JOIN Departments d
ON e.Departments_Id = d.Id;

SELECT c.First_name, c.Last_name, a.Street_name, a.House_number, a.City, a.State_Id
FROM Customers c
LEFT JOIN Addresses a ON c.Addresses_Id = a.Id
UNION
SELECT c.First_name, c.Last_name, a.Street_name, a.House_number, a.City, a.State_Id
FROM Customers c
LEFT JOIN Addresses a ON c.Addresses_Id = a.Id;

-- 1 big statement to join all tables in the database

SELECT c.Last_name, ch.Balance as Checking_Balance, sa.Balance as Savings_Balance, cc.Balance as Credit_Balance, la.Amount as Loan_Apl_Amount, apr.Approval_Status as Loan_approved,ap.Completed as Completed_appointments, ad.City, s.Name,
cr.Notes as Meeting_Notes, em.Last_name as Employee_LN, br.Name as Branch_name, dp.Name as Department, pr.Pay_type
FROM Customers c
LEFT JOIN Checking_accounts ch ON c.SSN = ch.Customers_Id
LEFT JOIN Saving_accounts sa ON c.SSN = sa.Customers_Id
LEFT JOIN Credit_card_accounts cc ON c.SSN = cc.Customers_Id
LEFT JOIN Loan_applications la ON c.SSN = la.Customers_Id
LEFT JOIN Appointments ap ON c.SSN = ap.Customers_Id
LEFT JOIN Customer_relations cr ON c.SSN = cr.Customers_Id
LEFT JOIN Addresses ad  ON c.Addresses_id = ad.Id
LEFT JOIN States s ON ad.State_id = s.Id
LEFT JOIN Employees em ON cr.Employees_Id = em.Id
LEFT JOIN Loan_approvals apr ON em.Id = apr.Employees_Id
LEFT JOIN Departments dp ON em.Departments_Id = dp.Id
LEFT JOIN Payrolls pr ON em.Payrolls_Id = pr.Id
LEFT JOIN Branches br ON em.Branches_Id = br.Id;



