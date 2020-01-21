package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class LayoutBoardDetailBindingImpl extends LayoutBoardDetailBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(11);
        sIncludes.setIncludes(1, 
            new String[] {"holder_comment_box"},
            new int[] {7},
            new int[] {R.layout.holder_comment_box});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.recycler_file, 8);
        sViewsWithIds.put(R.id.recycler_comment, 9);
        sViewsWithIds.put(R.id.empty_view, 10);
    }
    // views
    @NonNull
    private final android.support.v4.widget.NestedScrollView mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @NonNull
    private final android.widget.TextView mboundView2;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    private final android.widget.TextView mboundView6;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public LayoutBoardDetailBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }
    private LayoutBoardDetailBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (android.widget.ImageButton) bindings[4]
            , (com.iindicar.indicar.databinding.HolderCommentBoxBinding) bindings[7]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.LinearLayout) bindings[10]
            , (com.iindicar.indicar.utils.EmptyRecyclerView) bindings[9]
            , (android.support.v7.widget.RecyclerView) bindings[8]
            );
        this.btnMenu.setTag(null);
        this.commentMore.setTag(null);
        this.mboundView0 = (android.support.v4.widget.NestedScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView6 = (android.widget.TextView) bindings[6];
        this.mboundView6.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x40L;
        }
        commentBox.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (commentBox.hasPendingBindings()) {
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
        else if (BR.activity == variableId) {
            setActivity((com.iindicar.indicar.view.community.boardDetail.BoardDetailActivity) variable);
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
    public void setActivity(@Nullable com.iindicar.indicar.view.community.boardDetail.BoardDetailActivity Activity) {
        this.mActivity = Activity;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.activity);
        super.requestRebind();
    }

    @Override
    public void setLifecycleOwner(@Nullable android.arch.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        commentBox.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeItem((com.iindicar.indicar.model.Board) object, fieldId);
            case 1 :
                return onChangeCommentBox((com.iindicar.indicar.databinding.HolderCommentBoxBinding) object, fieldId);
            case 2 :
                return onChangeActivityIsMyBoard((android.databinding.ObservableBoolean) object, fieldId);
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
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        else if (fieldId == BR.commentCount) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeCommentBox(com.iindicar.indicar.databinding.HolderCommentBoxBinding CommentBox, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeActivityIsMyBoard(android.databinding.ObservableBoolean ActivityIsMyBoard, int fieldId) {
        if (fieldId == BR._all) {
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
        boolean androidDatabindingViewDataBindingSafeUnboxItemCommentCountInt5 = false;
        com.iindicar.indicar.view.community.boardDetail.BoardDetailActivity activity = mActivity;
        int androidDatabindingViewDataBindingSafeUnboxItemLikeCount = 0;
        java.lang.String stringValueOfAndroidDatabindingViewDataBindingSafeUnboxItemCommentCountInt5 = null;
        java.lang.Integer itemLikeCount = null;
        int AndroidDatabindingViewDataBindingSafeUnboxItemCommentCountInt51 = 0;
        int androidDatabindingViewDataBindingSafeUnboxItemCommentCount = 0;
        java.lang.Integer itemCommentCount = null;
        java.lang.String stringValueOfAndroidDatabindingViewDataBindingSafeUnboxItemLikeCount = null;
        boolean activityIsMyBoardGet = false;
        android.databinding.ObservableBoolean activityIsMyBoard = null;

        if ((dirtyFlags & 0x71L) != 0) {


            if ((dirtyFlags & 0x51L) != 0) {

                    if (item != null) {
                        // read item.likeCount
                        itemLikeCount = item.getLikeCount();
                    }


                    // read android.databinding.ViewDataBinding.safeUnbox(item.likeCount)
                    androidDatabindingViewDataBindingSafeUnboxItemLikeCount = android.databinding.ViewDataBinding.safeUnbox(itemLikeCount);


                    // read String.valueOf(android.databinding.ViewDataBinding.safeUnbox(item.likeCount))
                    stringValueOfAndroidDatabindingViewDataBindingSafeUnboxItemLikeCount = java.lang.String.valueOf(androidDatabindingViewDataBindingSafeUnboxItemLikeCount);
            }
            if ((dirtyFlags & 0x61L) != 0) {

                    if (item != null) {
                        // read item.commentCount
                        itemCommentCount = item.getCommentCount();
                    }


                    // read android.databinding.ViewDataBinding.safeUnbox(item.commentCount)
                    androidDatabindingViewDataBindingSafeUnboxItemCommentCount = android.databinding.ViewDataBinding.safeUnbox(itemCommentCount);


                    // read String.valueOf(android.databinding.ViewDataBinding.safeUnbox(item.commentCount))
                    stringValueOfAndroidDatabindingViewDataBindingSafeUnboxItemCommentCount = java.lang.String.valueOf(androidDatabindingViewDataBindingSafeUnboxItemCommentCount);
                    // read android.databinding.ViewDataBinding.safeUnbox(item.commentCount) > 5
                    androidDatabindingViewDataBindingSafeUnboxItemCommentCountInt5 = (androidDatabindingViewDataBindingSafeUnboxItemCommentCount) > (5);
                    // read (android.databinding.ViewDataBinding.safeUnbox(item.commentCount)) - (5)
                    AndroidDatabindingViewDataBindingSafeUnboxItemCommentCountInt51 = (androidDatabindingViewDataBindingSafeUnboxItemCommentCount) - (5);


                    // read String.valueOf((android.databinding.ViewDataBinding.safeUnbox(item.commentCount)) - (5))
                    stringValueOfAndroidDatabindingViewDataBindingSafeUnboxItemCommentCountInt5 = java.lang.String.valueOf(AndroidDatabindingViewDataBindingSafeUnboxItemCommentCountInt51);
            }
        }
        if ((dirtyFlags & 0x4cL) != 0) {



                if (activity != null) {
                    // read activity.isMyBoard
                    activityIsMyBoard = activity.isMyBoard;
                }
                updateRegistration(2, activityIsMyBoard);


                if (activityIsMyBoard != null) {
                    // read activity.isMyBoard.get()
                    activityIsMyBoardGet = activityIsMyBoard.get();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x4cL) != 0) {
            // api target 1

            this.btnMenu.setVisibility(com.iindicar.indicar.utils.BindingUtil.setVisibility(activityIsMyBoardGet));
        }
        if ((dirtyFlags & 0x61L) != 0) {
            // api target 1

            this.commentMore.setVisibility(com.iindicar.indicar.utils.BindingUtil.setVisibility(androidDatabindingViewDataBindingSafeUnboxItemCommentCountInt5));
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, stringValueOfAndroidDatabindingViewDataBindingSafeUnboxItemCommentCount);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView6, stringValueOfAndroidDatabindingViewDataBindingSafeUnboxItemCommentCountInt5);
        }
        if ((dirtyFlags & 0x51L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, stringValueOfAndroidDatabindingViewDataBindingSafeUnboxItemLikeCount);
        }
        executeBindingsOn(commentBox);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): commentBox
        flag 2 (0x3L): activity.isMyBoard
        flag 3 (0x4L): activity
        flag 4 (0x5L): item.likeCount
        flag 5 (0x6L): item.commentCount
        flag 6 (0x7L): null
    flag mapping end*/
    //end
}