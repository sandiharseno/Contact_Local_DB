package com.marsindonesia.contactlocaldb.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.marsindonesia.contactlocaldb.R;
import com.marsindonesia.contactlocaldb.dao.ContactDetailDao;
import com.marsindonesia.contactlocaldb.model.ContactDetail;
import com.marsindonesia.contactlocaldb.util.App;
import com.marsindonesia.contactlocaldb.util.Constant;
import com.marsindonesia.contactlocaldb.util.Helpers;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditContactActivity extends AppCompatActivity {

    EditText namaET, nomorHPET, nomorKantorET, nomorRumahET, emailET, websiteET;
    String nama, nomorHP, nomorKantor, nomorRumah, email, website, icon;
    FloatingActionButton saveButton;
    ImageView iconIV;

    Bundle extras;
    int idContact;

    int CAPTURE_IMAGE_CONTACT = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        iconIV = (ImageView) findViewById(R.id.pictMain);
        namaET = (EditText) findViewById(R.id.edittextnama);
        nomorHPET = (EditText) findViewById(R.id.edittextnomorHP);
        nomorKantorET = (EditText) findViewById(R.id.edittextnomorKantor);
        nomorRumahET = (EditText) findViewById(R.id.edittextnomorRumah);
        emailET = (EditText) findViewById(R.id.edittextemail);
        websiteET = (EditText) findViewById(R.id.edittextwebsite);

        iconIV = (ImageView) findViewById(R.id.pictMain);

        iconIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture(CAPTURE_IMAGE_CONTACT);
            }
        });

        extras = this.getIntent().getExtras();

        idContact = extras.getInt("contactId");
        nama = extras.getString("contactNama");
        nomorHP = extras.getString("contactnomorHP");
        nomorKantor = extras.getString("contactnomorKantor");
        nomorRumah = extras.getString("contactnomorRumah");
        email = extras.getString("contactemail");
        website = extras.getString("contactwebsite");
        icon = extras.getString("contacticon");

        namaET.setText(nama);
        nomorHPET.setText(nomorHP);
        nomorKantorET.setText(nomorKantor);
        nomorRumahET.setText(nomorRumah);
        emailET.setText(email);
        websiteET.setText(website);

        if(icon != null) {
            Picasso.with(App.getContext()).load(new File(Environment.getExternalStorageDirectory(), Constant.IMAGE_PATH + icon + ".jpg")).fit().centerCrop().into(iconIV);
        }
        saveButton = (FloatingActionButton) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helpers.showDialogYesNo(EditContactActivity.this, getString(R.string.konfirmasi), getString(R.string.andaYakinMengubah)+" "+nama,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                nama = namaET.getText().toString().trim();
                                nomorHP = nomorHPET.getText().toString().trim();
                                nomorKantor = nomorKantorET.getText().toString().trim();
                                nomorRumah = nomorRumahET.getText().toString().trim();
                                email = emailET.getText().toString().trim();
                                website = websiteET.getText().toString().trim();

                                if(nama == null || nama.isEmpty()){
                                    Toast.makeText(getApplicationContext(), getString(R.string.namaKosong), Toast.LENGTH_SHORT).show();
                                }

                                System.out.println(nama != null);
                                System.out.println(!nama.isEmpty());
                                System.out.println(nomorHP != null);
                                System.out.println(!nomorHP.isEmpty());
                                System.out.println(nomorKantor != null);
                                System.out.println(!nomorKantor.isEmpty());
                                System.out.println(nomorRumah != null);
                                System.out.println(!nomorRumah.isEmpty());
                                System.out.println(email != null);
                                System.out.println(!email.isEmpty());
                                System.out.println(website != null);
                                System.out.println(!website.isEmpty());

                                if((nama != null && !nama.isEmpty()) && ((nomorHP != null && !nomorHP.isEmpty()) || (nomorKantor != null && !nomorKantor.isEmpty()) || (nomorRumah != null && !nomorRumah.isEmpty()) || (email != null && !email.isEmpty()) || (website != null  && !website.isEmpty()))){
                                    editContact(nama, nomorHP, nomorKantor, nomorRumah, email, website, icon);
                                }
                                else{
                                    if(nama == null || nama.isEmpty()){
                                        System.out.println("masuk nama null dan isiempty");
                                    }else {
                                        Toast.makeText(getApplicationContext(), getString(R.string.isiKontak) + " " + nama, Toast.LENGTH_SHORT).show();
                                    }
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
    }

    private void editContact(String nama, String nomorHP, String nomorKantor, String nomorRumah, String email, String website, String icon){
        try {
            ContactDetailDao contactDetailDao = new ContactDetailDao();

            contactDetailDao.update(contactDetailDao.getDao(), "nama", nama, "id", idContact);
            contactDetailDao.update(contactDetailDao.getDao(), "nomorHP", nomorHP, "id", idContact);
            contactDetailDao.update(contactDetailDao.getDao(), "nomorKantor", nomorKantor, "id", idContact);
            contactDetailDao.update(contactDetailDao.getDao(), "nomorRumah", nomorRumah, "id", idContact);
            contactDetailDao.update(contactDetailDao.getDao(), "email", email, "id", idContact);
            contactDetailDao.update(contactDetailDao.getDao(), "website", website, "id", idContact);
            contactDetailDao.update(contactDetailDao.getDao(), "icon", icon, "id", idContact);

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    File fileTemp;

    private void takePicture(int REQUEST) {
        File imageStorageFoldera = new File(Environment.getExternalStorageDirectory(), Constant.IMAGE_PATH);
        if (!imageStorageFoldera.exists()) {
            imageStorageFoldera.mkdirs();
        }

        fileTemp = new File(Environment.getExternalStorageDirectory(), Constant.IMAGE_PATH + "Image_temp.jpg");
        try {
            fileTemp.createNewFile();
        } catch (IOException e) {
            Toast.makeText(EditContactActivity.this, getString(R.string.gagalMengambilGambar), Toast.LENGTH_SHORT).show();
        }

        Uri outputFileUri = Uri.fromFile(fileTemp);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        startActivityForResult(cameraIntent, REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        if (requestCode == CAPTURE_IMAGE_CONTACT && resultCode == RESULT_OK) {
            saveResizeImageKecil("contact_"+timeStamp);
        }
    }

    private void saveResizeImageKecil(String previx) {
        File imageStorageFoldera = new File(Environment.getExternalStorageDirectory() + Constant.IMAGE_PATH);
        if (!imageStorageFoldera.exists()) {
            imageStorageFoldera.mkdirs();
        }

        if (fileTemp.exists()) {
            try {

                Bitmap bitmapAsli = BitmapFactory.decodeFile(fileTemp.getAbsolutePath());
                int lebarAsli = bitmapAsli.getWidth();
                int tinggiAsli = bitmapAsli.getHeight();
                System.out.println("ini ukuran asli: "+ lebarAsli +" , "+ tinggiAsli);
                int ukuranInSample = 1;
                if (lebarAsli > tinggiAsli){
                    ukuranInSample = lebarAsli / 1024;
                }else{
                    ukuranInSample = tinggiAsli / 1024;
                }

                BitmapFactory.Options bitmapOption = new BitmapFactory.Options();
                bitmapOption.inSampleSize = ukuranInSample;
                Bitmap bitmap = BitmapFactory.decodeFile(fileTemp.getAbsolutePath(), bitmapOption);
                File newfile2 = new File(Environment.getExternalStorageDirectory(), Constant.IMAGE_PATH + previx + ".jpg");

                FileOutputStream out = new FileOutputStream(newfile2);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
                out.flush();
                out.close();

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(newfile2);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

                icon = previx;
                Picasso.with(App.getContext()).load(new File(Environment.getExternalStorageDirectory(), Constant.IMAGE_PATH+icon+".jpg")).fit().centerCrop().into(iconIV);
            } catch (Exception ex) {

            }
        }

    }

    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra("idContact", idContact);
        startActivity(i);
    }
}
