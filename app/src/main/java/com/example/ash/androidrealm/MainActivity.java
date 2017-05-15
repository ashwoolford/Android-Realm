package com.example.ash.androidrealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    EditText editText1, editText2;
    TextView textView;
    Button button;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

        Realm.init(this);
        realm = Realm.getDefaultInstance();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        getData();


    }

    public void insertData(){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Persons persons = realm.createObject(Persons.class);
                int key;
                try {
                    key = realm.where(Persons.class).max("id").intValue() + 1;
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    key = 0;
                }

                persons.setId(key);
                persons.setName(editText1.getText().toString());
                persons.setEmail(editText2.getText().toString());

                //realm.commitTransaction();
                getData();
            }
        });



    }

    public void getData(){
        RealmResults<Persons> results = realm.where(Persons.class).findAll();

        for (int i = 0 ; i < results.size(); i++)
            textView.append(results.get(i).id+" : "+results.get(i).getEmail()+" : "+results.get(i).getName());
    }
}
