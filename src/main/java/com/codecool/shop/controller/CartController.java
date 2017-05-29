package com.codecool.shop.controller;

import com.codecool.shop.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlling the list of products that are shown on the actual Shopping Cart
 * <p>
 * Responsible for show the products, choosed by the customer.
 *  </p>
 *
 */
public class CartController {
    /**
     * Renders all products from the shopping cart.
     * <p>
     * Returns a Stark ModelAndView object, what contains information about the products to be rendered
     * in the object params. And it sets the name for the route which will call the rendering.
     * <p>
     *
     * @param  req Request object, provides information about the HTTP request
     * @param res Response object, provides information about the HTTP request
     * @param order Order object which contains the lines of the shopping cart
     * @return ModelAndView object to be rendered (it sets the name of the view and the model object to be rendered).
     */

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    public static ModelAndView renderCart(Request req, Response res, Order order) {
//        ProductDao productDataStore = ProductDaoMem.getInstance();
//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Order myOrder = order;
        Map params = new HashMap<>();
//        params.put("order", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        params.put("order", myOrder);
        params.put("lines", myOrder.getOrderLines());
        return new ModelAndView(params, "product/cart");
    }

}
