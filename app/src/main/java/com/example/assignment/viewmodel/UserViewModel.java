package com.example.assignment.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.assignment.model.UserModel;
import com.example.assignment.repository.UserRepo;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends AndroidViewModel {
    private static final String TAG = "UserViewModel";
    private UserRepo userRepo;
    private MutableLiveData<List<UserModel>> allUsers = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public UserViewModel(@NonNull Application application) {
        super(application);

        userRepo = new UserRepo(application);
        getUsers();
    }

    private void getUsers(){
        Disposable disposable = userRepo.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onUserFetched, this::onError);

        compositeDisposable.add(disposable);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    private void onError(Throwable throwable) {
        Log.e(TAG, "onError: ",throwable);
    }

    private void onUserFetched(List<UserModel> users) {
        this.allUsers.setValue(users);
    }


    public void insert(UserModel user){
        userRepo.insert(user);
    }
    public void update(UserModel user){
        userRepo.update(user);
    }
    public void delete(UserModel user){
        userRepo.delete(user);
    }
    public void deleteAll(){
        userRepo.deleteAllUsers();
    }

    public LiveData<List<UserModel>> getAllUsers(){
        return allUsers;
    }
}
