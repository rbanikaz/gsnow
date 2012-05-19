package com.xad.gsnow.web

import com.britesnow.snow.util.AnnotationMap
import com.britesnow.snow.web.RequestContext
import com.britesnow.snow.web.handler.annotation.WebActionHandler
import com.britesnow.snow.web.handler.annotation.WebModelHandler
import com.britesnow.snow.web.param.annotation.WebModel
import com.britesnow.snow.web.param.annotation.WebParam
import com.britesnow.snow.web.param.resolver.annotation.WebParamResolver
import com.xad.gsnow.dao.EmployeeDao
import com.xad.gsnow.entity.Employee
import com.google.inject.Inject

class EmployeeWebHandlers {
	@Inject
	EmployeeDao employeeDao;
	
	@WebModelHandler(startsWith="/")
	def pageIndex(@WebModel Map m){
		
	}
			
	@WebModelHandler(startsWith="/employee")
	def employeeRequest(@WebModel Map m, @WebParam("employeeId")Employee employee ){
		m["result"] = employee;
	}
	
	@WebModelHandler(startsWith="/employees")
	def employees(@WebModel Map m,  @WebParam("q") String q){
		def list = employeeDao.list();
		
		if(q) {
			return list.findAll({ e ->
				return e.firstName.matches(".*${q}.*") || e.lastName.matches(".*${q}.*") 
			})
		}
		return list;
		
	}
	
	@WebActionHandler
	def giveRaise(@WebModel Map m, @WebParam("employeeId")Employee employee , @WebParam("amt")Double amount) {
		employeeDao.get(id).wage += amount; 
	}
	
	@WebActionHandler
	@WebModelHandler(startsWith="/raiseAll")
	def raiseAll(@WebModel Map m, @WebParam("amt")Double amount) {
		employeeDao.list.each({ e -> e.wage += amount })
	}
	
	
	@WebActionHandler
	def addEmployee(@WebParam("firstName")String firstName, @WebParam("lastName")String lastName){
		employeeDao.createNewEmployee(firstName, lastName);
	}
	
	@WebParamResolver
    Employee resolveEmployee(AnnotationMap annotationMap, Class paramType, RequestContext rc) {
		def id = annotationMap.get(WebParam.class).value();
		return employeeDao.get(rc.getParam(id));
	}
}