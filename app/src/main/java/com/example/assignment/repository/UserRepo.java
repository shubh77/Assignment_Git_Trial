package com.example.assignment.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.assignment.db.UserDatabase;
import com.example.assignment.db.dao.UserDao;
import com.example.assignment.model.UserModel;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserRepo {

    private UserDao userDao;
    private Flowable<List<UserModel>> allUsers;

    public UserRepo(Application application){
        userDao = UserDatabase.getInstance(application).userDao();
        allUsers = userDao.getAll();
    }

    /**
     * This method is used for inserting data into DB.
     * @param user, contains actual User - info.
     */
    public void insert(UserModel user){
//        insertObject(user);
//        insertObjects(user);
        insertUser(user);
    }

    /**
     * This method is used for updating the same record.
     * @param user, with this new User Object data.
     */
    public void update(UserModel user){
        Completable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                userDao.update(user);
                return true;
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void delete(UserModel user){
        Completable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                userDao.delete(user);
                return true;
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void deleteAllUsers(){
        Completable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                userDao.deleteAll();
                return true;
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public Flowable<List<UserModel>> getAllUsers(){
        return allUsers;
    }

    /**
     * Inserting User object into DB Using "Completable".
     * @param user, Actual record.
     */
    public void insertUser(UserModel user) {
        Completable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                userDao.insert(user);
                return true;
            }
        })
                .subscribeOn(Schedulers.io());
//                .subscribe();
    }

    /**
     * Inserting User object into DB Using "Single".
     * @param user, Actual record.
     */
    public void insertObject(UserModel user) {
        Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                userDao.insert(user);
                return true;
            }
        }).subscribeOn(Schedulers.io())
        .subscribe();
    }

    /**
     * Inserting User object into DB Using "Maybe".
     * @param user, Actual record.
     */
    public Maybe<Boolean> insertObjects(UserModel user) {
        return  Maybe.create(new MaybeOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<Boolean> emitter) throws Exception {
                userDao.insert(user);
                emitter.onSuccess(true);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    private static class DeleteUserAsyncTask extends AsyncTask<UserModel, Void, Void> {

        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserModel... users) {
            userDao.delete(users[0]);
            return null;
        }
    }
    private static class DeleteAllUserAsyncTask extends AsyncTask<Void, Void, Void> {

        private UserDao userDao;

        private DeleteAllUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAll();
            return null;
        }
    }
}
