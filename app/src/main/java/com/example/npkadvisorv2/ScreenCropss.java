package com.example.npkadvisorv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.contentcapture.DataShareWriteAdapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScreenCropss extends AppCompatActivity {
    FragmentTransaction transaction;
    Fragment fragmentInicio, fragmentA, fragmentM, fragmentD, fragmentCh;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screencropss);
        fragmentInicio = new InicioFragment();
        fragmentM = new Add_Modify();
        fragmentA = new add_();
        fragmentCh = new Check();
        fragmentD = new Delete_Check();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentAM, fragmentInicio).commit();
    }
    public void onClick(View view)
    {
        transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId())
        {
            case R.id.btn_fAdd: transaction.replace(R.id.contenedorFragmentAM, fragmentA);
            transaction.addToBackStack(null);
                break;
            case R.id.btn_fMod: transaction.replace(R.id.contenedorFragmentAM, fragmentM);
                transaction.addToBackStack(null);
                break;
            case R.id.btn_fdelete:transaction.replace(R.id.contenedorFragmentAM, fragmentD);
                transaction.addToBackStack(null);
                break;
            case R.id.btn_fverify:transaction.replace(R.id.contenedorFragmentAM, fragmentCh);
               transaction.addToBackStack(null);
               break;
        }
       transaction.commit();
    }
}
