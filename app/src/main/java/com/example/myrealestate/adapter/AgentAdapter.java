package com.example.myrealestate.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrealestate.MainActivity;
import com.example.myrealestate.PropertyListActivity;
import com.example.myrealestate.R;
import com.example.myrealestate.models.Agent;
import com.example.myrealestate.preference.UserPreferences;

import java.util.List;

public class AgentAdapter extends RecyclerView.Adapter<AgentAdapter.AgentViewHolder> {

    //Adaptater pour bind vue et data (RecyclerView)
    private final List<Agent> agent;
    private Activity activity;

    public AgentAdapter(List<Agent> agent, Activity activity) {
        this.agent = agent;
        this.activity = activity;
    }

    public final class AgentViewHolder extends RecyclerView.ViewHolder {

        private final ImageView avatar;
        private final TextView name;
        private final TextView job;



        public AgentViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            name = itemView.findViewById(R.id.name);
            job = itemView.findViewById(R.id.job);
        }

        public void updateViewHolder(final Agent agent) {

            //Mise à jour de des données
            name.setText(agent.firstname +" "+agent.lastname);
            job.setText(agent.job);
//            ContextCompat.getDrawable(AgentAdapter.class,agent.getAvatar());
//            avatar.setImageResource();

            itemView.setOnClickListener(v -> {

                final Intent intent = new Intent(itemView.getContext(), PropertyListActivity.class);
               // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                UserPreferences.saveUserAgentProfile(itemView.getContext(), agent.firstname);
                itemView.getContext().startActivity(intent);
                activity.finish();


            });
        }
    }

    @NonNull
    @Override
    public AgentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.agent_recyclerview, parent,false);
        return new AgentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgentViewHolder holder, int position) {
        holder.updateViewHolder(agent.get(position));
    }

    @Override
    public int getItemCount() {
        return agent.size();
    }
}
