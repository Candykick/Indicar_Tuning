package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityBoardDetailBindingImpl extends ActivityBoardDetailBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(5);
        sIncludes.setIncludes(1, 
            new String[] {"layout_text_toolbar", "holder_board_header"},
            new int[] {2, 3},
            new int[] {R.layout.layout_text_toolbar, R.layout.holder_board_header});
        sIncludes.setIncludes(0, 
            new String[] {"layout_board_detail"},
            new int[] {4},
            new int[] {R.layout.layout_board_detail});
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final android.support.design.widget.CoordinatorLayout mboundView0;
    @NonNull
    private final android.support.design.widget.AppBarLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityBoardDetailBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private ActivityBoardDetailBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4
            , (com.iindicar.indicar.databinding.HolderBoardHeaderBinding) bindings[3]
            , (com.iindicar.indicar.databinding.LayoutBoardDetailBinding) bindings[4]
            , (com.iindicar.indicar.databinding.LayoutTextToolbarBinding) bindings[2]
            );
        this.mboundView0 = (android.support.design.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.support.design.widget.AppBarLayout) bindings[1];
        this.mboundView1.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x10L;
        }
        toolbar.invalidateAll();
        boardHeader.invalidateAll();
        content.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (toolbar.hasPendingBindings()) {
            return true;
        }
        if (boardHeader.hasPendingBindings()) {
            return true;
        }
        if (content.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.item == variableId) {
            setItem((com.iindicar.indicar.model.Board) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.iindicar.indicar.model.Board Item) {
        updateRegistration(0, Item);
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    @Override
    public void setLifecycleOwner(@Nullable android.arch.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        toolbar.setLifecycleOwner(lifecycleOwner);
        boardHeader.setLifecycleOwner(lifecycleOwner);
        content.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeItem((com.iindicar.indicar.model.Board) object, fieldId);
            case 1 :
                return onChangeContent((com.iindicar.indicar.databinding.LayoutBoardDetailBinding) object, fieldId);
            case 2 :
                return onChangeBoardHeader((com.iindicar.indicar.databinding.HolderBoardHeaderBinding) object, fieldId);
            case 3 :
                return onChangeToolbar((com.iindicar.indicar.databinding.LayoutTextToolbarBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeItem(com.iindicar.indicar.model.Board Item, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeContent(com.iindicar.indicar.databinding.LayoutBoardDetailBinding Content, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeBoardHeader(com.iindicar.indicar.databinding.HolderBoardHeaderBinding BoardHeader, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeToolbar(com.iindicar.indicar.databinding.LayoutTextToolbarBinding Toolbar, int fieldId) {
        if (fieldId == BR._all) {
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
        com.iindicar.indicar.model.Board item = mItem;

        if ((dirtyFlags & 0x11L) != 0) {
        }
        // batch finished
        if ((dirtyFlags & 0x11L) != 0) {
            // api target 1

            this.boardHeader.setItem(item);
            this.content.setItem(item);
        }
        executeBindingsOn(toolbar);
        executeBindingsOn(boardHeader);
        executeBindingsOn(content);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): content
        flag 2 (0x3L): boardHeader
        flag 3 (0x4L): toolbar
        flag 4 (0x5L): null
    flag mapping end*/
    //end
}