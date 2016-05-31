package com.ciacavus.jsonparsing;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ciaran on 25/05/2016.
 */
public class ActorAdapter extends ArrayAdapter<Actors> implements ListAdapter  {

    ArrayList<Actors> listItem;
    LayoutInflater inflater;
    int Resource;
    ViewHolder holder;

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public class ViewHolder {

        public ImageView imageView;
        private TextView tvName;
        private TextView tvDesc;
        private TextView tvDob;
        private TextView tvCountry;
        private TextView tvHeight;
        private TextView tvSpouse;
        private TextView tvChildren;

    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap>
    {
        ImageView dImage;
        String urlDisplay;

        public DownloadImage(ImageView imageView){
            this.dImage = dImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urlDisplay = urls[0];
            Bitmap Icon = null;

            try{
                InputStream in = new java.net.URL(urlDisplay).openStream();
                Icon = BitmapFactory.decodeStream(in);

            }catch (Exception e)
            {
                Log.e("Message: ", e.getMessage());
            }

            return Icon;
        }

        @Override
        protected void onPostExecute(Bitmap result)
        {
            dImage.setImageBitmap(result);
        }
    }

    public ActorAdapter(Context context, int resource, ArrayList<Actors> obj) {
        super(context, resource, obj);

        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        listItem = obj;

    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Actors getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;

        if(v==null)
        {
            holder = new ViewHolder();

            v = inflater.inflate(Resource,null);
            holder.imageView = (ImageView)v.findViewById(R.id.tvImage);
            holder.tvName = (TextView) v.findViewById(R.id.tvName);
            holder.tvDesc = (TextView) v.findViewById(R.id.tvDescription);
            holder.tvDob = (TextView) v.findViewById(R.id.tvDateOfBirth);
            holder.tvCountry = (TextView) v.findViewById(R.id.tvCountry);
            holder.tvHeight = (TextView) v.findViewById(R.id.tvHeight);
            holder.tvSpouse = (TextView) v.findViewById(R.id.tvSpouse);
            holder.tvChildren = (TextView) v.findViewById(R.id.tvChildren);

            v.setTag(holder);
        }else
        {
            holder = (ViewHolder)v.getTag();

        }

        holder.imageView.setImageResource(R.mipmap.ic_launcher);

        new DownloadImage(holder.imageView).execute(listItem.get(position).getImage());

        holder.tvName.setText(listItem.get(position).getName());
        holder.tvDesc.setText(listItem.get(position).getDesc());
        holder.tvDob.setText(listItem.get(position).getDob());
        holder.tvCountry.setText(listItem.get(position).getCountry());
        holder.tvHeight.setText(listItem.get(position).getHeight());
        holder.tvChildren.setText(listItem.get(position).getChildren());
        holder.tvSpouse.setText(listItem.get(position).getSpouse());

        return v;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}


