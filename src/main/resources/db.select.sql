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