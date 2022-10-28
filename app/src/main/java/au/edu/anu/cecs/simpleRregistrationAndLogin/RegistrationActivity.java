package au.edu.anu.cecs.simpleRregistrationAndLogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {
    private EditText username;
    private EditText email;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mUserRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = (EditText) findViewById(R.id.username_input);
        email = (EditText) findViewById(R.id.email_input);
        Button DoneButton = (Button) findViewById(R.id.buttonDone);

        mDatabase = FirebaseDatabase.getInstance();
        mUserRef = mDatabase.getReference("users");

        DoneButton.setOnClickListener(listner);

    }

    private View.OnClickListener listner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String user_name = username.getText().toString();
            String _email = email.getText().toString();
            User user = new User(user_name,_email);
            mUserRef.push().setValue(user);
            Intent intent = new Intent(RegistrationActivity.this,Dashboard.class);
            startActivity(intent);
        }
    };
}