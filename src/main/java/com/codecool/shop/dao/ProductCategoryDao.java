package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.util.List;

/**
 * <h1>ProductCategoryDao</h1>
 * Data Access Object for ProductCategory Data
 * <p>
 * responsible for manage data about ProductCategories
 * add to and remove from memory or database - depending on the implementation
 * </p>
 *
 * @author  Codewalkers
 * @since   2017-05
 */
public interface ProductCategoryDao {

    /**
     * Adds ProductCategory to the memory or database - depending on the implementation
     *
     * @param  category ProductCategory object which will be added
     */
    void add(ProductCategory category);

    /**
     * Looking for ProductCategory in the memory or database - depending on the implementation
     *
     * @param  id id of the ProductCategory search for
     * @return ProductCategory object with the given id
     */
    ProductCategory find(int id);

    /**
     * Removes ProductCategory from the memory or database - depending on the implementation
     *
     * @param  id id of the ProductCategory which will be removed
     */
    void remove(int id);

    /**
     * Returns all ProductCategories from the memory or database - depending on the implementation
     *
     * @return  List of all ProductCategories
     */
    List<ProductCategory> getAll();

}
