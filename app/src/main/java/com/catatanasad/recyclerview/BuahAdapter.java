package com.catatanasad.recyclerview;

import android.content.Context;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;

public class BuahAdapter extends RecyclerView.Adapter<BuahAdapter.ViewHolder> {

    Context context;

    public BuahAdapter(Context context) {
        this.context = context;
    }

    String [] nama_buah = {"Alpukat", "Apel", "Ceri", "Durian", "Jambu Air", "Manggis", "Strawberry"};
    int [] gambar_buah = {R.drawable.alpukat,
            R.drawable.apel,
            R.drawable.ceri,
            R.drawable.durian,
            R.drawable.jambuair,
            R.drawable.manggis,
            R.drawable.strawberry};

    @NonNull
    @Override
    public BuahAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO konek layout di recyclerView
        View v = LayoutInflater.from(context).inflate(R.layout.list_buah, parent,false);
        return new BuahAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BuahAdapter.ViewHolder holder, final int position) {

        holder.textBuah.setText(nama_buah [position]);
        holder.imageBuah.setImageResource(gambar_buah [position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO memanggil toast
                Toast.makeText(context, nama_buah[position], Toast.LENGTH_SHORT).show();
                playSong (position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nama_buah.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageBuah;
        TextView textBuah;

        public ViewHolder(View itemView) {
            super(itemView);

            imageBuah = itemView.findViewById(R.id.image_buah);
            textBuah = itemView.findViewById(R.id.text_buah);
        }
    }

    //TODO method untuk memberikan suara ketika diklik
    private void playSong(int position) {
        // TODO deklarasi file suara
        int [] song_buah = {R.raw.alpukat,
                R.raw.apel,
                R.raw.ceri,
                R.raw.durian,
                R.raw.jambu_air,
                R.raw.manggis,
                R.raw.strawberry};

        // TODO parsing suara ke URI
        Uri uri = Uri.parse("android.resource://"+getClass().getPackage().getName()+"/"+song_buah[position]);

        //TODO memanggil Media Player
        MediaPlayer player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        //TODO konek URI ke Media Player
        try {
            player.setDataSource(context,uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
    }
}
