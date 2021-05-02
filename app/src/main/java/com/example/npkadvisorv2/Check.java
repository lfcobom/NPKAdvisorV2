package com.example.npkadvisorv2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Check#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Check extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Spinner spinner;
    public Check() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Check.
     */
    // TODO: Rename and change types and number of parameters
    @NonNull
    public static Check newInstance(String param1, String param2) {
        Check fragment = new Check();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_check, container, false);
        spinner = view.findViewById(R.id.spinner1);
        ShowCrop();




        // Inflate the layout for this fragment
        return view;
    }
    public void ShowCrop() {
        Call<CropResponse> cropResponseCall = ApiClient.getUserService().findAllC();
        cropResponseCall.enqueue(new Callback<CropResponse>() {
            @Override
            public void onResponse(Call<CropResponse> call, Response<CropResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<CropResponse2> cropResponses = response.body().getCultivosBuscados();
                    for (int i = 0; i < cropResponses.size(); i++) {
                        Log.d(TAG, "onResponse: \n " +
                                "Cultivo " + cropResponses.get(i).getCNombre());
                    }
                        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, cropResponses);
                        spinner.setAdapter(adaptador);
                }
            }
            @Override
            public void onFailure(Call<CropResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
                //System.out.println("causes" + t.fillInStackTrace());
            }
        });
    }
}