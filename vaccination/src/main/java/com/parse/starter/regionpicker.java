package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class regionpicker extends AppCompatActivity {

    String userregion;

    public void sendloc(View view)
    {
      //  ParseUser user = new ParseUser();
        ParseQuery<ParseObject> parentuser = ParseQuery.getQuery("parentuser");

        parentuser.whereEqualTo("PhoneNumber", getIntent().getStringExtra("phoneno"));
        parentuser.findInBackground(new FindCallback<ParseObject>( ) {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
               if(e==null)
               {
                   if(objects.size()>0)
                   {
                       Toast.makeText(regionpicker.this,userregion,Toast.LENGTH_LONG).show();
                       for (ParseObject object : objects)
                       {
                           object.put("region",userregion);
                           object.saveInBackground(new SaveCallback( ) {
                               @Override
                               public void done(ParseException e) {

                                   if(e==null)
                                   {
                                       Toast.makeText(regionpicker.this,"region saved ",Toast.LENGTH_SHORT).show();

                                   }
                                   else
                                   {
                                       Toast.makeText(regionpicker.this," ni hua region saved ",Toast.LENGTH_SHORT).show();

                                   }

                               }
                           });
                       }
                   }
               }

            }
        });




    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regionpicker);

        Spinner dynamicSpinner = (Spinner) findViewById(R.id.dynamic_spinner);

        String[] items = new String[] { "Rajasthan", "Madhya pradesh" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        dynamicSpinner.setAdapter(adapter);

        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Toast.makeText(regionpicker.this,(String) parent.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
                Spinner citySpinner = (Spinner) findViewById(R.id.spinner_city);
                String[] city = new String[]{" "};
                if(position==0)
                {
                    city= new String[]{"jaipur","udaipur"};
                }
                if(position==1)
                {
                    city= new String[]{"indore","ujjain"};
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(regionpicker.this,
                        android.R.layout.simple_spinner_item, city);



                citySpinner.setAdapter(adapter);

                citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener( ) {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        Toast.makeText(regionpicker.this,(String) adapterView.getItemAtPosition(i)+" tt",Toast.LENGTH_SHORT).show();
                        Spinner regionSpinner = (Spinner) findViewById(R.id.spinner_region);
                        String[] region = new String[]{" "};
                        if(adapterView.getItemAtPosition(i).toString().matches("jaipur"))
                        {
                            region= new String[]{"Mansarovar","vaishali nagar","manvi nagar"};
                        }
                        if(adapterView.getItemAtPosition(i).toString().matches("udaipur"))
                        {
                            region= new String[]{"eklavya colony","godli","ISWL"};
                        }
                        if(adapterView.getItemAtPosition(i).toString().matches("indore"))
                        {
                            region= new String[]{"Vijay nagar","khajrana","patnipura"};
                        }
                        if(adapterView.getItemAtPosition(i).toString().matches("ujjain"))
                        {
                            region= new String[]{"nanakheda","lal gate","vip colony"};
                        }

                        Spinner regionspinner11 = (Spinner) findViewById(R.id.spinner_region);
                        userregion=String.valueOf(regionspinner11.getSelectedItem());
                            Toast.makeText(regionpicker.this,userregion+" value",Toast.LENGTH_LONG).show();
                      // String  region= String.valueOf(regionSpinner.getSelectedItem());



                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(regionpicker.this,
                                android.R.layout.simple_spinner_item, region);

                        regionSpinner.setAdapter(adapter);

                        regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener( ) {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                userregion=adapterView.getItemAtPosition(i).toString();
                                Toast.makeText(regionpicker.this,userregion+" selected",Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });








    }
}
