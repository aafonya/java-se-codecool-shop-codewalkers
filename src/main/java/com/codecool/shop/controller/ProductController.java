package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.ProductDaoWithJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoJDBC;
import com.codecool.shop.model.Order;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;
/**
 * Controlling the list of products that are shown
 * <p>
 * Responsible for determining which products to show and
 * how to group them by rendering.
 *  </p>
 *
 */
public class ProductController {

    /**
     * Instance of the Product Data Access Object
     */
    public static ProductDao productDataStore = ProductDaoWithJdbc.getInstance();
    /**
     *Instance of the ProductCategory Data Access Object
     */
    public static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
    /**
     *Instance of the Supplier Data Access Object
     */
    public static SupplierDao supplierDataStore = SupplierDaoJDBC.getInstance();
    //public static Order order = Order.getOrder();

    /**
     * Renders all products from the DataStore
     * <p>
     * Returns a Stark ModelAndView object, what contains information about the products to be rendered
     * in the object params. And it sets the name for the route which will call the rendering.
     * <p>
     *
     * @param  req Request object, provides information about the HTTP request
     * @param res Response object, provides information about the HTTP request
     * @return ModelAndView object to be rendered (it sets the name of the view and the model object to be rendered).
     */
    public static ModelAndView renderProducts(Request req, Response res) {


        Map params = new HashMap<>();
        params.put("totalQua", Order.getOrder(req).getTotalQuantity());
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

    /**
     * Renders products from DataStore filtered by productCategory
     * <p>
     * Returns a Stark ModelAndView object, what contains information about the products to be rendered
     * in the object params. And it sets the name for the route which will call the rendering.
     * <p>
     *
     * @param  req Request object, provides information about the HTTP request
     * @param res Response object, provides information about the HTTP request
     * @param id id of the ProductCategory whose products will be rendered
     * @return ModelAndView object to be rendered (it sets the name of the view and the model object to be rendered).
     */
    public static ModelAndView prodByCategory(Request req, Response res, int id){


        Map params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("category", productCategoryDataStore.find(id));
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(id)));
        return new ModelAndView(params, "product/index");
    }

    /**
     * Renders products from DataStore filtered by Supplier
     * <p>
     * Returns a Stark ModelAndView object, what contains information about the products to be rendered
     * in the object params. And it sets the name for the route which will call the rendering.
     * <p>
     *
     * @param  request Request object, provides information about the HTTP request
     * @param response Response object, provides information about the HTTP request
     * @param id id of the Supplier whose products will be rendered
     * @return ModelAndView object to be rendered (it sets the name of the view and the model object to be rendered).
     */
    public static ModelAndView prodBySupplier(Request request, Response response, int id){


        Map paramsSup = new HashMap<>();
        paramsSup.put("suppliers", supplierDataStore.getAll());
        paramsSup.put("supplier", supplierDataStore.find(id));
        paramsSup.put("products", productDataStore.getBy(supplierDataStore.find(id)));
        return new ModelAndView(paramsSup, "product/index");

    }

}