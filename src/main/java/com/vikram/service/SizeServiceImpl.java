package com.vikram.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikram.entity.Size;
import com.vikram.repository.SizeRepository;

@Service
public class SizeServiceImpl implements SizeService {
	
	@Autowired
	SizeRepository sizeRepository;

	@Override
	public Size findById(int id) {
		
		return sizeRepository.getById(id);
	}

	@Override
	public List<Size> findAll() {
		
		return sizeRepository.findAll();
	}

	@Override
	public Size saveOrUpdate(Size size) {
		
		List<Size> findByProduct = sizeRepository.findByProduct(size.getProduct());
		if(!findByProduct.isEmpty()) {
			for(Size each :findByProduct ) {
				if(each.getProductSize().equals(size.getProductSize())) {
					each.setStock(each.getStock()+size.getStock());
					return sizeRepository.save(each);
				}
			}
		}
			
		return sizeRepository.save(size);
		
	}

	@Override
	public void removeById(int id) {
		
		sizeRepository.deleteById(id);;

	}


}
