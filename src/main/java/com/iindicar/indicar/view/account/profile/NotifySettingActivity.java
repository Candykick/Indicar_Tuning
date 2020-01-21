package com.iindicar.indicar.view.account.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.ActivityNotifySettingBinding;
import com.iindicar.indicar.databinding.HolderNotifySettingItemBinding;
import com.iindicar.indicar.view.BaseActivity;
import com.iindicar.indicar.view.adapter.BaseRecyclerAdapter;
import com.iindicar.indicar.view.listener.OnItemClickListener;

public class NotifySettingActivity extends BaseActivity<ActivityNotifySettingBinding> implements OnItemClickListener {

    private NotificationAdapter adapter;
    private SharedPreferences prefLogin;
    SharedPreferences.Editor editor;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notify_setting;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {

        // 툴바 생성
        binding.toolbar.btnLeft.setOnClickListener(v -> finish());
        binding.toolbar.tvCenter.setText(getString(R.string.notification_setting));

        //알람 설정 가져오기
        prefLogin = getApplication().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        editor = prefLogin.edit();

        // 리사이클러뷰 생성
        adapter = new NotificationAdapter(this, prefLogin.getBoolean("EventAlarm", true), prefLogin.getBoolean("OtherAlarm", true));
        adapter.setOnItemClickListener(this);

        binding.recyclerNotification.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerNotification.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {

        NotifySettingItem item = adapter.getItem(position);
        switch(position) {
            case 0:
                item.setActive(!item.isActive());
                editor.putBoolean("EventAlarm", item.isActive());
                break;
            case 1:
                item.setActive(!item.isActive());
                editor.putBoolean("OtherAlarm", item.isActive());
                break;
        }

        editor.commit();
        binding.recyclerNotification.getAdapter().notifyDataSetChanged();
    }

    public class NotifySettingItem {

        private String title;

        private String subtitle;

        private boolean isActive;

        public NotifySettingItem(String title, String subtitle, boolean isActive) {
            this.title = title;
            this.subtitle = subtitle;
            this.isActive = isActive;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }
    }

    public class NotificationAdapter extends BaseRecyclerAdapter<NotifySettingItem, NotificationAdapter.ViewHolder> {

        public NotificationAdapter(Context context, boolean eventAlarm, boolean otherAlarm) {
            super(context);

            this.addItem(new NotifySettingItem(
                    context.getString(R.string.event_alarm_title),
                    context.getString(R.string.event_alarm_subtitle),
                    eventAlarm));

            this.addItem(new NotifySettingItem(
                    context.getString(R.string.notice_alarm_title),
                    context.getString(R.string.notice_alarm_subtitle),
                    otherAlarm));
        }

        @Override
        protected void onBindView(ViewHolder holder, final int position) {

            holder.binding.setItem(itemList.get(position));

            if(onItemClickListener != null){
                holder.binding.btnAlarm.setOnClickListener(v -> onItemClickListener.onItemClick(position));
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.holder_notify_setting_item, parent, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            protected HolderNotifySettingItemBinding binding;

            public ViewHolder(View itemView) {
                super(itemView);
                binding = DataBindingUtil.bind(itemView);
            }
        }
    }
}
