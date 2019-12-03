package com.example.sampledagger.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sampledagger.R;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonHolder> {

    private final List<Person> data = new ArrayList<>();

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        return new PersonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder holder, int position) {
        Person person = data.get(position);
        holder.bind(person);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Person> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    static class PersonHolder extends RecyclerView.ViewHolder {
        public PersonHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Person person) {
            ImageView imgAvatar = itemView.findViewById(R.id.imgAvatar);
            Glide.with(itemView)
                    .load(person.getAvatarUrl())
                    .into(imgAvatar);
            TextView txtName = itemView.findViewById(R.id.txtName);
            txtName.setText(person.getName());
        }
    }
}
