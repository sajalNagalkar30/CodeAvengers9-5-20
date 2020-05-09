package spares.matrix.vicky.swapnil.btmnavphery.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralFood {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productCompany")
    @Expose
    private String productCompany;
    @SerializedName("productPrice")
    @Expose
    private String productPrice;

    private Integer count=1;
    @SerializedName("productPriceBeforeDiscount")
    @Expose
    private String productPriceBeforeDiscount;
    @SerializedName("productDescription")
    @Expose
    private Object productDescription;
    @SerializedName("productImage1")
    @Expose
    private String productImage1;
    @SerializedName("shippingCharge")
    @Expose
    private String shippingCharge;
    @SerializedName("productAvailability")
    @Expose
    private String productAvailability;
    @SerializedName("postingDate")
    @Expose
    private String postingDate;
    @SerializedName("updationDate")
    @Expose
    private Object updationDate;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("filepath")
    @Expose
    private String filepath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(String productCompany) {
        this.productCompany = productCompany;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getProductPriceBeforeDiscount() {
        return productPriceBeforeDiscount;
    }

    public void setProductPriceBeforeDiscount(String productPriceBeforeDiscount) {
        this.productPriceBeforeDiscount = productPriceBeforeDiscount;
    }

    public Object getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(Object productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImage1() {
        return productImage1;
    }

    public void setProductImage1(String productImage1) {
        this.productImage1 = productImage1;
    }

    public String getShippingCharge() {
        return shippingCharge;
    }

    public void setShippingCharge(String shippingCharge) {
        this.shippingCharge = shippingCharge;
    }

    public String getProductAvailability() {
        return productAvailability;
    }

    public void setProductAvailability(String productAvailability) {
        this.productAvailability = productAvailability;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    public Object getUpdationDate() {
        return updationDate;
    }

    public void setUpdationDate(Object updationDate) {
        this.updationDate = updationDate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

}