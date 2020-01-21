package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class HolderCommentItemBindingImpl extends HolderCommentItemBinding  {

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
    private final android.view.View mboundView1;
    @NonNull
    private final android.widget.ImageView mboundView2;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    private final android.widget.TextView mboundView4;
    @NonNull
    private final android.widget.TextView mboundView5;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public HolderCommentItemBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private HolderCommentItemBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[0]
            );
        this.itemPanel.setTag(null);
        this.mboundView1 = (android.view.View) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.ImageView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.TextView) bindings[5];
        this.mboundView5.setTag(null);
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
            setItem((com.iindicar.indicar.model.Comment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.iindicar.indicar.model.Comment Item) {
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
        com.iindicar.indicar.model.Comment item = mItem;
        int itemParentId = 0;
        java.lang.String itemFirstTime = null;
        java.lang.String itemText = null;
        java.lang.String itemUserName = null;
        java.lang.String itemUserImageUrl = null;
        boolean itemIdItemParentId = false;
        int itemId = 0;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.parentId
                    itemParentId = item.getParentId();
                    // read item.firstTime
                    itemFirstTime = item.getFirstTime();
                    // read item.text
                    itemText = item.getText();
                    // read item.userName
                    itemUserName = item.getUserName();
                    // read item.userImageUrl
                    itemUserImageUrl = item.getUserImageUrl();
                    // read item.id
                    itemId = item.getId();
                }


                // read item.id != item.parentId
                itemIdItemParentId = (itemId) != (itemParentId);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.mboundView1.setVisibility(com.iindicar.indicar.utils.BindingUtil.setVisibility(itemIdItemParentId));
            com.iindicar.indicar.utils.GlideUtil.loadCircleImage(this.mboundView2, itemUserImageUrl);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, itemUserName);
            com.iindicar.indicar.utils.BindingUtil.convertDateToDisplayText(this.mboundView4, itemFirstTime);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, itemText);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}