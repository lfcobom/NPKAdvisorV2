 package com.example.npkadvisorv2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

 /**
 * A simple {@link Fragment} subclass.
 * Use the {@link Delete_Check#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Delete_Check extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Spinner spinnerdelete1;

    public Delete_Check() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Delete_Check.
     */
    // TODO: Rename and change types and number of parameters
    public static Delete_Check newInstance(String param1, String param2) {
        Delete_Check fragment = new Delete_Check();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete__check, container, false);
        spinnerdelete1 = view.findViewById(R.id.spinnerdelete);
        DeleteCrop();
        // Inflate the layout for this fragment
        return view;
    }

     public void DeleteCrop() {
         Call<CropResponse> cropResponseCall = ApiClient.getUserService().findAllC();
         cropResponseCall.enqueue(new Callback<CropResponse>() {
             @Override
             public void onResponse(Call<CropResponse> call, Response<CropResponse> response) {
                 if (response.isSuccessful()) {
                     ArrayList<CropResponse2> cropResponses2 = response.body().getCultivosBuscados();
                     for (int i = 0; i < cropResponses2.size(); i++) {
                         Log.d(TAG, "onResponse: \n " +
                                 "Cultivo " + cropResponses2.get(i).getCNombre());
                     }
                     ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, cropResponses2);
                     spinnerdelete1.setAdapter(adaptador);
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