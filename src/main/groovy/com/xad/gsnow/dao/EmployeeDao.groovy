package com.xad.gsnow.dao

import com.xad.gsnow.entity.Employee

@Singleton
class EmployeeDao {
	// need to add constructor for Guice
	EmployeeDao() {}
	
	private Map store = [
		"1": new Employee(id: 1, firstName: "Fred",   lastName: "Flintstone"),
		"2": new Employee(id: 2, firstName: "Barney", lastName: "Rubble")
	];

	private Long idSeq = 3;
	
	Employee createNewEmployee(firstName, lastName) {
		store[idSeq.toString()] = new Employee(id: idSeq, firstName: firstName, lastName: lastName, wage: 10000.00);
		return store[idSeq];
		idSeq++;
	}
	
	Employee get(id) {
		def e = store[id];
		if(e) {
			return e;
		}
		throw new DaoException();
	}
	
	List list() {
		return store.values();
	}
	
	
}
