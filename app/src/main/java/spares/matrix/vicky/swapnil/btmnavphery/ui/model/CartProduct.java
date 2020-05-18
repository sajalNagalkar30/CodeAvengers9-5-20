package spares.matrix.vicky.swapnil.btmnavphery.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartProduct {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("userId")
    @Expose
    private String userId;


    @SerializedName("response")
    @Expose
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    @SerializedName("orderDate")
    @Expose
    private String orderDate1;
    @SerializedName("paymentMethod")
    @Expose
    private String paymentMethod;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @SerializedName("orders")
    @Expose
    private List<CartList> orders = null;

    public List<CartList> getOrders() {
        return orders;
    }

    public void setOrders(List<CartList> orders) {
        this.orders = orders;
    }


    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String toString() {
        return "CartProduct{" +
                "response='" + response + '\'' +
                ", userId='" + userId + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", orderDate1='" + orderDate1 + '\'' +




                '}';
    }

}
