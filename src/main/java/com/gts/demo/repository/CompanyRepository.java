package com.gts.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gts.demo.beans.Company;

@Transactional(readOnly = true)
public interface CompanyRepository<T, ID extends Serializable> extends
		JpaRepository<Company, Long> {

	@Query("select c from Company c where c.id= ?1")
	List<Company> findTodosById(Long id);

	@Query("select c from Company c where c.isDeleted!= '1'")
	List<Company> findAllActiveCompany();
}
