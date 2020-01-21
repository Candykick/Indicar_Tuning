package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class HolderBoardFilterBindingImpl extends HolderBoardFilterBinding  {

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
    private final android.widget.ImageView mboundView1;
    @NonNull
    private final android.view.View mboundView2;
    @NonNull
    private final android.widget.ImageView mboundView3;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public HolderBoardFilterBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private HolderBoardFilterBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.FrameLayout) bindings[0]
            );
        this.itemFrame.setTag(null);
        this.mboundView1 = (android.widget.ImageView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.view.View) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.ImageView) bindings[3];
        this.mboundView3.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
            setItem((com.iindicar.indicar.model.BoardFilterItem) variable);
        }
        else if (BR.boardType == variableId) {
            setBoardType((android.databinding.ObservableField) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.iindicar.indicar.model.BoardFilterItem Item) {
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }
    public void setBoardType(@Nullable android.databinding.ObservableField BoardType) {
        updateRegistration(0, BoardType);
        this.mBoardType = BoardType;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.boardType);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeBoardType((android.databinding.ObservableField) object, fieldId);
        }
        return false;
    }
    private boolean onChangeBoardType(android.databinding.ObservableField BoardType, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
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
        com.iindicar.indicar.model.BoardFilterItem item = mItem;
        java.lang.Object boardTypeGet = null;
        android.databinding.ObservableField boardType = mBoardType;
        boolean itemTypeNameEqualsBoardType = false;
        int itemImageId = 0;
        java.lang.String itemTypeName = null;

        if ((dirtyFlags & 0x7L) != 0) {


            if ((dirtyFlags & 0x6L) != 0) {

                    if (item != null) {
                        // read item.imageId
                        itemImageId = item.getImageId();
                    }
            }

                if (item != null) {
                    // read item.typeName
                    itemTypeName = item.getTypeName();
                }
                if (boardType != null) {
                    // read boardType.get()
                    boardTypeGet = boardType.get();
                }


                if (itemTypeName != null) {
                    // read item.typeName.equals(boardType.get())
                    itemTypeNameEqualsBoardType = itemTypeName.equals(boardTypeGet);
                }
        }
        // batch finished
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            com.iindicar.indicar.utils.GlideUtil.loadImageResource(this.mboundView1, itemImageId);
        }
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            this.mboundView2.setVisibility(com.iindicar.indicar.utils.BindingUtil.setVisibility(itemTypeNameEqualsBoardType));
            this.mboundView3.setVisibility(com.iindicar.indicar.utils.BindingUtil.setVisibility(itemTypeNameEqualsBoardType));
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): boardType
        flag 1 (0x2L): item
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}