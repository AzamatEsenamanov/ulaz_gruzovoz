package kg.gruzovoz.login;

import kg.gruzovoz.models.Login;
import kg.gruzovoz.models.User;
import kg.gruzovoz.network.CargoService;
import kg.gruzovoz.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.LoginPresenter {

    LoginContract.LoginView loginView;
    CargoService service = RetrofitClientInstance.getRetrofitInstance().create(CargoService.class);

    public LoginPresenter(LoginContract.LoginView loginView){
        this.loginView = loginView;
    }

    @Override
    public void login(String phoneNumber, String password) {

        Login login = new Login(phoneNumber,password);
        Call<User> call =  service.login(login);
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    loginView.addAuthToken(String.format("%s", response.body().getToken()));
                } else {
                    if (loginView.isConnected()) {
                        loginView.showLoginError();
                    } else {
                        loginView.showErrorToast();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                if (loginView.isConnected()) {
                    loginView.showLoginError();
                } else {
                    loginView.showErrorToast();
                }
            }
        });

    }



}
