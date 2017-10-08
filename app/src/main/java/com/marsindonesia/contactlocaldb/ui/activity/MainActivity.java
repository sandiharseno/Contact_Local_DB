package com.marsindonesia.contactlocaldb.ui.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.marsindonesia.contactlocaldb.R;
import com.marsindonesia.contactlocaldb.dao.ContactDetailDao;
import com.marsindonesia.contactlocaldb.model.ContactDetail;
import com.marsindonesia.contactlocaldb.ui.adapter.ContactListAdapter;
import com.marsindonesia.contactlocaldb.ui.dataobject.ContactObject;
import com.marsindonesia.contactlocaldb.ui.interfaces.DetailContactInterface;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView contactKosong;

    ArrayList<ContactObject> searchLists;

    FloatingActionButton addButton;
    String cari = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        contactKosong = (TextView) findViewById(R.id.textKosong);

        showAllData(cari);

        addButton = (FloatingActionButton) findViewById(R.id.newContact);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NewContactActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }


    private void showAllData(String searchValue) {
        ContactDetailDao contactDetailDao = new ContactDetailDao();
        try {
            List<ContactDetail> contactDetailList = null;
            if (TextUtils.isEmpty(searchValue)) {
                contactDetailList = contactDetailDao.getAllDataList(contactDetailDao.getDao(), "nama", true);
            } else {
                contactDetailList = contactDetailDao.getDataWithWhereLike(contactDetailDao.getDao(), "nama", searchValue, "nama", true);
            }

            searchLists = new ArrayList<ContactObject>();
            for (ContactDetail contactObject : contactDetailList) {
                ContactObject contactList = new ContactObject();
                contactList.setId(contactObject.getId());
                contactList.setNama(contactObject.getNama());
                contactList.setNomorHP(contactObject.getNomorHP());
                contactList.setNomorKantor(contactObject.getNomorKantor());
                contactList.setNomorRumah(contactObject.getNomorRumah());
                contactList.setEmail(contactObject.getEmail());
                contactList.setWebsite(contactObject.getWebsite());
                contactList.setIcon(contactObject.getIcon());
                searchLists.add(contactList);
            }

            final ContactListAdapter mAdapter = new ContactListAdapter(searchLists, new DetailContactInterface.OnAfterProcess() {
                @Override
                public void onCallBack(int idContact) {

                    Intent i = new Intent(getApplicationContext(), DetailContactActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.putExtra("idContact", idContact);
                    startActivity(i);
                }
            }
            );
            mRecyclerView.setAdapter(mAdapter);
            if(searchLists.size() > 0){
                contactKosong.setVisibility(View.GONE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
