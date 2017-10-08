package com.marsindonesia.contactlocaldb.ui.adapter;

import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marsindonesia.contactlocaldb.R;
import com.marsindonesia.contactlocaldb.ui.dataobject.ContactObject;
import com.marsindonesia.contactlocaldb.ui.interfaces.DetailContactInterface;
import com.marsindonesia.contactlocaldb.util.App;
import com.marsindonesia.contactlocaldb.util.Constant;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView
        .Adapter<ContactListAdapter
        .ContactObjectHolder> {
    private static String LOG_TAG = "ContactListAdapter";
    private ArrayList<ContactObject> mDataset;

    private DetailContactInterface.OnAfterProcess onAfterProcessInterface;

    public static class ContactObjectHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        ImageView logo;

        protected View mView;

        public ContactObjectHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textNama);
            logo = (ImageView) itemView.findViewById(R.id.pictMain);

            mView = itemView;
        }

    }

    public ContactListAdapter(ArrayList<ContactObject> myDataset, final DetailContactInterface.OnAfterProcess onAfterProcess) {
        mDataset = myDataset;
        onAfterProcessInterface = onAfterProcess;
    }

    @Override
    public ContactObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contains_main, parent, false);

        ContactObjectHolder dataObjectHolder = new ContactObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(ContactObjectHolder holder, int position) {

        final ContactObject obj = mDataset.get(position);
        if(obj.getNama() != null){
            holder.name.setText(obj.getNama());
        }

        if(obj.getIcon() != null){
            Picasso.with(App.getContext()).load(new File(Environment.getExternalStorageDirectory(), Constant.IMAGE_PATH+obj.getIcon()+".jpg")).fit().centerInside().into(holder.logo);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAfterProcessInterface.onCallBack(obj.getId());
            }
        });

    }

    public void addItem(ContactObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}