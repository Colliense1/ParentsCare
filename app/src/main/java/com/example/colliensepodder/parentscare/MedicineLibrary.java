package com.example.colliensepodder.parentscare;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class MedicineLibrary extends AppCompatActivity {


    Toolbar mtoolbar;
    MaterialSearchView searchView;
    ListView listView;

    String[] listSource = {
            "Ace Plus®",
            "Ace®",
            "Acetram®",
            "Adryl®",
            "Afun®",
            "Afun® VT",
            "Alacot® DS Eye Drops",
            "Alacot® Eye Drops",
            "Alarid® Eye Drops",
            "Alatrol®",
            "Alice",
            "Almex®",
            "Ambrisan™ 5",
            "Ambrox®",
            "Amistar™ IM/IV Injection",
            "Amodis®",
            "Amodis® 500 IV",
            "Anadol®",
            "Anclog Plus®",
            "Anclog®",
            "Anema™",
            "Anema™",
            "Angilock®",
            "Angilock® plus",
            "Angivent MR®",
            "Anleptic®",
            "Anoxa 10®",
            "Anril 0.5®",
            "Anril® Injection",
            "Anril® spray",
            "Anril® spray",
            "Ansulin® Pen Cartridge",
            "Ansulin® Vial",
            "AnsuPen®",
            "Antazol®",
            "Antazol® Plus",
            "Antista®",
            "Antiva®",
            "Anzitor®",
            "Apsol®",
            "Ariprex™",
            "Asynta™",
            "AvasprayTM",
            "B-50 Forte®",
            "Bactrocin®",
            "Barif®",
            "Beclomin®",
            "Becospray®",
            "Benzapen®",
            "Beovit®",
            "Betameson-N®",
            "Bicozin-I®",
            "Bicozin®",
            "BimatorTM Eye Drops",
            "Bisocor®",
            "Bisocor™ Plus",
            "Bonizol™",
            "Brofex®",
            "Bromolac®",
            "Bufocort™ Cozycap",
            "Burna®",
            "Calbo 500®","Calbo Forte®","Calbo Jr.®","Calbo-C®","Calbo-D Vita","Calbo-D®","Calboplex®","Calboral-D™","Calborate™","Calcitrol®","Camlodin®","Camlodin® plus","Camlopril®","Camlosart™","Camoval®","Canaglif™","Candex®","Carbizol®","Carva®","Cavir®",
            "Ceevit Forte®","Ceevit®","Ceevit™ DS","Cef-3®","CefopenTM","Cefotil®","Cefotil™ Plus","Ceftiben™","Ceftron®","Cerevas™",
            "Cholenak™ IV","Cilosta®","Cinaron®","Cinaron® Plus","Ciprocin®",
            "Ciprocin® Eye/Ear Drops","Ciprocin® 200 IV","Climycin®","Clinface®","Clobam®",
            "Clofenac 100 Suppository®","Clofenac DT®","Clofenac Gel®","Clofenac® Plus Injection","Clofenac®",
            "Clopirox™ Cream","Clotinex™","Colicon®","Colimax™","Comet®",
            "Comprid™","Contifil®","Contilex®","Cotrim®",
            "De-rash®","Defiron™","Deflacort™","Delipid®","Depram®","Deprex®",
            "Dermasol-N®","Dermasol™-S","Dermasol®","Dexonex-C™ Eye/Ear Drops",
            "Dexonex®","Dexonex® Eye/Ear Drops","Dibenol®","Diliner® DR","Diltizem® SR 90",
            "Diprobet®","Dormitol®",
            "Duolax™","Durol®","Dyvon Plus®","Dyvon®","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","", "",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_library);

        mtoolbar =(Toolbar)findViewById(R.id.toolbar1);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Medicine Library");
        mtoolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));


        listView = (ListView)findViewById(R.id.list_view);
        ArrayAdapter adapter= new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,listSource);

        listView.setAdapter(adapter);

        searchView = (MaterialSearchView)findViewById(R.id.search_view);

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

                listView = (ListView)findViewById(R.id.list_view);
                ArrayAdapter adapter= new ArrayAdapter<String>(
                        MedicineLibrary.this,android.R.layout.simple_list_item_1,listSource);
                listView.setAdapter(adapter);

            }
        });



       searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()){
                    List<String> lstFound = new ArrayList<String>();
                    for (String item:listSource){
                        if (item.contains(newText))
                            lstFound.add(item);
                    }

                    ArrayAdapter adapter= new ArrayAdapter<String>(
                            MedicineLibrary.this,android.R.layout.simple_list_item_1,lstFound);
                    listView.setAdapter(adapter);
                }else {

                    //if search text is empty
                    //return default

                    ArrayAdapter adapter= new ArrayAdapter<String>(
                            MedicineLibrary.this,android.R.layout.simple_list_item_1,listSource);
                    listView.setAdapter(adapter);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem item= menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }
}
