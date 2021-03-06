package com.devsuperior.dsvendas.api.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsvendas.api.dto.SaleDTO;
import com.devsuperior.dsvendas.api.dto.SellerSalesSuccessDTO;
import com.devsuperior.dsvendas.api.dto.SellerSalesSumDTO;
import com.devsuperior.dsvendas.api.mappers.SaleMapper;
import com.devsuperior.dsvendas.domain.services.SaleService;
import com.devsuperior.dsvendas.domain.services.SaleSuccessService;
import com.devsuperior.dsvendas.domain.services.SellerSalesSumService;

@RestController
@RequestMapping("/sales")
public class SaleController {

	private SaleService saleService;
	private SellerSalesSumService saleSumService;
	private SaleSuccessService saleSuccessService;
	private SaleMapper saleMapper;
	
	public SaleController(SaleService saleService, SaleMapper saleMapper, SellerSalesSumService saleSumService,
						SaleSuccessService saleSuccessService) {
		
		this.saleService = saleService;
		this.saleMapper = saleMapper;
		this.saleSumService = saleSumService;
		this.saleSuccessService = saleSuccessService;
	}
	
	@GetMapping
	public ResponseEntity<Page<SaleDTO>> findAll(Pageable pageable){
		Page<SaleDTO> list = saleMapper.toCollectionDTO(saleService.findAll(pageable));
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/sum-by-seller")
	public ResponseEntity<List<SellerSalesSumDTO>> amountGroupedBySeller(){
		List<SellerSalesSumDTO> list = saleSumService.amountGroupedBySeller();
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/success-by-seller")
	public ResponseEntity<List<SellerSalesSuccessDTO>> successGroupedBySeller(){
		List<SellerSalesSuccessDTO> list = saleSuccessService.successGroupedBySeller();
		
		return ResponseEntity.ok(list);
	}
}