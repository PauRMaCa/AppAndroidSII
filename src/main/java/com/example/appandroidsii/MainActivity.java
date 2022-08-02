package com.example.appandroidsii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.appandroidsii.utilities.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    
    Button          searchButton;EditText    idInput;
    TextView        idEmployeeView, nameEmployeeView, ageEmployeeView, salaryEmployeeView,
          annualSalaryEmployeeView;
    String          url;
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        //Assign the control to each element
        idInput                     = findViewById(R.id.idInput);
        searchButton                = findViewById(R.id.searchButton);
        idEmployeeView              = findViewById(R.id.idEmployee);
        nameEmployeeView            = findViewById(R.id.nameEmployee);
        ageEmployeeView             = findViewById(R.id.ageEmployee);
        salaryEmployeeView          = findViewById(R.id.salaryEmployee);
        annualSalaryEmployeeView    = findViewById(R.id.annualSalaryEmployee);
        
        //Listener for the button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                //Check the input to search in the URL specified
                if (idInput.getText().toString().isEmpty()){
                    url = getString(R.string.employeeURL) + "s";
                } else
                    url = getString(R.string.employeeURL) + "/" + idInput.getText().toString();
                
                // Request a string response from the provided URL.
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                      null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray dataEmployees = new JSONArray();
                        String idString = "", nameString = "", ageString = "", salaryString = "",
                              annualSalaryString = "", indents ="";
                        try {
                            if (idInput.getText().toString().isEmpty()){
                                dataEmployees = response.getJSONArray("data");
                                // -> [ {}, {}, {}, ]
                            } else{
                                JSONObject data = response.getJSONObject("data");
                                // -> { ... }
                                dataEmployees.put(data);
                            }
                            for (int i = 0; i < dataEmployees.length(); i++){
                                indents += "/n" + dataEmployees.getJSONObject(i).
                                      getString("id");
                                idString += "\n" + dataEmployees.getJSONObject(i).
                                      getString("id");
                                nameString += "\n" + dataEmployees.getJSONObject(i).
                                      getString("employee_name");
                                ageString += "\n" + dataEmployees.getJSONObject(i).
                                      getString("employee_age");
                                int salary = Integer.parseInt(dataEmployees.
                                      getJSONObject(i).getString("employee_salary"));
                                salaryString += "\n" + salary;
                                annualSalaryString += ("\n" + (salary * 12));
                            }
                            System.out.println(indents);
                            idEmployeeView.setText(idString);
                            nameEmployeeView.setText(nameString);
                            ageEmployeeView.setText(ageString);
                            salaryEmployeeView.setText(salaryString);
                            annualSalaryEmployeeView.setText(annualSalaryString);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, getString(R.string.badResponse),
                              Toast.LENGTH_SHORT).show();
            
                    }
                });
    
                MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);
    
            }
        });
    }
    
}
