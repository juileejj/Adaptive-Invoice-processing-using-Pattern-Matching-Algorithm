
Table `teragigs`.`header_mstr`-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `teragigs`.`header_mstr` (
  `transmission_date` DATE NOT NULL COMMENT '',
  `invoice_num` VARCHAR(10) NOT NULL COMMENT '',
  `control_num` VARCHAR(10) NOT NULL COMMENT '',
  `sender_code` VARCHAR(10) NOT NULL COMMENT '',
  `receiver_code` VARCHAR(10) NOT NULL COMMENT '',
  `invoice_header_number` VARCHAR(10) NOT NULL COMMENT '',
  `invoice_header_supplier` VARCHAR(10) NOT NULL COMMENT '',
  PRIMARY KEY (`invoice_num`)  COMMENT '')
ENGINE = InnoDB;








use teragigs ;
INSERT INTO header_mstr
(transmission_date,invoice_num,control_num,sender_code,receiver_code,invoice_header_number,
invoice_header_supplier) 
VALUES
( '2015-12-12','2','1','3','04','01','02'
);


use teragigs ;
INSERT INTO invoice_header
(invoice_num,sender_code,bil_lto_code,purchase_order_number,date,
remarks) 
VALUES
( '2','3','3','02','2015=09-24','nill'
);


use teragigs ;
INSERT INTO address_mstr
(type,sender_code,address_line,zip,phone,
email) 
VALUES
( '20','3','2','02114','201509024','abc@gmail'
);

use teragigs ;
INSERT INTO Detail
(invoice_num,line_number,item_number,quantity,
line_price) 
VALUES
( '2','14','4','5','201'
);




use teragigs ;
INSERT INTO summary_mstr
(invoice_num,invoice_total,total_tax,discount_pct,discount_amount,payment_duedate,
tax_pct) 
VALUES
( '2','14','2000','5.55','20.00','2015-12-12','1.00'
);

use teragigs ;
INSERT INTO supplier_mstr
(sender_code,ship_to,taxable,name,salesperson) 
VALUES
( '3','14','2','abcd','person1');


use teragigs ;
INSERT INTO zipcode_info
(zip,city,state,country) 
VALUES
( '02114','Boston','MA','USA');



use teragigs ;
INSERT INTO item_mstr
(item_number,unit_of_measurement,price_per_qty) 
VALUES
( '4','mm','400');


use teragigs ;
INSERT INTO document_mstr
(doc_id,doc_name) 
VALUES
( '23','Invoice1');


use teragigs ;
INSERT INTO table_mstr
(tbl_id,tbl_name) 
VALUES
( '23','header_mstr');

use teragigs ;
INSERT INTO column_mstr
(col_id,col_name,col_tab_id) 
VALUES
( '2','invoicenumber','23');



