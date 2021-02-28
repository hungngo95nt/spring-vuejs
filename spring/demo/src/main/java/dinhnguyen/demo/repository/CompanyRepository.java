package dinhnguyen.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dinhnguyen.demo.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Query(value = "Select company.address from Company company", nativeQuery = true)
	public List<String> listCity();
	
	
	@Query("SELECT company FROM Company company "
			+  " WHERE company.name like  %:name% OR company.address like  %:address% ")
    Page<Company> searchCompanies(@Param("name") String name, @Param("address") String address, Pageable page);
	

}
