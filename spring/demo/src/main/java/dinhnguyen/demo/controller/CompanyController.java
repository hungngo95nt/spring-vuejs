package dinhnguyen.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dinhnguyen.demo.entity.Company;
import dinhnguyen.demo.services.CompanyService;

@RestController
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping("/company/paging/{pageNo}/{pageSize}")
	public ResponseEntity<Page<Company>> paging(@PathVariable("pageNo") int pageNo,
			@PathVariable("pageSize") int pageSize) {
		Page<Company> page = this.companyService.paging(pageNo, pageSize);
		return new ResponseEntity<Page<Company>>(page, HttpStatus.OK);
	}

	@GetMapping("/company/list/citiy")
	public ResponseEntity<List<String>> listCity() {
		List<String> list = this.companyService.list();
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);

	}

	@GetMapping("/company/seach/{name}/{address}/{pageNo}/{pageSize}")
	public ResponseEntity<Page<Company>> search(
			@PathVariable("name") String name,
			@PathVariable("address") String address, 
			@PathVariable("pageNo") int pageNo,
			@PathVariable("pageSize") int pageSize) {
		Page<Company> list = this.companyService.search(name, address, pageNo, pageSize);

		return new ResponseEntity<Page<Company>>(list, HttpStatus.OK);

	}
	
	@PostMapping("/company/commit")
	public ResponseEntity<Object> commit(@RequestBody List<Company> list){
		for (Company company : list) {
			System.out.println(company.getAddress());
		}
		this.companyService.saveMultiple(list);
		return new ResponseEntity<>("OK", HttpStatus.OK);
		
	}
}
