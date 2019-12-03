package com.codegym.c0319h2.quanlycongty.repository.Company;

import com.codegym.c0319h2.quanlycongty.model.Relationship.Relationship;
import com.codegym.c0319h2.quanlycongty.model.company.Company;
import com.codegym.c0319h2.quanlycongty.model.technology.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository< Company,Long> {
    @Query("select c from Company c where c.active = true")
    Iterable<Company> finByActive();

    Company findAllByCompanyName(String companyName);

//    Iterable<Company> findAllByRelationship(Relationship relationship);

    Iterable<Company> findAllByTechnologyAndRelationship(Technology technologies,Relationship relationship);

//    @Query("select c from Company c where lower(c.companyName) like %:companyName%")
//    Iterable<Company> findAllByCompanyNameContaining(@Param("companyName") String companyName);


    @Query("select c from Company c where lower(c.companyName) like %:companyName% and c.relationship in (:relationship)")
    Iterable<Company> findAllByCompanyNameAndRelationshipContaining(@Param("companyName") String companyName,@Param("relationship") Relationship relationship);

//    @Query("SELECT c1.company_id from company_technology( SELECT ct FROM company_technology ct WHERE technology_id IN (1,2) ) c1 GROUP BY c1.company_id having COUNT(c1.company_id) = 2")
//    List<Integer> findAllIdCompany( Technology[] technology);
}
