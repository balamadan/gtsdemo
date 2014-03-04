package com.gts.demo.service;

import com.gts.demo.beans.Company;

import java.util.LinkedList;
import java.util.List;


public interface ICompanyService {
    public LinkedList<Company> getAllCompanies();

    public Company getCompanyById(Long id);

    public void addCompany(Company company);

    public void deleteCompanyById(Long id);

    public void deleteAll();

    public void updateCompany(Company company);

	Company saveExpenseCategory(Company company);

	void updateExpenseCategory(Company company);

	Company findByCompanyId(Long id);

	List<Company> findAllCompany();
	Boolean checkCompanyId(String companyId);
}
