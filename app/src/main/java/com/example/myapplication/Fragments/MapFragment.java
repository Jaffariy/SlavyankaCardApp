package com.example.myapplication.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

public class MapFragment extends Fragment{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MapView mapView;
    private TextView titleTextView;
    private TextView snippetTextView;


    private String mParam1;
    private String mParam2;

    public MapFragment() {

    }

    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
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
        Configuration.getInstance().setUserAgentValue(getContext().getPackageName());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.map_fragment, container, false);

        // Find the views from the inflated layout
        mapView = rootView.findViewById(R.id.mapView);
        titleTextView = rootView.findViewById(R.id.titleTextView);
        snippetTextView = rootView.findViewById(R.id.snippetTextView);

        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mapView.setMultiTouchControls(true);
        GeoPoint startPoint = new GeoPoint(51.2967, 37.8416);
        mapView.getController().setZoom(15);
        mapView.getController().setCenter(startPoint);

        MapIcons();

        return rootView;
    }
    /*
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

     */
    private void MapIcons()
    {
        GeoPoint SlavyankaShop1 = new GeoPoint(51.323821, 37.881030); //пр-т Алексея Угарова д.6
        GeoPoint SlavyankaShop2 = new GeoPoint(51.341019, 37.846472); //ул. 1 Конной Армии д.65
        GeoPoint SlavyankaShop3 = new GeoPoint(51.307344, 37.866621); //ул. Прядченко д.116
        GeoPoint SlavyankaShop4 = new GeoPoint(51.295636, 37.835019); //ул. Ленина д.22
        GeoPoint SlavyankaShop5 = new GeoPoint(51.309224, 37.873053); //мкр. Ольминского д.17
        GeoPoint SlavyankaShop6 = new GeoPoint(51.307073, 37.891262); //мкр. Олимпийский д.63
        GeoPoint SlavyankaShop7 = new GeoPoint(51.322037, 37.883087); //пр-т Алексея Угарова д.4/1
        GeoPoint SlavyankaShop8 = new GeoPoint(51.286776, 37.809866); //микр. Интернациональный д.12, рынок Болгарский
        GeoPoint SlavyankaShop9 = new GeoPoint(51.309545, 37.916092); //мкр. Дубрава 3, д.32 Б
        GeoPoint SlavyankaShop10 = new GeoPoint(51.312718, 37.899485); //мкр. Солнечный, рынок Солнечный

        Marker Slavyanka1 = new Marker(mapView);
        Marker Slavyanka2 = new Marker(mapView);
        Marker Slavyanka3 = new Marker(mapView);
        Marker Slavyanka4 = new Marker(mapView);
        Marker Slavyanka5 = new Marker(mapView);
        Marker Slavyanka6 = new Marker(mapView);
        Marker Slavyanka7 = new Marker(mapView);
        Marker Slavyanka8 = new Marker(mapView);
        Marker Slavyanka9 = new Marker(mapView);
        Marker Slavyanka10 = new Marker(mapView);

        Slavyanka1.setPosition(SlavyankaShop1);
        Slavyanka2.setPosition(SlavyankaShop2);
        Slavyanka3.setPosition(SlavyankaShop3);
        Slavyanka4.setPosition(SlavyankaShop4);
        Slavyanka5.setPosition(SlavyankaShop5);
        Slavyanka6.setPosition(SlavyankaShop6);
        Slavyanka7.setPosition(SlavyankaShop7);
        Slavyanka8.setPosition(SlavyankaShop8);
        Slavyanka9.setPosition(SlavyankaShop9);
        Slavyanka10.setPosition(SlavyankaShop10);

        Slavyanka1.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        Slavyanka2.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        Slavyanka3.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        Slavyanka4.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        Slavyanka5.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        Slavyanka6.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        Slavyanka7.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        Slavyanka8.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        Slavyanka9.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        Slavyanka10.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        mapView.getOverlays().add(Slavyanka1);
        mapView.getOverlays().add(Slavyanka2);
        mapView.getOverlays().add(Slavyanka3);
        mapView.getOverlays().add(Slavyanka4);
        mapView.getOverlays().add(Slavyanka5);
        mapView.getOverlays().add(Slavyanka6);
        mapView.getOverlays().add(Slavyanka7);
        mapView.getOverlays().add(Slavyanka8);
        mapView.getOverlays().add(Slavyanka9);
        mapView.getOverlays().add(Slavyanka10);

        Slavyanka1.setTitle("Фирменный магазин Славянка");
        Slavyanka1.setSnippet("пр-т Алексея Угарова д.6");

        Slavyanka2.setTitle("Фирменный магазин Славянка");
        Slavyanka2.setSnippet("ул. 1 Конной Армии д.65");

        Slavyanka3.setTitle("Фирменный магазин Славянка");
        Slavyanka3.setSnippet("ул. Прядченко д.116");

        Slavyanka4.setTitle("Фирменный магазин Славянка");
        Slavyanka4.setSnippet("ул. Ленина д.22");

        Slavyanka5.setTitle("Фирменный магазин Славянка");
        Slavyanka5.setSnippet("мкр. Ольминского д.17");

        Slavyanka6.setTitle("Фирменный магазин Славянка");
        Slavyanka6.setSnippet("мкр. Олимпийский д.63");

        Slavyanka7.setTitle("Фирменный магазин Славянка");
        Slavyanka7.setSnippet("пр-т Алексея Угарова д.4/1");

        Slavyanka8.setTitle("Фирменный магазин Славянка");
        Slavyanka8.setSnippet("микр. Интернациональный д.12, рынок Болгарский");

        Slavyanka9.setTitle("Фирменный магазин Славянка");
        Slavyanka9.setSnippet("мкр. Дубрава 3, д.32 Б");

        Slavyanka10.setTitle("Фирменный магазин Славянка");
        Slavyanka10.setSnippet("мкр. Солнечный, рынок Солнечный");
    }
}