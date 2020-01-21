package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class HolderNotifySettingItemBindingImpl extends HolderNotifySettingItemBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView1;
    @NonNull
    private final android.widget.TextView mboundView2;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public HolderNotifySettingItemBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private HolderNotifySettingItemBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageButton) bindings[3]
            );
        this.btnAlarm.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.item == variableId) {
            setItem((com.iindicar.indicar.view.account.profile.NotifySettingActivity.NotifySettingItem) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.iindicar.indicar.view.account.profile.NotifySettingActivity.NotifySettingItem Item) {
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        com.iindicar.indicar.view.account.profile.NotifySettingActivity.NotifySettingItem item = mItem;
        java.lang.String itemSubtitle = null;
        android.graphics.drawable.Drawable itemIsActiveBtnAlarmAndroidDrawableBtnAlarmOnBtnAlarmAndroidDrawableBtnAlarmOff = null;
        java.lang.String itemTitle = null;
        boolean itemIsActive = false;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.subtitle
                    itemSubtitle = item.getSubtitle();
                    // read item.title
                    itemTitle = item.getTitle();
                    // read item.isActive
                    itemIsActive = item.isActive();
                }
            if((dirtyFlags & 0x3L) != 0) {
                if(itemIsActive) {
                        dirtyFlags |= 0x8L;
                }
                else {
                        dirtyFlags |= 0x4L;
                }
            }


                // read item.isActive ? @android:drawable/btn_alarm_on : @android:drawable/btn_alarm_off
                itemIsActiveBtnAlarmAndroidDrawableBtnAlarmOnBtnAlarmAndroidDrawableBtnAlarmOff = ((itemIsActive) ? (getDrawableFromResource(btnAlarm, R.drawable.btn_alarm_on)) : (getDrawableFromResource(btnAlarm, R.drawable.btn_alarm_off)));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.btnAlarm, itemIsActiveBtnAlarmAndroidDrawableBtnAlarmOnBtnAlarmAndroidDrawableBtnAlarmOff);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, itemTitle);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, itemSubtitle);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): null
        flag 2 (0x3L): item.isActive ? @android:drawable/btn_alarm_on : @android:drawable/btn_alarm_off
        flag 3 (0x4L): item.isActive ? @android:drawable/btn_alarm_on : @android:drawable/btn_alarm_off
    flag mapping end*/
    //end
}