INSERT INTO ACCOUNT (USER_ID, ACCOUNT_NUMBER, ACCOUNT_NAME, ACCOUNT_TYPE, BALANCE_DATE, CURRENCY, BALANCE)
    VALUES (100,'100309209', 'SGSavings726', 'Savings', '2019-11-08', 'SGD', 12327.51);
INSERT INTO ACCOUNT (USER_ID, ACCOUNT_NUMBER, ACCOUNT_NAME, ACCOUNT_TYPE, BALANCE_DATE, CURRENCY, BALANCE)
    VALUES (100,'100209209', 'USDSavings726', 'Savings', '2019-10-08', 'USD', 10327.51);
INSERT INTO ACCOUNT (USER_ID, ACCOUNT_NUMBER, ACCOUNT_NAME, ACCOUNT_TYPE, BALANCE_DATE, CURRENCY, BALANCE)
    VALUES (200, '200066619', 'AUSavings933', 'Savings', '2020-11-08', 'AUD', 23005.93);

INSERT INTO Account_transaction (ACCOUNT_ID, VALUE_DATE, DEBIT_AMOUNT, CREDIT_AMOUNT, TRANSACTION_TYPE, TRANSACTION_NARRATIVE)
    VALUES ((SELECT ID FROM ACCOUNT WHERE ACCOUNT_NUMBER = '100309209'), '2020-11-08', 0,9540.98, 'CREDIT', 'Salary credit');
INSERT INTO Account_transaction (ACCOUNT_ID, VALUE_DATE, DEBIT_AMOUNT, CREDIT_AMOUNT, TRANSACTION_TYPE, TRANSACTION_NARRATIVE)
    VALUES ((SELECT ID FROM ACCOUNT WHERE ACCOUNT_NUMBER = '100309209'), '2021-02-08', 500, 0, 'DEBIT', 'ATM withdrawal');
INSERT INTO Account_transaction (ACCOUNT_ID, VALUE_DATE, DEBIT_AMOUNT, CREDIT_AMOUNT, TRANSACTION_TYPE, TRANSACTION_NARRATIVE)
    VALUES ((SELECT ID FROM ACCOUNT WHERE ACCOUNT_NUMBER = '100309209'), '2021-02-10', 600, 0, 'DEBIT', 'Bill payment');