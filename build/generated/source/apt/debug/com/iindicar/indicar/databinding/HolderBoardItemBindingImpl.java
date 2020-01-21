package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class HolderBoardItemBindingImpl extends HolderBoardItemBinding  {

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
    private final android.widget.ImageView mboundView2;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    private final android.widget.TextView mboundView4;
    @NonNull
    private final android.widget.TextView mboundView5;
    @NonNull
    private final android.widget.TextView mboundView6;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public HolderBoardItemBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }
    private HolderBoardItemBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.ImageView) bindings[1]
            );
        this.ivNotice.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView2 = (android.widget.ImageView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (android.widget.TextView) bindings[6];
        this.mboundView6.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
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
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeItem((com.iindicar.indicar.model.Board) object, fieldId);
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
        else if (fieldId == BR.likeCount) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.commentCount) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
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
        java.lang.String stringValueOfAndroidDatabindingViewDataBindingSafeUnboxItemCommentCount = null;
        int androidDatabindingViewDataBindingSafeUnboxItemLikeCount = 0;
        java.lang.String itemMainText = null;
        java.lang.Integer itemLikeCount = null;
        java.lang.String itemAuthorImageUrl = null;
        java.lang.String itemAuthorName = null;
        java.lang.String itemMainFileUrl = null;
        int androidDatabindingViewDataBindingSafeUnboxItemCommentCount = 0;
        java.lang.Integer itemCommentCount = null;
        java.lang.String stringValueOfAndroidDatabindingViewDataBindingSafeUnboxItemLikeCount = null;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0x9L) != 0) {

                    if (item != null) {
                        // read item.mainText
                        itemMainText = item.getMainText();
                        // read item.authorImageUrl
                        itemAuthorImageUrl = item.getAuthorImageUrl();
                        // read item.authorName
                        itemAuthorName = item.getAuthorName();
                        // read item.mainFileUrl
                        itemMainFileUrl = item.getMainFileUrl();
                    }
            }
            if ((dirtyFlags & 0xbL) != 0) {

                    if (item != null) {
                        // read item.likeCount
                        itemLikeCount = item.getLikeCount();
                    }


                    // read android.databinding.ViewDataBinding.safeUnbox(item.likeCount)
                    androidDatabindingViewDataBindingSafeUnboxItemLikeCount = android.databinding.ViewDataBinding.safeUnbox(itemLikeCount);


                    // read String.valueOf(android.databinding.ViewDataBinding.safeUnbox(item.likeCount))
                    stringValueOfAndroidDatabindingViewDataBindingSafeUnboxItemLikeCount = java.lang.String.valueOf(androidDatabindingViewDataBindingSafeUnboxItemLikeCount);
            }
            if ((dirtyFlags & 0xdL) != 0) {

                    if (item != null) {
                        // read item.commentCount
                        itemCommentCount = item.getCommentCount();
                    }


                    // read android.databinding.ViewDataBinding.safeUnbox(item.commentCount)
                    androidDatabindingViewDataBindingSafeUnboxItemCommentCount = android.databinding.ViewDataBinding.safeUnbox(itemCommentCount);


                    // read String.valueOf(android.databinding.ViewDataBinding.safeUnbox(item.commentCount))
                    stringValueOfAndroidDatabindingViewDataBindingSafeUnboxItemCommentCount = java.lang.String.valueOf(androidDatabindingViewDataBindingSafeUnboxItemCommentCount);
            }
        }
        // batch finished
        if ((dirtyFlags & 0x9L) != 0) {
            // api target 1

            com.iindicar.indicar.utils.GlideUtil.loadImageCenterCrop(this.ivNotice, itemMainFileUrl);
            com.iindicar.indicar.utils.GlideUtil.loadCircleImage(this.mboundView2, itemAuthorImageUrl);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, itemAuthorName);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, itemMainText);
        }
        if ((dirtyFlags & 0xbL) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, stringValueOfAndroidDatabindingViewDataBindingSafeUnboxItemLikeCount);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView6, stringValueOfAndroidDatabindingViewDataBindingSafeUnboxItemCommentCount);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): item.likeCount
        flag 2 (0x3L): item.commentCount
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}