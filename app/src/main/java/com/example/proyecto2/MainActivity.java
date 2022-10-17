package com.example.proyecto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, RadioGroup.OnCheckedChangeListener {
    private LinearLayout layout1,layout2,layout3,layout4;
    private ArrayList<LinearLayout> layouts= new ArrayList<LinearLayout>();
    private RadioButton radio1,radio2;
    private ArrayList<Integer> r= new ArrayList<Integer>();
    private ArrayList<Integer> rPrecio= new ArrayList<Integer>();
    private String[] nombres;
    private RadioGroup grupo1,grupo2,grupo3,grupo4;
    private ArrayList<RadioGroup> grupos= new ArrayList<RadioGroup>();
    private TextView trans,hotel,comida,ocio;
    private String[] text;
    private EditText et1,et2,et3,et4,etP;
    private ArrayList<EditText> edits= new ArrayList<EditText>();
    private int total,precioTransporte,precioComida,precioHotel,precioOcio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vista1();


    }

    private void vista1() {
        edits.add(et1= (EditText) findViewById(R.id.edit1));
        edits.add(et2= (EditText) findViewById(R.id.edit2));
        edits.add(et3= (EditText) findViewById(R.id.edit3));
        edits.add(et4= (EditText) findViewById(R.id.edit4));
        edits.add(etP= (EditText) findViewById(R.id.editPrecio));
        grupos.add(grupo1= (RadioGroup) findViewById(R.id.grupo1));
        grupos.add(grupo2= (RadioGroup) findViewById(R.id.grupo2));
        grupos.add(grupo3= (RadioGroup) findViewById(R.id.grupo3));
        grupos.add(grupo4= (RadioGroup) findViewById(R.id.grupo4));
        layouts.add(layout1= (LinearLayout) findViewById(R.id.layout1));
        layouts.add(layout2= (LinearLayout) findViewById(R.id.layout2));
        layouts.add(layout3= (LinearLayout) findViewById(R.id.layout3));
        layouts.add(layout4= (LinearLayout) findViewById(R.id.layout4));
        r.add(R.array.transporte);
        rPrecio.add(R.array.transportePrecio);
        r.add(R.array.hotel);
        rPrecio.add(R.array.hotelPrecio);
        r.add(R.array.comida);
        rPrecio.add(R.array.comidaPrecio);
        r.add(R.array.ocio);
        rPrecio.add(R.array.ocioPrecio);
        Resources res = getResources();
        trans= (TextView) findViewById(R.id.view1);
        hotel= (TextView) findViewById(R.id.view2);
        comida= (TextView) findViewById(R.id.view3);
        ocio= (TextView) findViewById(R.id.view4);
        trans.setText(res.getResourceEntryName(R.array.transporte));
        hotel.setText(res.getResourceEntryName(R.array.hotel));
        comida.setText(res.getResourceEntryName(R.array.comida));
        ocio.setText(res.getResourceEntryName(R.array.ocio));
        for(int i=0;i<r.size();i++) {
            rellenarRadios(res.getStringArray(r.get(i)), res.getStringArray(rPrecio.get(i)), grupos.get(i), layouts.get(i));
        }
        for(int i=0;i<4;i++){
            grupos.get(i).setOnCheckedChangeListener(this);
        }
    }


    private void rellenarRadios(String[] array, String[] precio, RadioGroup grupo, LinearLayout layout) {
        for(int i=0;i<array.length;i++){
            RadioButton rb = new RadioButton(this);
            rb.setLayoutParams(new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
            rb.setText(array[i].toString()+precio[i].toString());
            rb.setId(i+1);
            grupo.addView(rb);
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Resources res = getResources();
        edits.get(edits.size()-1).setText("");
        int total=0;
        for(int i=0;i<grupos.size();i++){
            String precioPorPersona = res.getStringArray(rPrecio.get(i))[grupos.get(0).getCheckedRadioButtonId()-1];
            String personas= edits.get(i).getText().toString();
            total= total+Integer.parseInt(precioPorPersona)*Integer.parseInt(personas);
        }
        edits.get(edits.size()-1).setText(Integer.toString(total));
        return false;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        //edits.get(4).setText(Integer.toString(grupos.get(0).getCheckedRadioButtonId()));

        //edits.get(3).setText(Integer.toString(grupos.get(i).getId()));
        Resources res = getResources();

        for (int a = 0; a < grupos.size(); a++) {
            if (radioGroup.getId() == grupos.get(a).getId()) {
                switch (i) {
                    case 1:
                        comprobar(radioGroup,grupos,edits,total,res,i,a);
                        break;
                    case 2:
                        comprobar(radioGroup,grupos,edits,total,res,i,a);
                        break;
                    case 3:
                        comprobar(radioGroup,grupos,edits,total,res,i,a);
                        break;
                    case 4:
                        comprobar(radioGroup,grupos,edits,total,res,i,a);
                        break;
                }
            }
        }
    }

    private void comprobar(RadioGroup radioGroup, ArrayList<RadioGroup> grupos, ArrayList<EditText> edits, int total, Resources res, int i,int a) {
            String precioPorPersona = res.getStringArray(rPrecio.get(a))[i-1];
            String personas= edits.get(a).getText().toString();
            int precioCalculado=Integer.parseInt(precioPorPersona)*Integer.parseInt(personas);
            switch (a){
                case 0:
                    precioTransporte=precioCalculado;
                    break;
                case 1:
                    precioComida=precioCalculado;
                    break;
                case 2:
                    precioHotel=precioCalculado;
                    break;
                case 3:
                    precioOcio=precioCalculado;
                    break;
            }
            edits.get(edits.size()-1).setText(Integer.toString(precioTransporte+precioComida+precioHotel+precioOcio));
        }
    }
