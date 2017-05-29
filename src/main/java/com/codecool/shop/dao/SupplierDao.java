package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.List;
/**
 * <h1>SupplierDao</h1>
 * Data Access Object for Supplier Data
 * <p>
 * responsible for manage data about Suppliers
 * add to and remove from memory or database - depending on the implementation
 * </p>
 *
 * @author  Codewalkers
 * @since   2017-05
 */
public interface SupplierDao {

    /**
     * Adds Supplier to the memory or database - depending on the implementation
     *
     * @param  supplier Supplier object which will be added
     */
    void add(Supplier supplier);

    /**
     * Looking for Supplier in the memory or database - depending on the implementation
     *
     * @param  id id of the Supplier search for
     * @return Supplier object with the given id
     */
    Supplier find(int id);

    /**
     * Removes Supplier from the memory or database - depending on the implementation
     *
     * @param  id id of the Supplier which will be removed
     */
    void remove(int id);

    /**
     * Returns all Suppliers from the memory or database - depending on the implementation
     *
     * @return  List of all Suppliers
     */
    List<Supplier> getAll();
}
