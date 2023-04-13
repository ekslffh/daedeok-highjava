package dao;

import java.util.List;

public interface DAOIfs<T> {
	List<T> findAll(); // select * from bus_company
	T findById(String id); // select * from bus_company where c_code=1
	int insert(T vo); // insert into bus_company (c_code, c_name, c_address, c_telno) values ('1','나성민', '월평동 1234','01084819654')
	int update(T vo); // update bus_company set c_name='홍길동' where c_code = ?;
	int deleteById(String id); // delete from bus_company where c_code = 1
}
