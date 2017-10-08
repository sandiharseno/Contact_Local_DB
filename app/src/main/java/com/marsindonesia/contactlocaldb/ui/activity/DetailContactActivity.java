package com.marsindonesia.contactlocaldb.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.marsindonesia.contactlocaldb.R;
import com.marsindonesia.contactlocaldb.dao.ContactDetailDao;
import com.marsindonesia.contactlocaldb.model.ContactDetail;
import com.marsindonesia.contactlocaldb.util.App;
import com.marsindonesia.contactlocaldb.util.Constant;
import com.marsindonesia.contactlocaldb.util.Helpers;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.sql.SQLException;

public class DetailContactActivity extends AppCompatActivity {

    FloatingActionButton deleteButton, editButton;
    TextView namaET, nomorHPET, nomorKantorET, nomorRumahET, emailET, websiteET;
    ImageView iconIV;
    int idContact;

    Bundle extras;
    ContactDetail contactDetailList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        extras = this.getIntent().getExtras();
        idContact = extras.getInt("idContact");

        iconIV = (ImageView) findViewById(R.id.pictMain);

        namaET = (TextView) findViewById(R.id.textnama);
        nomorHPET = (TextView) findViewById(R.id.textnomorHP);
        nomorKantorET = (TextView) findViewById(R.id.textnomorKantor);
        nomorRumahET = (TextView) findViewById(R.id.textnomorRumah);
        emailET = (TextView) findViewById(R.id.textemail);
        websiteET = (TextView) findViewById(R.id.textwebsite);

        showAllData(idContact);

        deleteButton = (FloatingActionButton) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helpers.showDialogYesNo(DetailContactActivity.this, getString(R.string.konfirmasi), getString(R.string.andaInginMenghapus)+contactDetailList.getNama()+getString(R.string.dariDaftar),new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                ContactDetailDao contactDetailDao = new ContactDetailDao();
                                try {
                                int hasilDelete = contactDetailDao.deleteDataById(contactDetailDao.getDaoId(), "id", idContact);
                                if(hasilDelete == 1){
                                    Helpers.showDialogYes(DetailContactActivity.this, getString(R.string.informasi), getString(R.string.berhasilMenghapus)+contactDetailList.getNama()+getString(R.string.dariDaftar),new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(i);
                                                }
                                            }, false
                                    );
                                }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        },new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }, false
                );
            }
        });

        editButton = (FloatingActionButton) findViewById(R.id.edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditContactActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("contactId", contactDetailList.getId());
                i.putExtra("contactNama", contactDetailList.getNama());
                i.putExtra("contactnomorHP", contactDetailList.getNomorHP());
                i.putExtra("contactnomorKantor", contactDetailList.getNomorKantor());
                i.putExtra("contactnomorRumah", contactDetailList.getNomorRumah());
                i.putExtra("contactemail", contactDetailList.getEmail());
                i.putExtra("contactwebsite", contactDetailList.getWebsite());
                i.putExtra("contacticon", contactDetailList.getIcon());
                startActivity(i);
            }
        });
    }



    private void showAllData(int searchValue) {
        ContactDetailDao contactDetailDao = new ContactDetailDao();
        try {

            contactDetailList = contactDetailDao.getDataById(contactDetailDao.getDaoId(), searchValue);

                namaET.setText(contactDetailList.getNama());
                nomorHPET.setText(contactDetailList.getNomorHP());
                nomorKantorET.setText(contactDetailList.getNomorKantor());
                nomorRumahET.setText(contactDetailList.getNomorRumah());
                emailET.setText(contactDetailList.getEmail());
                websiteET.setText(contactDetailList.getWebsite());
                if(contactDetailList.getIcon() != null){
                    Picasso.with(App.getContext()).load(new File(Environment.getExternalStorageDirectory(), Constant.IMAGE_PATH+contactDetailList.getIcon()+".jpg")).fit().centerCrop().into(iconIV);
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
