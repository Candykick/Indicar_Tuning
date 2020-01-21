package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class HolderBoardHeaderBindingImpl extends HolderBoardHeaderBinding  {

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
    private final android.widget.ImageView mboundView1;
    @NonNull
    private final android.widget.TextView mboundView2;
    @NonNull
    private final android.widget.TextView mboundView3;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public HolderBoardHeaderBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private HolderBoardHeaderBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.ImageButton) bindings[4]
            , (android.widget.ImageButton) bindings[5]
            );
        this.btnComment.setTag(null);
        this.btnLike.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.ImageView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
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
        else if (fieldId == BR.likeBoard) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
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
        android.graphics.drawable.Drawable itemLikeBoardBtnLikeAndroidDrawableBtnHeartOnBtnLikeAndroidDrawableBtnHeartOff = null;
        boolean itemLikeBoard = false;
        java.lang.String itemAuthorImageUrl = null;
        java.lang.String itemAuthorName = null;
        java.lang.String itemWriteTime = null;

        if ((dirtyFlags & 0x7L) != 0) {



                if (item != null) {
                    // read item.likeBoard
                    itemLikeBoard = item.isLikeBoard();
                }
            if((dirtyFlags & 0x7L) != 0) {
                if(itemLikeBoard) {
                        dirtyFlags |= 0x10L;
                }
                else {
                        dirtyFlags |= 0x8L;
                }
            }


                // read item.likeBoard ? @android:drawable/btn_heart_on : @android:drawable/btn_heart_off
                itemLikeBoardBtnLikeAndroidDrawableBtnHeartOnBtnLikeAndroidDrawableBtnHeartOff = ((itemLikeBoard) ? (getDrawableFromResource(btnLike, R.drawable.btn_heart_on)) : (getDrawableFromResource(btnLike, R.drawable.btn_heart_off)));
            if ((dirtyFlags & 0x5L) != 0) {

                    if (item != null) {
                        // read item.authorImageUrl
                        itemAuthorImageUrl = item.getAuthorImageUrl();
                        // read item.authorName
                        itemAuthorName = item.getAuthorName();
                        // read item.writeTime
                        itemWriteTime = item.getWriteTime();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.btnComment, getDrawableFromResource(btnComment, R.drawable.btn_board_comment));
        }
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.btnLike, itemLikeBoardBtnLikeAndroidDrawableBtnHeartOnBtnLikeAndroidDrawableBtnHeartOff);
        }
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            com.iindicar.indicar.utils.GlideUtil.loadCircleImage(this.mboundView1, itemAuthorImageUrl);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, itemAuthorName);
            com.iindicar.indicar.utils.BindingUtil.convertDateToDisplayText(this.mboundView3, itemWriteTime);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): item.likeBoard
        flag 2 (0x3L): null
        flag 3 (0x4L): item.likeBoard ? @android:drawable/btn_heart_on : @android:drawable/btn_heart_off
        flag 4 (0x5L): item.likeBoard ? @android:drawable/btn_heart_on : @android:drawable/btn_heart_off
    flag mapping end*/
    //end
}