package br.com.dlweb.listaadaptadores;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.TimeViewHolder>{
    private int checkedPosition = 0;
    private String[] times;

    TimeAdapter(String[] times){
        this.times = times;
    }

    class TimeViewHolder extends RecyclerView.ViewHolder {
        private TextView nomeView;
        private ImageView imageView;

        TimeViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeView = itemView.findViewById(R.id.tvListNome);
            imageView = itemView.findViewById(R.id.imageView);
        }

        void bind(final String time) {
            Log.w("getBindingAdapterPosition()", getAbsoluteAdapterPosition()+"");
            if (checkedPosition == -1) {
                imageView.setVisibility(View.GONE);
            } else {
                if (checkedPosition == getAbsoluteAdapterPosition()) {
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                }
            }
            nomeView.setText(time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageView.setVisibility(View.VISIBLE);
                    if (checkedPosition != getAbsoluteAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAbsoluteAdapterPosition();
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new TimeViewHolder(v);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(TimeViewHolder viewHolder, int i) {
        viewHolder.bind(times[i]);
    }

    @Override
    public int getItemCount() {
        return times.length;
    }

    public String getSelected() {
        if (checkedPosition != -1) {
            return times[checkedPosition];
        }
        return null;
    }
}
