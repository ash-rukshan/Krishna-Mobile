package lk.krishan_mobile.asset.brand.dao;

import lk.krishan_mobile.asset.brand.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer> {
    Brand findByName(String nic);
}
