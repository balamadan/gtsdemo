package com.gts.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gts.demo.beans.Company;
import com.gts.demo.repository.CompanyRepository;

@Service("companyService")
public class CompanyService implements ICompanyService {
	private static List<Company> companyList = new ArrayList<Company>();
	private static Long id;

	@Autowired
	CompanyRepository<Company, Long> companyRepository;

	public List<Company> getAllCompanies() {
		return companyRepository.findAllActiveCompany();
	}

	public Company getCompanyById(Long id) {
		return findCompanyById(id);
	}

	public void addCompany(Company company) {
		//company.setId(++id);
		//companyList.add(company);
		companyRepository.saveAndFlush(company);
	}

	public void deleteCompanyById(Long id) {
		//Company foundCompany = findCompanyById(id);
		//System.out.println("Delete  -->>" + id + foundCompany);
		Company foundCompany = companyRepository.findOne(id);
		foundCompany.setIsDeleted(Boolean.TRUE);
		updateCompany(foundCompany);
		/*if (foundCompany != null) {
			System.out.println("Company found-->>");
			companyList.remove(foundCompany);
			id--;
		}*/
	}

	public void deleteAll() {
		companyList.clear();
		id = 0L;
	}

	public void updateCompany(Company company) {
		//Company foundCompany = findCompanyById(company.getId());
		companyRepository.saveAndFlush(company);
		/*if (foundCompany != null) {
			companyList.remove(foundCompany);
			companyList.add(company);
		}*/
	}

	private Company findCompanyById(Long id) {
/*		System.out.println("id -->>" + id);
		for (Company company : companyList) {
			System.out.println("Company id-->" + company.getId());
			if (company.getId().compareTo(id) == 0) {
				return company;
			}
		}
*/		return companyRepository.findOne(id);
	}

	public Company saveExpenseCategory(Company company) {
		// TODO Auto-generated method stub
		return companyRepository.saveAndFlush(company);

	}

	public void updateExpenseCategory(Company company) {
		companyRepository.saveAndFlush(company);
	}

	public Company findByCompanyId(Long id) {
		// TODO Auto-generated method stub
		return companyRepository.findOne(id);
	}

	public List<Company> findAllCompany() {
		return companyRepository.findAll();
	}

	public CompanyRepository<Company, Long> getCompanyRepository() {
		return companyRepository;
	}

	public void setCompanyRepository(
			CompanyRepository<Company, Long> companyRepository) {
		this.companyRepository = companyRepository;
	}

}
