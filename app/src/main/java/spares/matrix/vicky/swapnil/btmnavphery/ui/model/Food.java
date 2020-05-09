package spares.matrix.vicky.swapnil.btmnavphery.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Food {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;


    @SerializedName("Fruits_count")
    @Expose
    private Integer fruitsCount;
    @SerializedName("Fruits")
    @Expose
    private List<GeneralFood> fruits = null;

    @SerializedName("Spices_count")
    @Expose
    private Integer spicesCount;
    @SerializedName("Spices")
    @Expose
    private List<GeneralFood> spices = null;

    @SerializedName("Grains_count")
    @Expose
    private Integer grainsCount;
    @SerializedName("Grains")
    @Expose
    private List<GeneralFood> grains = null;
    @SerializedName("Vegetables_count")
    @Expose
    private Integer vegetablesCount;
    @SerializedName("Vegetables")
    @Expose
    private List<GeneralFood> vegetables = null;

    @SerializedName("Offers_count")
    @Expose
    private Integer offersCount;
    @SerializedName("Offers")
    @Expose
    private List<Offer> offers = null;

    @SerializedName("Category_count")
    @Expose
    private Integer categoryCount;
    @SerializedName("Category")
    @Expose
    private List<Category> category = null;


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getVegetablesCount() {
        return vegetablesCount;
    }

    public void setVegetablesCount(Integer vegetablesCount) {
        this.vegetablesCount = vegetablesCount;
    }

    public List<GeneralFood> getVegetables() {
        return vegetables;
    }

    public void setVegetables(List<GeneralFood> vegetables) {
        this.vegetables = vegetables;
    }
    public Integer getFruitsCount() {
        return fruitsCount;
    }

    public void setFruitsCount(Integer fruitsCount) {
        this.fruitsCount = fruitsCount;
    }

    public List<GeneralFood> getFruits() {
        return fruits;
    }

    public void setFruits(List<GeneralFood> fruits) {
        this.fruits = fruits;
    }


    public Integer getSpicesCount() {
        return spicesCount;
    }

    public void setSpicesCount(Integer spicesCount) {
        this.spicesCount = spicesCount;
    }

    public List<GeneralFood> getSpices() {
        return spices;
    }

    public void setSpices(List<GeneralFood> spices) {
        this.spices = spices;
    }

    public Integer getGrainsCount() {
        return grainsCount;
    }

    public void setGrainsCount(Integer grainsCount) {
        this.grainsCount = grainsCount;
    }

    public List<GeneralFood> getGrains() {
        return grains;
    }

    public void setGrains(List<GeneralFood> grains) {
        this.grains = grains;
    }


    public Integer getOffersCount() {
        return offersCount;
    }

    public void setOffersCount(Integer offersCount) {
        this.offersCount = offersCount;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public Integer getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(Integer categoryCount) {
        this.categoryCount = categoryCount;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }



}

  