package dinhnguyen.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;

import dinhnguyen.demo.entity.Company;

public interface CompanyService {
		
	public Page<Company> paging(int pageNo, int pageSize) ;

	public List<String> list();

	public Page<Company> search(String name, String address, int pageNo, int pageSize);

	public void saveMultiple(List<Company> list);

}
