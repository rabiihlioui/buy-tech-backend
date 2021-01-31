package com.buytech.backend.dao;

import java.util.List;

public interface DAO<T> {

	List<T> getProductsByType(String type);
	List<T> getProductsScreenWide(int screen);
	List<T> getProductsByPrice(float minPrice, float maxPrice);
	List<T> getProductsByManufacturer(String manufacturer);
	List<T> getProductsBySortType(String sortType);

}
