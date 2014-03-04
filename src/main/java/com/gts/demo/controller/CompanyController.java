package com.gts.demo.controller;

import com.gts.demo.beans.Company;
import com.gts.demo.service.ICompanyService;
import com.sun.media.sound.JavaSoundAudioClip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {

	@Autowired
	private ICompanyService companyService;

	@RequestMapping("companieslist.json")
	public @ResponseBody
	LinkedList<Company> getCompanyList() {

		return companyService.getAllCompanies();
	}

	@RequestMapping(value = "/addCompany", method = RequestMethod.POST)
	public @ResponseBody
	void addCompany(@RequestBody Company company) {
		company.setId(null);
		companyService.addCompany(company);
	}

	@RequestMapping(value = "/checkCompanyId/{id}", method = RequestMethod.POST)
	public @ResponseBody
	Boolean checkCompany(@PathVariable("id") String companyId){
		return companyService.checkCompanyId(companyId);
	}

	@RequestMapping(value = "/updateCompany", method = RequestMethod.PUT)
	public @ResponseBody
	void updateCompany(@RequestBody Company company) {
		companyService.updateCompany(company);
	}

	@RequestMapping(value = "/removeCompany/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	void removeCompany(@PathVariable("id") Long id) {
		companyService.deleteCompanyById(id);
	}

	@RequestMapping(value = "/removeAllCompany", method = RequestMethod.DELETE)
	public @ResponseBody
	void removeAllCompany() {
		companyService.deleteAll();
	}

	@RequestMapping("/layout")
	public String getCompanyPartialPage(ModelMap modelMap) {
		return "companies/layout";
	}
}
