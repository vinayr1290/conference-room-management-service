package com.wellsfargo.utilities.crms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellsfargo.utilities.crms.model.Building;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Integer> {
}
