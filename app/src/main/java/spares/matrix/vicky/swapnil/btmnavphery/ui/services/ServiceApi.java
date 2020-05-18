package spares.matrix.vicky.swapnil.btmnavphery.ui.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.CartProduct;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.Food;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.User;

public interface ServiceApi {
    @GET("register.php")
    Call<User> doRegistration(@Query("name") String name, @Query("email") String email, @Query("phone") String phone, @Query("password") String password);

    @GET("order.php")
    Call<CartProduct> dopost1(@Query("userId") String user_id,@Query("paymentMethod") String payment_method,
                             @Query("productId") String product_id,@Query("quantity") String quantity);




    @GET("login.php")
    Call<User> doLogin(@Query("email") String email, @Query("password") String password);


    @GET("Vegetables.php")
    abstract public Call<Food> getFoods();


    @GET("Fruits.php")
    abstract public Call<Food> getFruitFoods();

    @GET("Grains.php")
    abstract public Call<Food> getGrainsFoods();

    @GET("Spices.php")
    abstract public Call<Food> getSpicesFoods();

    @GET("Offers.json")
    abstract public Call<Food> getOfferssFood();

    @GET("Category.json")
    abstract public Call<Food> getCategoryFood();

}
