package com.ecommerce.global;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;

import com.ecommerce.entity.Product;

public class GlobalData {

	public static List<Product> cart;
	
	static {
		cart = new ArrayList<Product>();
	}
}
