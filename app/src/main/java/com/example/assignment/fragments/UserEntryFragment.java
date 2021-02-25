package com.example.assignment.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.assignment.R;
import com.example.assignment.databinding.FragmentUserEntryBinding;
import com.example.assignment.model.PageViewModel;
import com.example.assignment.model.UserModel;
import com.example.assignment.repository.UserRepo;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserEntryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserEntryFragment extends Fragment {


    FragmentUserEntryBinding binding;
    private PageViewModel pageViewModel;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private UserRepo userRepo;



    public UserEntryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserEntryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserEntryFragment newInstance(String param1, String param2) {
        UserEntryFragment fragment = new UserEntryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(requireActivity()).get(PageViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_entry,container,false);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.etName.getText().toString().equalsIgnoreCase("") && !binding.etPhoneNo.getText().toString().equalsIgnoreCase("") && !binding.etDob.getText().toString().equalsIgnoreCase(""))
                {
                    UserModel user = new UserModel(binding.etName.getText().toString(),binding.etDob.getText().toString(),
                            binding.etPhoneNo.getText().toString());
                    userRepo = new UserRepo(requireActivity().getApplication());
                    userRepo.insert(user);

                    /*pageViewModel.setName(""+System.currentTimeMillis());

                    binding.etName.setText("");
                    binding.etPhoneNo.setText("");
                    binding.etDob.setText("");
                    binding.invalidateAll();*/

//                    AppDatabase db = DatabaseClient.getmInstance(getActivity()).getAppDatabase();

                    /*Completable.fromAction(new Action() {
                        @Override
                        public void run() throws Exception {
                            UserModel user = new UserModel(binding.etName.getText().toString(),binding.etDob.getText().toString(),
                                    binding.etPhoneNo.getText().toString());
                            db.userDAO().insert(user);
                        }
                    }).observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onComplete() {

                            pageViewModel.setName(""+System.currentTimeMillis());

                            binding.etName.setText("");
                            binding.etPhoneNo.setText("");
                            binding.etDob.setText("");
                            binding.invalidateAll();
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }
                    });*/

                }else{
                    Toast.makeText(getActivity(),"Please enter all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}