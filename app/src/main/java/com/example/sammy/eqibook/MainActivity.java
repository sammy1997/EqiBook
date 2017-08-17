package com.example.sammy.eqibook;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {
    private Activity context;
    FirebaseDatabase database;
    DatabaseReference fireBaseRef;
    String name,from,to,dateValue,amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=FirebaseDatabase.getInstance();
        fireBaseRef=database.getReference("EqiuBook");
        Log.d("Oncreate","Done");
        context=this;
        initUI();
    }
    private void initUI() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                 View view ;
                ListView listView;

                switch (position) {
                    case 0: view=LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_request,null,false);
                        final EditText fromTime,toTime,date;
                        final Spinner spinner;
                        final SeekBar seekBar;
                        final TextView amountSelected;
                        Button request;
                        spinner=(Spinner)view.findViewById(R.id.spinner2);
                        fromTime=(EditText)view.findViewById(R.id.editText);
                        toTime=(EditText)view.findViewById(R.id.editText3);
                        date=(EditText)view.findViewById(R.id.editText2);
                        request=(Button)view.findViewById(R.id.button2);
                        seekBar=(SeekBar)view.findViewById(R.id.seekBar);
                        amountSelected=(TextView)view.findViewById(R.id.textView6);
                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                amount = String.valueOf(seekBar.getProgress());
                                amountSelected.setText(amount);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }
                        });
                        request.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                from=fromTime.getText().toString().trim();
                                to=toTime.getText().toString().trim();
                                dateValue=date.getText().toString().trim();
                                name=spinner.getSelectedItem().toString().trim();
                                amount = String.valueOf(seekBar.getProgress());
                                Toast.makeText(getApplicationContext(),"Request made",Toast.LENGTH_LONG).show();
                                Request request = new Request(name,from,to,dateValue,amount);
                                AddToDB(request);

                            }
                        });
                        break;
                    case 1: view=LayoutInflater.from(context).inflate(R.layout.item_vp_list,null,false);
                        List<Requested> list= new ArrayList<Requested>();
                        list=populateRequestList(list);
                        ListAdapter adapterReq=new RequestAdapdter(getApplicationContext(),list,context);
                        listView=(ListView)view.findViewById(R.id.listview);
                        listView.setAdapter(adapterReq);
                        break;
                    case 2:view=LayoutInflater.from(context).inflate(R.layout.item_vp_list,null,false);
                        List<Approved> listApproved= new ArrayList<Approved>();
                        listApproved=populateApprovedList(listApproved);
                        ListAdapter adapterApprove=new ApprovedAdapter(getApplicationContext(),listApproved,context);
                        listView=(ListView)view.findViewById(R.id.listview);
                        listView.setAdapter(adapterApprove);
                        break;
                    case 3:view=LayoutInflater.from(context).inflate(R.layout.item_vp_list,null,false);
                        List<Subscribe> listSubscribe= new ArrayList<Subscribe>();
                        listSubscribe=populateSubscribeList(listSubscribe);
                        ListAdapter adapterSub=new SubscribeAdapter(getApplicationContext(),listSubscribe,context);
                        listView=(ListView)view.findViewById(R.id.listview);
                        listView.setAdapter(adapterSub);
                        break;

                    default:view = LayoutInflater.from(
                            getApplicationContext()).inflate(R.layout.item_vp, null, false);
                }
                container.addView(view);
                return view;
            }
        });

        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.home),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.home))
                        .title("Home")
                        .badgeTitle("Equi")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.applied),
                        Color.parseColor(colors[1]))
                        .title("Applied")
                        .badgeTitle("list")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.approved),
                        Color.parseColor(colors[2]))
                        .title("Approved")
                        .badgeTitle("by")
                        .build()
        );

        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.newspaper),
                        Color.parseColor(colors[4]))
                        .title("Newspaper")
                        .badgeTitle("subscribe")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.information),
                        Color.parseColor(colors[3]))
                        .title("About")
                        .badgeTitle("info")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });
        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }

    List<Requested> populateRequestList(List<Requested> list){
        Requested requested = new Requested("Cricket Bat","Pending","Amount : 3","12/7/17");
        list.add(requested);
        return list;
    }
    List<Subscribe> populateSubscribeList(List<Subscribe> list){
        Subscribe paper=new Subscribe();
        for (String paper_name:SHARED_CONSTANTS.paperNames){
            paper.setPaperName(paper_name);
            list.add(paper);
        }
        return list;
    }
    List<Approved> populateApprovedList(List<Approved> list){
        Approved approved=new Approved("Crricket Bat","Approved","12/7/17");
        list.add(approved);
        return list;
    }
    void AddToDB(Request request){
        String id = fireBaseRef.push().getKey();
        fireBaseRef.child("Requests").child(id).setValue(request);
    }

    @Override
    protected void onStart() {
        super.onStart();
        fireBaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
