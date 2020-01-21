package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class HolderAlbumItemBindingImpl extends HolderAlbumItemBinding  {

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
    private final android.widget.FrameLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public HolderAlbumItemBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private HolderAlbumItemBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.TextView) bindings[3]
            , (android.widget.ImageView) bindings[1]
            , (android.view.View) bindings[2]
            );
        this.btnIndex.setTag(null);
        this.imageView.setTag(null);
        this.layoutSelected.setTag(null);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x10L;
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
            setItem((com.iindicar.indicar.data.local.GalleryVO) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.iindicar.indicar.data.local.GalleryVO Item) {
        updateRegistration(0, Item);
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
            case 0 :
                return onChangeItem((com.iindicar.indicar.data.local.GalleryVO) object, fieldId);
        }
        return false;
    }
    private boolean onChangeItem(com.iindicar.indicar.data.local.GalleryVO Item, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.mediaPath) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.selected) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        else if (fieldId == BR.index) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
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
        com.iindicar.indicar.data.local.GalleryVO item = mItem;
        java.lang.String itemIndex = null;
        java.lang.String itemMediaPath = null;
        boolean itemSelected = false;

        if ((dirtyFlags & 0x1fL) != 0) {


            if ((dirtyFlags & 0x19L) != 0) {

                    if (item != null) {
                        // read item.index
                        itemIndex = item.getIndex();
                    }
            }
            if ((dirtyFlags & 0x13L) != 0) {

                    if (item != null) {
                        // read item.mediaPath
                        itemMediaPath = item.getMediaPath();
                    }
            }
            if ((dirtyFlags & 0x15L) != 0) {

                    if (item != null) {
                        // read item.selected
                        itemSelected = item.isSelected();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x19L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.btnIndex, itemIndex);
        }
        if ((dirtyFlags & 0x13L) != 0) {
            // api target 1

            com.iindicar.indicar.utils.GlideUtil.loadImageCenterCrop(this.imageView, itemMediaPath);
        }
        if ((dirtyFlags & 0x15L) != 0) {
            // api target 1

            this.layoutSelected.setVisibility(com.iindicar.indicar.utils.BindingUtil.setVisibility(itemSelected));
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): item.mediaPath
        flag 2 (0x3L): item.selected
        flag 3 (0x4L): item.index
        flag 4 (0x5L): null
    flag mapping end*/
    //end
}