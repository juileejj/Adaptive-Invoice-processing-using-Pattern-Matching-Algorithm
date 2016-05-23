SELECT i1.invoice_num,i1.sender_code 
FROM invoice_header i1
INNER JOIN header_mstr h1 
ON  (i1.invoice_num = h1.invoice_num 
AND  i1.sender_code =h1.sender_code);

SELECT i1.sender_code
FROM invoice_header i1
INNER JOIN supplier_mstr s1
ON ( i1.sender_code = s1.sender_code);

SELECT i1.sender_code
FROM invoice_header i1
INNER JOIN address_mstr a1
ON(i1.sender_code= a1.sender_code);

SELECT i1.invoice_num
FROM invoice_header i1
INNER JOIN summary_mstr sm1
ON(i1.invoice_num= sm1.invoice_num);

SELECT i1.invoice_num
FROM invoice_header i1
INNER JOIN detail d1
ON ( i1.invoice_num = d1.invoice_num);

SELECT z1.zip
FROM zipcode_info z1
INNER JOIN address_mstr a1
ON ( z1.zipcode_info = a1.zipcode_info);


SELECT it1.item_number
FROM item_mstr it1
INNER JOIN detail d1
ON ( it1.item_number = d1.item_number);

SELECT t1.tbl_id
FROM table_mstr t1
INNER JOIN column_mstr c1
ON ( t1.tbl_id = c1.col_tab_id);


