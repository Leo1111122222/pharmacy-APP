package com.example.pgarmacy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pgarmacy.Medicine.Medicine;
import com.example.pgarmacy.R;

import java.util.List;


class SearchViewAdapter extends RecyclerView.ViewHolder {
    public TextView name, price, company, roof, cabin;

    public SearchViewAdapter(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        price = itemView.findViewById(R.id.price);
        company = itemView.findViewById(R.id.company);
        roof = itemView.findViewById(R.id.roof_num);
        cabin = itemView.findViewById(R.id.cabin_num);
    }
}


/**
 * @noinspection ALL
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchViewAdapter> {
    private Context context;
    private List<Medicine> medicines;

    public SearchAdapter(Context context, List<Medicine> medicines) {
        this.context = context;
        this.medicines = medicines;
    }

    @NonNull
    @Override
    public SearchViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.activity_items, parent, false);
        return new SearchViewAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewAdapter holder, int position) {
        holder.name.setText(medicines.get(position).getName());
        holder.company.setText(medicines.get(position).getCompany());
        holder.price.setText(medicines.get(position).getPrice());
        holder.roof.setText(medicines.get(position).getRoof());
        holder.cabin.setText(medicines.get(position).getCabin());

    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }
}
