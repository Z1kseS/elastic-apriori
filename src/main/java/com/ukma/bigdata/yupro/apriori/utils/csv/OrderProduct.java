package com.ukma.bigdata.yupro.apriori.utils.csv;

public class OrderProduct {

    private String orderId;
    private String productId;
    private String addToCartOrder;

    public OrderProduct(String orderId, String productId, String addToCartOrder) {
        this.orderId = orderId;
        this.productId = productId;
        this.addToCartOrder = addToCartOrder;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAddToCartOrder() {
        return addToCartOrder;
    }

    public void setAddToCartOrder(String addToCartOrder) {
        this.addToCartOrder = addToCartOrder;
    }

    @Override
    public String toString() {
        return "OrderProduct{" + "orderId=" + orderId + ", productId="
                + productId + ", addToCartOrder=" + addToCartOrder + '}';
    }
}
