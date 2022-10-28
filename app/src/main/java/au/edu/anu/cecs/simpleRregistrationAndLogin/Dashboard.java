package au.edu.anu.cecs.simpleRregistrationAndLogin;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    private ArrayList<String> my_list;
    private ArrayList<User> my_list_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ListView list = (ListView) findViewById(R.id.userList);
        my_list = new ArrayList<>();
        my_list_user = new ArrayList<>();

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mPostRef = mDatabase.getReference("users");

        mPostRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                my_list_user.add(0, user);
                my_list.add(0, "Username: " + user.getUsername() + "\n\n" + "Email id: " + user.getEmail());
                ArrayAdapter listView = new ArrayAdapter(getApplicationContext(), R.layout.simple_list_item_1, my_list);
                list.setAdapter(listView);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mPostRef.get().addOnSuccessListener(dataSnapshot -> {
            for (DataSnapshot ds: dataSnapshot.getChildren())
            {
                User user = ds.getValue(User.class);
                my_list_user.add(0, user);
                my_list.add(0, "Username: " + user.getUsername() + "\n\n" + "Email id: " + user.getEmail());
            }
            ArrayAdapter listView = new ArrayAdapter(getApplicationContext(), R.layout.simple_list_item_1, my_list);
            list.setAdapter(listView);

        });

        Button goBack = (Button) findViewById(R.id.buttonGoBack);
        goBack.setOnClickListener(view -> {
            Intent intent = new Intent(Dashboard.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }
}