package com.example.assignment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.adapter.UserAdapter;
import com.example.assignment.adapter.UserListRecyclerViewAdapter;
import com.example.assignment.db.dao.UserDao;
import com.example.assignment.model.PageViewModel;
import com.example.assignment.model.UserModel;
import com.example.assignment.repository.UserRepo;
import com.example.assignment.view.MainActivity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A fragment representing a list of Items.
 */
public class UsersListFragment extends Fragment {

    RecyclerView recyclerView;
    private PageViewModel pageViewModel;
    private UserRepo userRepo;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UsersListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static UsersListFragment newInstance(int columnCount) {
        UsersListFragment fragment = new UsersListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userRepo = new UserRepo(requireActivity().getApplication());
        pageViewModel = ViewModelProviders.of(requireActivity()).get(PageViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users_list_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
             recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            getUsersList();

        }
        return view;
    }

    @Override
    public void onViewCreated(@androidx.annotation.NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pageViewModel.getName().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                getUsersList();
            }
        });
    }


    private UserDao userDao;
    private Flowable<List<UserModel>> allUsers;

    private void getUsersList() {
        Disposable disposable = userRepo.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<UserModel>>() {
                    @Override
                    public void accept(List<UserModel> userModels) throws Exception {
                        recyclerView.setAdapter(new UserListRecyclerViewAdapter(userModels));
//                        recyclerView.setAdapter(adapter);
                    }
                });
//                .subscribe(this::onUserFetched, this::onError);
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}