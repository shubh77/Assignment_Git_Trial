package com.example.assignment.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.assignment.R;
import com.example.assignment.adapter.SectionsPagerAdapter;
import com.example.assignment.adapter.UserAdapter;
import com.example.assignment.model.UserModel;
import com.example.assignment.utils.Constant;
import com.example.assignment.viewmodel.UserViewModel;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
}





    /*public static final int ADD_USER_REQUEST_CODE = 1234;
    public static final int EDIT_USER_REQUEST_CODE = 1235;

    private UserViewModel userViewModel;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final UserAdapter adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, AddEditUserActivity.class);
//                startActivityForResult(intent, ADD_USER_REQUEST_CODE);
                Intent intent = new Intent(MainActivity.this, FragmentViewPagerActivity.class);
                startActivity(intent);
            }
        });

        userViewModel.getAllUsers().observe(this, users -> {
            adapter.submitList(users);
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                userViewModel.delete(adapter.getUserAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "User deleted", Toast.LENGTH_SHORT).show();
            }
        })
        .attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UserModel user) {
                Toast.makeText(MainActivity.this, "onItemClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                final UserModel user = adapter.getUserAt(position);

                PopupMenu menu = new PopupMenu(MainActivity.this,view);
                menu.getMenu().add("Edit");
                menu.show();
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(MainActivity.this, AddEditUserActivity.class);
                        intent.putExtra(Constant.EXTRA_ID, user.getId());
                        intent.putExtra(Constant.EXTRA_NAME, user.getName());
                        intent.putExtra(Constant.EXTRA_ADDRESS, user.getAddress());
                        intent.putExtra(Constant.EXTRA_EMAIL, user.getEmail());
                        intent.putExtra(Constant.EXTRA_PHONE, user.getPhone());
                        startActivityForResult(intent, EDIT_USER_REQUEST_CODE);
                        return true;
                    }
                });
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_USER_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            String name = data.getStringExtra(Constant.EXTRA_NAME);
            String dob = data.getStringExtra(Constant.EXTRA_DOB);
            String phone = data.getStringExtra(Constant.EXTRA_PHONE);

            UserModel user = new UserModel(name, dob, phone);
            userViewModel.insert(user);

            Toast.makeText(this, "User saved", Toast.LENGTH_SHORT).show();

        }else if (requestCode == EDIT_USER_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            int id = data.getIntExtra(Constant.EXTRA_ID, -1);

            if (id== -1){
                Toast.makeText(this, "User can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = data.getStringExtra(Constant.EXTRA_NAME);
            String address = data.getStringExtra(Constant.EXTRA_ADDRESS);
            String email = data.getStringExtra(Constant.EXTRA_EMAIL);
            String phone = data.getStringExtra(Constant.EXTRA_PHONE);

            UserModel user = new UserModel(name,address, email, phone);
            user.setId(id);

            userViewModel.update(user);

            Toast.makeText(this, "User updated", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_delete_all_users:
                userViewModel.deleteAll();
                Toast.makeText(this, "All users deleted", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}*/
