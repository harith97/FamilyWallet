package ccpe001.familywallet;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import ccpe001.familywallet.admin.Notification;

import java.util.Arrays;

/**
 * Created by harithaperera on 7/10/17.
 */
public class NotificationCards extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notificationtab, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }



    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        NotificationDetails notificationDetais = new NotificationDetails();

        private String[] titles = {notificationDetais.getNotiTitle(),
                "Chapter Two",
                "Chapter Three",
                "Chapter Four",
                "Chapter Five",
                "Chapter Six",
                "Chapter Seven",
                "Chapter Eight"};

        private String[] details = {notificationDetais.getNotiDesc(),
                "Item two details", "Item three details",
                "Item four details", "Item file details",
                "Item six details", "Item seven details",
                "Item eight details"};



        class ViewHolder extends RecyclerView.ViewHolder{

            public int currentItem;
            public ImageButton noti_delBtn;
            public TextView noti_date,noti_desc,noti_title;

            public ViewHolder(View itemView) {
                super(itemView);
                noti_delBtn = (ImageButton)itemView.findViewById(R.id.noti_delBtn);
                noti_date = (TextView)itemView.findViewById(R.id.noti_date);
                noti_desc = (TextView)itemView.findViewById(R.id.noti_desc);
                noti_title = (TextView)itemView.findViewById(R.id.noti_title);

                noti_delBtn.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        int position = getAdapterPosition();

                        Snackbar.make(v, "Click detected on item " + position,
                                Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }
                });
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.notificationcard, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.noti_title.setText(titles[i]);
            viewHolder.noti_desc.setText(details[i]);

        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }
}
