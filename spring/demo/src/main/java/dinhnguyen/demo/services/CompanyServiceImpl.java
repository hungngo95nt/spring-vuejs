package dinhnguyen.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dinhnguyen.demo.entity.Company;
import dinhnguyen.demo.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Page<Company> paging(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Company> pagedResult = this.companyRepository.findAll(paging);
		for (Company com : pagedResult) {
			System.out.println(com.getName());
		}
		return pagedResult;
	}

	@Override
	public List<String> list() {
		return this.companyRepository.listCity().stream().distinct().collect(Collectors.toList());

	}

	@Override
	public Page<Company> search(String name, String address, int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		if (name == null || name.trim().length() == 0) {
			name = " ";
		}
		if (address == null || address.trim().length() == 0) {
			address = " ";
		}
		return this.companyRepository.searchCompanies(name.trim(), address.trim(), paging);

	}

	@Override
	public void saveMultiple(List<Company> list) {
		this.companyRepository.saveAll(list);

	}

}
