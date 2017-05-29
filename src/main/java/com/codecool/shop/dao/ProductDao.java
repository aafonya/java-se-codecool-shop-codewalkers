package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

/**
 * <h1>ProductDao</h1>
 * Data Access Object for Product Data
 * <p>
 * responsible for manage data about Products
 * add to and remove from memory or database - depending on the implementation
 * </p>
 *
 * @author  Codewalkers
 * @since   2017-05
 */
public interface ProductDao {

    /**
     * Adds Product to the memory or database - depending on the implementation
     *
     * @param  product Product object which will be added
     */
    void add(Product product);

    /**
     * Looking for Product in the memory or database - depending on the implementation
     *
     * @param  id id of the Product search for
     * @return Product object with the given id
     */
    Product find(int id);

    /**
     * Removes Product from the memory or database - depending on the implementation
     *
     * @param  id id of the Product which will be removed
     */
    void remove(int id);

    /**
     * Returns all Products from the memory or database - depending on the implementation
     *
     * @return  List of all Products
     */
    List<Product> getAll();

    /**
     * Returns Products from the memory or database - depending on the implementation
     * filtered by Supplier
     *
     * @return  List of all Products by the given Supplier
     */
    List<Product> getBy(Supplier supplier);

    /**
     * Returns Products from the memory or database - depending on the implementation
     * filtered by ProductCategory
     *
     * @return  List of all Products by the given ProductCategory
     */
    List<Product> getBy(ProductCategory productCategory);

}
