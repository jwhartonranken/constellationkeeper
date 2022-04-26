package tech.jameswharton.sqlitetest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList constellation_id;
    private ArrayList constellation_name;
    private ArrayList constellation_brightest;
    private ArrayList constellation_magnitude;
    private ArrayList constellation_domain;

    public MyAdapter(Context context, Activity activity, ArrayList constellation_id, ArrayList constellation_name, ArrayList constellation_brightest, ArrayList constellation_magnitude, ArrayList constellation_domain) {
        this.context = context;
        this.activity = activity;
        this.constellation_id = constellation_id;
        this.constellation_name = constellation_name;
        this.constellation_brightest = constellation_brightest;
        this.constellation_magnitude = constellation_magnitude;
        this.constellation_domain = constellation_domain;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.constellationId_rv.setText(String.valueOf(constellation_id.get(position)));
        holder.constellationName_rv.setText(String.valueOf(constellation_name.get(position)));
        holder.constellationBrightest_rv.setText(String.valueOf(constellation_brightest.get(position)));
        holder.constellationMagnitude_rv.setText(String.valueOf(constellation_magnitude.get(position)));
        holder.constellationDomain_rv.setText(String.valueOf(constellation_domain.get(position)));
        // RecyclerView OnClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(constellation_id.get(holder.getAdapterPosition())));
                intent.putExtra("name", String.valueOf(constellation_name.get(holder.getAdapterPosition())));
                intent.putExtra("domain", String.valueOf(constellation_domain.get(holder.getAdapterPosition())));
                intent.putExtra("brightest", String.valueOf(constellation_brightest.get(holder.getAdapterPosition())));
                intent.putExtra("magnitude", String.valueOf(constellation_magnitude.get(holder.getAdapterPosition())));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return constellation_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView constellationId_rv;
        TextView constellationName_rv;
        TextView constellationBrightest_rv;
        TextView constellationMagnitude_rv;
        TextView constellationDomain_rv;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            constellationId_rv = itemView.findViewById(R.id.constellation_id_rv);
            constellationName_rv = itemView.findViewById(R.id.constellation_name_rv);
            constellationBrightest_rv = itemView.findViewById(R.id.constellation_brightest_rv);
            constellationMagnitude_rv = itemView.findViewById(R.id.constellation_magnitude_rv);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            constellationDomain_rv = itemView.findViewById(R.id.constellation_domain_rv);
        }
    }
}

