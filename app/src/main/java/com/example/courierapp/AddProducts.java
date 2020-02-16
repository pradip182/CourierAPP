package com.example.courierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.courierapp.app.AppConfig;
import com.example.courierapp.app.AppController;
import com.example.courierapp.helper.SQLiteHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddProducts extends AppCompatActivity {
    private static final String TAG = AddProducts.class.getSimpleName();
    private EditText productdescription;
    private EditText productname;
    private EditText pquantity;
    private Button addproducts;
    String description,pname,quantity;
    RequestQueue requestQueue;
    private ProgressDialog pDialog;
    private SQLiteHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        productdescription = findViewById(R.id.productdescription);
        productname = findViewById(R.id.productname);
        pquantity = findViewById(R.id.pquantity);
        addproducts = findViewById(R.id.btnAddProducts);
        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(AddProducts.this);

        pDialog = new ProgressDialog(AddProducts.this);

        // Adding click listener to button.
        addproducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Showing progress dialog at user registration time.
                pDialog.setMessage("Please Wait, We are Inserting Your Data on the Server");
                pDialog.show();

                // Calling method to get value from EditText.
                GetValueFromEditText();

                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_PRODUCTS,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {

                                // Hiding the progress dialog after all task complete.
                                pDialog.dismiss();

                                // Showing response message coming from server.
                                Toast.makeText(AddProducts.this, ServerResponse, Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                                // Hiding the progress dialog after all task complete.
                                pDialog.dismiss();

                                // Showing error message if something goes wrong.
                                Toast.makeText(AddProducts.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {

                    @Override
                    protected Map<String, String> getParams() {

                        // Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();

                        // Adding All values to Params.
                        params.put("description", description);
//                        params.put("access_token", "7ACFdsd328BEA81sssdfgg556B91");
                        params.put("pname", pname);
                        params.put("quantity", quantity);

                        return params;
                    }

                };

                // Creating RequestQueue.
                RequestQueue requestQueue = Volley.newRequestQueue(AddProducts.this);

                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);
                // Launch login activity
                Intent intent = new Intent(
                        AddProducts.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    // Creating method to get value from EditText.
    public void GetValueFromEditText(){

        description = productdescription.getText().toString().trim();
      pname = productname.getText().toString().trim();
        quantity = pquantity.getText().toString().trim();

    }

}