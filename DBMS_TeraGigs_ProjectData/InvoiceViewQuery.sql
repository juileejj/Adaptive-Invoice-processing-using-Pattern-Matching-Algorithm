alter view invoiceView as
select h.sender_code,h.receiver_code,h.invoice_num, 
a.type ,a.zip,
invh.purchase_order_number,
d.item_number,d.quantity,d.line_price,
s.total_tax,s.discount_pct,s.payment_duedate,
supp.salesperson
from header_mstr h
inner join supplier_mstr supp
on supp.sender_code=h.sender_code
inner join invoice_header invh
on h.sender_code=invh.sender_code and h.invoice_num=invh.invoice_num
inner join detail d 
on d.invoice_num=invh.invoice_num
inner join summary_mstr s
on s.invoice_num=invh.invoice_num
inner join address_mstr a
on h.sender_code=a.sender_code;
