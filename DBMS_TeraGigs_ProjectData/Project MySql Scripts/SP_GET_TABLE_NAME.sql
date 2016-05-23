CREATE PROCEDURE `SP_GET_TABLE_NAME` (IN str VARCHAR(255),OUT table_name VARCHAR(255))
BEGIN
DECLARE GET_TABL_NAME VARCHAR(200);
set GET_TABL_NAME = 'SELECT table_name FROM TABLE_Mstr';
if get_tabl_name like str or
get_tabl_name like concat('%',str) or
get_tabl_name like concat(str,'%') or
get_table_name like concat('%',str,'%')
then 
set table_name=get_tabl_name;
end if;
END
