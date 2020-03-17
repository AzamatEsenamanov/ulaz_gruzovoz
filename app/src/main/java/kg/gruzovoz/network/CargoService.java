package kg.gruzovoz.network;

import java.util.List;

import kg.gruzovoz.models.AcceptOrder;
import kg.gruzovoz.models.FinishOrder;
import kg.gruzovoz.models.Login;
import kg.gruzovoz.models.Order;
import kg.gruzovoz.models.User;
import kg.gruzovoz.models.UserPage;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CargoService {



    @POST("/auth/login/")
    Call<User> login(@Body Login login);

    @PUT("/order/done/{id}/")
    Call<Void> acceptOrder(@Path("id") long id, @Header("Authorization") String authToken, @Body AcceptOrder acceptOrder);

    @GET("/order/user-orders/")
    Call<List<Order>> getOrdersHistory(@Header("Authorization") String authToken, @Query("search") boolean isDone);

    @PUT("order/done/{id}/")
    Call<Void> finishOrder(@Path("id") long id, @Header("Authorization") String authToken, @Body FinishOrder finishOrder);

    @GET("/order/all/")
    Call<List<Order>> getAllOrders(@Header("Authorization") String authToken);

    @GET("/auth/driver/")
    Call<UserPage> getPersonalData(@Header("Authorization") String authToken);
}
