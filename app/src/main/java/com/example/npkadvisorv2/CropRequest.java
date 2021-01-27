package com.example.npkadvisorv2;

import android.text.Editable;

public class CropRequest {
    private String CNombre;

    public String getCNombre() {
        return CNombre;
    }

    public void setCNombre(String CNombre) {
        this.CNombre = CNombre;
    }

    public float getCArea() {
        return CArea;
    }

    public void setCArea(float CArea) {
        this.CArea = CArea;
    }

    private float CArea;
}
