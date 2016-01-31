package com.fichadas.fichada;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fichadas.fichada.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class pagina1 extends Fragment {


    private Integer ES = 0;

    public pagina1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pagina1, container, false);
    }



}
