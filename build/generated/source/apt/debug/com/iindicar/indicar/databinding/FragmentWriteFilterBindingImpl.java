package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentWriteFilterBindingImpl extends FragmentWriteFilterBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(11);
        sIncludes.setIncludes(1, 
            new String[] {"layout_write_filter_item", "layout_write_filter_item"},
            new int[] {4, 5},
            new int[] {R.layout.layout_write_filter_item, R.layout.layout_write_filter_item});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.recycler_board_filter, 6);
        sViewsWithIds.put(R.id.recycler_images, 7);
        sViewsWithIds.put(R.id.empty_view, 8);
        sViewsWithIds.put(R.id.btn_cancel, 9);
        sViewsWithIds.put(R.id.btn_next, 10);
    }
    // views
    @NonNull
    private final android.support.v4.widget.NestedScrollView mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @Nullable
    private final com.iindicar.indicar.databinding.LayoutWriteFilterItemBinding mboundView11;
    @Nullable
    private final com.iindicar.indicar.databinding.LayoutWriteFilterItemBinding mboundView12;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentWriteFilterBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }
    private FragmentWriteFilterBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageButton) bindings[2]
            , (android.widget.ImageButton) bindings[3]
            , (android.widget.Button) bindings[9]
            , (android.widget.Button) bindings[10]
            , (android.widget.TextView) bindings[8]
            , (android.support.v7.widget.RecyclerView) bindings[6]
            , (com.iindicar.indicar.utils.EmptyRecyclerView) bindings[7]
            );
        this.btnAlbum.setTag(null);
        this.btnCamera.setTag(null);
        this.mboundView0 = (android.support.v4.widget.NestedScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView11 = (com.iindicar.indicar.databinding.LayoutWriteFilterItemBinding) bindings[4];
        setContainedBinding(this.mboundView11);
        this.mboundView12 = (com.iindicar.indicar.databinding.LayoutWriteFilterItemBinding) bindings[5];
        setContainedBinding(this.mboundView12);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        mboundView11.invalidateAll();
        mboundView12.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (mboundView11.hasPendingBindings()) {
            return true;
        }
        if (mboundView12.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.presenter == variableId) {
            setPresenter((com.iindicar.indicar.view.community.boardWrite.WriteFilterPresenter) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setPresenter(@Nullable com.iindicar.indicar.view.community.boardWrite.WriteFilterPresenter Presenter) {
        this.mPresenter = Presenter;
    }

    @Override
    public void setLifecycleOwner(@Nullable android.arch.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        mboundView11.setLifecycleOwner(lifecycleOwner);
        mboundView12.setLifecycleOwner(lifecycleOwner);
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
        // batch finished
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            com.iindicar.indicar.utils.GlideUtil.loadFutureImageDrawable(this.btnAlbum, getDrawableFromResource(btnAlbum, R.drawable.background_write_album));
            com.iindicar.indicar.utils.GlideUtil.loadFutureImageDrawable(this.btnCamera, getDrawableFromResource(btnCamera, R.drawable.background_write_camera));
            this.mboundView11.setTitle(getRoot().getResources().getString(R.string.board_filter_hint));
            this.mboundView12.setTitle(getRoot().getResources().getString(R.string.image_filter_hint));
        }
        executeBindingsOn(mboundView11);
        executeBindingsOn(mboundView12);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): presenter
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}