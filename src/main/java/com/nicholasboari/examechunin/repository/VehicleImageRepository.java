package com.nicholasboari.examechunin.repository;

import com.nicholasboari.examechunin.domain.VehicleImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleImageRepository extends JpaRepository<VehicleImage,Long> {

}
