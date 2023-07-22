package com.example.myapplication.NotificationSetup;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.NotificationSetup.PurchaseNotification;
import com.example.myapplication.R;

import java.util.List;

public class PurchaseNotificationAdapter extends RecyclerView.Adapter<PurchaseNotificationAdapter.PurchaseNotificationViewHolder> {
    private List<PurchaseNotification> purchaseNotifications;
    private OnItemClickListener clickListener;
    public PurchaseNotificationAdapter(List<PurchaseNotification> purchaseNotifications) {
        this.purchaseNotifications = purchaseNotifications;
    }
    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    @NonNull
    @Override
    public PurchaseNotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_notification_item, parent, false);
        return new PurchaseNotificationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseNotificationViewHolder holder, int position) {
        PurchaseNotification notification = purchaseNotifications.get(position);
        holder.bind(notification);
    }
    @Override
    public int getItemCount() {
        return purchaseNotifications.size();
    }
    public class PurchaseNotificationViewHolder extends RecyclerView.ViewHolder {

        private TextView notificationTitle;

        public PurchaseNotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationTitle = itemView.findViewById(R.id.notificationTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onItemClick(getAdapterPosition());
                    }
                }
            });

            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setColor(Color.WHITE);
            drawable.setCornerRadius(8);
            notificationTitle.setBackground(drawable);
            notificationTitle.bringToFront();
        }

        public void bind(PurchaseNotification notification) {
            notificationTitle.setText(notification.getTitle());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
