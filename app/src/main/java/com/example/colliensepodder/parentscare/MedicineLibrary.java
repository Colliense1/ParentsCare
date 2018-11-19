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
            "Ace Plus®", "Ace®", "Acetram®", "Adryl®", "Afun®", "Afun® VT",
            "Alacot® DS Eye Drops", "Alacot® Eye Drops", "Alarid® Eye Drops",
            "Alatrol®", "Alice", "Almex®", "Ambrisan™ 5", "Ambrox®",
            "Amistar™ IM/IV Injection", "Amodis®", "Amodis® 500 IV", "Anadol®",
            "Anclog Plus®", "Anclog®", "Anema™", "Anema™", "Angilock®",
            "Angilock® plus", "Angivent MR®", "Anleptic®", "Anoxa 10®",
            "Anril 0.5®", "Anril® Injection", "Anril® spray",
            "Anril® spray", "Ansulin® Pen Cartridge", "Ansulin® Vial",
            "AnsuPen®", "Antazol®", "Antazol® Plus", "Antista®", "Antiva®",
            "Anzitor®", "Apsol®", "Ariprex™", "Asynta™", "AvasprayTM",
            "B-50 Forte®", "Bactrocin®", "Barif®", "Beclomin®", "Becospray®",
            "Benzapen®", "Beovit®", "Betameson-N®", "Bicozin-I®", "Bicozin®",
            "BimatorTM Eye Drops", "Bisocor®", "Bisocor™ Plus", "Bonizol™",
            "Brofex®", "Bromolac®", "Bufocort™ Cozycap", "Burna®",
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
            "Duolax™","Durol®","Dyvon Plus®","Dyvon®","EfaximTM","Efigrel®","Elzer®","Emcil®","Emolent™ Cream",
            "Entacyd Plus®","Entacyd®","Epitra®","Epitra®","Eporen™","Equra®","Erian®","Eromycin Lotion®",
            "Eromycin®","Eslicar™",
            "Esloric®","Evit Licap®","Eyevi®","Evit®","Ezex®",
            "Facticin®","Famotack®","FemastinTM","Fentizol™ VT 600","Fexo®",
            "Filfresh®","Filwel Gold®","Filwel Kids®","Filwel Silver®","Filwel™ Teen hm",
            "Filwel™ Teen hr","Flacol®","Flexi®", "Flexilax®", "Flindof™",
            "Flonasin™", "Flonaspray®", "Flugal®", "Flurizin®",
            "Fodexil™", "Fona plus®", "Fona®", "Frabex®", "Fungidal BT®",
            "Fungidal HC®", "Fungidal®", "Fusid®", "Fusid® Plus",
            "Fusitop-HC™","Gabastar®",
            "Gelora®","Genacyn Ointment®","Genisia™","Genacyn® Eye/Ear Drops",
            "Germisol® Hand Rub","Geston™","Glysup™","GOL™","Grastim™",
            "Gynepro®", "Halobet®",
            "Hemorif®","Hepavir®","Imotil®","Inflagic®","Infudex™ IV",
            "Intimate®","Iprex Inhaler®","Iprex Respirator Solution®",
            "Iracet™","IsodexTM IV","Isovent®","Itra®","Ivanor™",
            "Iventi Tablet","Iventi-DTM Eye Drops","Iventi® Eye Drops","Iventi™ 400 IV",
            "K-One®","Ketoral®","Kop Gel®",
            "Kop®","Lactoring™ IV","Lamicet™","Lanso®","Larsulin™","Laxyl®",
            "Lebac®","Lerozol®","Levocar®","Levostar®","Lido™","Linita™",
            "Lipired 160®","Lipired®","Livwel Syrup®","Locular® Eye Drop",
            "Locular® Plus Eye Drops",
            "Loracef®","Loratin®","Lubgel™ 1% Eye Drops",
            "Lubtear® Eye Drops","Lumertam®","Luraprex™",
            "Maganta Plus®","Maxbon™","MaxbonTM Kit","MaxcefTM","MaxpimeTM",
            "Maxrin®","Maxrin® D","Melcam®","Melixol®",
            "Menoral®","Merison®","Metaspray Nasal Spray®","Methicol®",
            "Mevin™","Mexlo®","Mexlo® Eye Drops","Miclofenac®",
            "Migranil®","Mirakof®","Montene®","Motifast®","Motigut®","Moxacil®",
            "Moxaclav®","Mucospel®",
            "Multivit Plus®","Myonil®","Nacromin®","Naurif®","Nebanol Plus®",
            "Nebanol®","Nebita™","Nectar®",
            "Neotack®","Neuro-B®","Neurolep™","Neurolin®",
            "Nexum®","Nexum® MUPS","Nidipine®","Nimocal®","Nixalo®","Nomi®","Norpill™1",
            "Norvis®","Ocof™","Ocubrom™ Eye Drops","Oculant™ Eye Drops","Ofran®",
            "Olicod®","Olistat™","Olmecar Plus®",
            "Olmecar®","Oni®","Orogel Dental Gel®",
            "Orostar™ Cool Mint and Original Antiseptic Mouthwash", "Orostar™ Plus",
            "Osmolax®","Ostel™-D","Otelast™","Oxapro®","Oxat®",
            "OxifunTM","Oxifyl CR®","Paloset™","Panodin® SR","Penrif®",
            "Pentadol®","Penvik®","Peridol®","Perkidopa™","Perkinil®","Perkirol®",
            "Pevitin®","Phylopen®","Piramed®","Pivalo™",
            "Prazolok™","Prolert®","Promtil®","Pronor®","Prosalic™ Lotion",
            "Proxivir®","Purotrol®","Pylotrip®","Pylotrip® R","Rabeca®",
            "Racedot™","Radirif®","Ranolin™","Rectocare®","Remac®","Remus®",
            "Renorma®","Repres plus®","Repres® SR","ResQ™",
            "Retabac™","Revira™","Revolizer","Rex®","Rice ORS®","Ripril®",
            "Risedon®","Risedon™ Plus","RivaXa™","Robic®","Rosuva®","Rupatrol™",
            "Rutix®","Saga®","Salicid™ Cream","Scabex®","Seclo® MUPS","Seclo®","Secnid®",
            "Secrin®",
            "Sedil®","Sedno®","Siglimet™","Siglita™","Simacor®","SoliderTM",
            "Solo®","Solo™ 0.9% IV","Solodex™ IV","SolodexTM Baby IV",
            "SolodexTM JR IV","Sonap®","Specbac®","Splendora™","SQ-Mycetin® Eye/Ear Drops",
            "Suev 10®","Sulprex MDI®","Sulprex Nebuliser Solution®","Sultolin HFA®","Sultolin Respirator Solution®",
            "Sultolin®",
            "Sultolin® Cozycap","Susten™","Suvirux™","SuvotolTM","Suxalax™ IM/IV Injection",
            "Suzyme®","Tazid®","Tazocilin™","Tebast™","Terminex™","Tetrax®",
            "Thyrin™","Ticalog™","Ticamet™ HFA","Ticamet™ Cozycap","Ticas®",
            "Tilex® Max","Timotor®","Togent®","Topicort®",
            "Torax®","Tory®","Trevox®","Trevox® 500 IV","Trispray®",
            "Trupan®","Truxil™","Tryptin®","Tusca PlusTM","Tylace™",
            "Ucol™","Uriset™","Urocure™","Urso™","Utal™","Valoate®","Vanprox®",
            "Versia®","Viglimet™","Viglita™",
            "Vigorex®","Virux®","Viodin®","Virux Tablet®","Virux® HC",
            "Virux® IV Injection","Xcid®","Xenole™","Xflam®","Xten®",
            "Xylocon™","Zanthin™","Zesup®","Zif Forte®","Zif-CI®","Zif®",
            "Zifolet®","Ziliron-B®","Zimax®","Zox®",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
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
