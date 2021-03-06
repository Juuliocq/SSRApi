package com.devsuperior.dsvendas.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsvendas.api.dto.SellerSalesSuccessDTO;
import com.devsuperior.dsvendas.api.dto.SellerSalesSumDTO;
import com.devsuperior.dsvendas.domain.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{

	@Query("SELECT new com.devsuperior.dsvendas.api.dto.SellerSalesSumDTO(obj.seller, SUM(obj.amount)) FROM Sale AS obj GROUP BY obj.seller")
	List<SellerSalesSumDTO> amountGroupedBySeller();
	
	@Query("SELECT new com.devsuperior.dsvendas.api.dto.SellerSalesSuccessDTO(obj.seller, SUM(obj.visited), SUM(obj.deals)) FROM Sale AS obj GROUP BY obj.seller")
	List<SellerSalesSuccessDTO> successGroupedBySeller();
}
