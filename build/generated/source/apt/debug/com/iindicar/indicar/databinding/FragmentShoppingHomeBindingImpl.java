package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentShoppingHomeBindingImpl extends FragmentShoppingHomeBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.recycler_image, 4);
        sViewsWithIds.put(R.id.divider_best, 5);
        sViewsWithIds.put(R.id.tv_best, 6);
        sViewsWithIds.put(R.id.tv_best_more, 7);
        sViewsWithIds.put(R.id.recycler_best, 8);
        sViewsWithIds.put(R.id.divider_new, 9);
        sViewsWithIds.put(R.id.tv_new, 10);
        sViewsWithIds.put(R.id.tv_new_more, 11);
        sViewsWithIds.put(R.id.recycler_new, 12);
    }
    // views
    @NonNull
    private final android.widget.Button mboundView1;
    @NonNull
    private final android.widget.Button mboundView2;
    @NonNull
    private final android.widget.Button mboundView3;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentShoppingHomeBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private FragmentShoppingHomeBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.view.View) bindings[5]
            , (android.view.View) bindings[9]
            , (com.iindicar.indicar.utils.EmptyRecyclerView) bindings[8]
            , (android.support.v7.widget.RecyclerView) bindings[4]
            , (com.iindicar.indicar.utils.EmptyRecyclerView) bindings[12]
            , (android.support.v4.widget.SwipeRefreshLayout) bindings[0]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[11]
            );
        this.mboundView1 = (android.widget.Button) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.Button) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.Button) bindings[3];
        this.mboundView3.setTag(null);
        this.swipeLayoutShopping.setTag(null);
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
        if (BR.presenter == variableId) {
            setPresenter((com.iindicar.indicar.view.shopping.shoppingHome.ShoppingHomePresenter) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setPresenter(@Nullable com.iindicar.indicar.view.shopping.shoppingHome.ShoppingHomePresenter Presenter) {
        this.mPresenter = Presenter;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.presenter);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangePresenterIsLoading((android.databinding.ObservableBoolean) object, fieldId);
            case 1 :
                return onChangePresenterCurrentImage((android.databinding.ObservableInt) object, fieldId);
        }
        return false;
    }
    private boolean onChangePresenterIsLoading(android.databinding.ObservableBoolean PresenterIsLoading, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangePresenterCurrentImage(android.databinding.ObservableInt PresenterCurrentImage, int fieldId) {
        if (fieldId == BR._all) {
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
        com.iindicar.indicar.view.shopping.shoppingHome.ShoppingHomePresenter presenter = mPresenter;
        boolean presenterCurrentImageInt1 = false;
        android.databinding.ObservableBoolean presenterIsLoading = null;
        boolean presenterCurrentImageInt0 = false;
        float presenterCurrentImageInt2FloatFloat10FloatFloat05 = 0f;
        float presenterCurrentImageInt0FloatFloat10FloatFloat05 = 0f;
        boolean presenterCurrentImageInt2 = false;
        int presenterCurrentImageGet = 0;
        boolean presenterIsLoadingGet = false;
        float presenterCurrentImageInt1FloatFloat10FloatFloat05 = 0f;
        android.databinding.ObservableInt presenterCurrentImage = null;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (presenter != null) {
                        // read presenter.isLoading
                        presenterIsLoading = presenter.isLoading;
                    }
                    updateRegistration(0, presenterIsLoading);


                    if (presenterIsLoading != null) {
                        // read presenter.isLoading.get()
                        presenterIsLoadingGet = presenterIsLoading.get();
                    }
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (presenter != null) {
                        // read presenter.currentImage
                        presenterCurrentImage = presenter.currentImage;
                    }
                    updateRegistration(1, presenterCurrentImage);


                    if (presenterCurrentImage != null) {
                        // read presenter.currentImage.get()
                        presenterCurrentImageGet = presenterCurrentImage.get();
                    }


                    // read presenter.currentImage.get() == 1
                    presenterCurrentImageInt1 = (presenterCurrentImageGet) == (1);
                    // read presenter.currentImage.get() == 0
                    presenterCurrentImageInt0 = (presenterCurrentImageGet) == (0);
                    // read presenter.currentImage.get() == 2
                    presenterCurrentImageInt2 = (presenterCurrentImageGet) == (2);
                if((dirtyFlags & 0xeL) != 0) {
                    if(presenterCurrentImageInt1) {
                            dirtyFlags |= 0x200L;
                    }
                    else {
                            dirtyFlags |= 0x100L;
                    }
                }
                if((dirtyFlags & 0xeL) != 0) {
                    if(presenterCurrentImageInt0) {
                            dirtyFlags |= 0x80L;
                    }
                    else {
                            dirtyFlags |= 0x40L;
                    }
                }
                if((dirtyFlags & 0xeL) != 0) {
                    if(presenterCurrentImageInt2) {
                            dirtyFlags |= 0x20L;
                    }
                    else {
                            dirtyFlags |= 0x10L;
                    }
                }


                    // read presenter.currentImage.get() == 1 ? (float) 1.0 : (float) 0.5
                    presenterCurrentImageInt1FloatFloat10FloatFloat05 = ((presenterCurrentImageInt1) ? (((float) (1.0))) : (((float) (0.5))));
                    // read presenter.currentImage.get() == 0 ? (float) 1.0 : (float) 0.5
                    presenterCurrentImageInt0FloatFloat10FloatFloat05 = ((presenterCurrentImageInt0) ? (((float) (1.0))) : (((float) (0.5))));
                    // read presenter.currentImage.get() == 2 ? (float) 1.0 : (float) 0.5
                    presenterCurrentImageInt2FloatFloat10FloatFloat05 = ((presenterCurrentImageInt2) ? (((float) (1.0))) : (((float) (0.5))));
            }
        }
        // batch finished
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 11
            if(getBuildSdkInt() >= 11) {

                this.mboundView1.setAlpha(presenterCurrentImageInt0FloatFloat10FloatFloat05);
                this.mboundView2.setAlpha(presenterCurrentImageInt1FloatFloat10FloatFloat05);
                this.mboundView3.setAlpha(presenterCurrentImageInt2FloatFloat10FloatFloat05);
            }
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            com.iindicar.indicar.utils.BindingUtil.setRefreshing(this.swipeLayoutShopping, presenterIsLoadingGet);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): presenter.isLoading
        flag 1 (0x2L): presenter.currentImage
        flag 2 (0x3L): presenter
        flag 3 (0x4L): null
        flag 4 (0x5L): presenter.currentImage.get() == 2 ? (float) 1.0 : (float) 0.5
        flag 5 (0x6L): presenter.currentImage.get() == 2 ? (float) 1.0 : (float) 0.5
        flag 6 (0x7L): presenter.currentImage.get() == 0 ? (float) 1.0 : (float) 0.5
        flag 7 (0x8L): presenter.currentImage.get() == 0 ? (float) 1.0 : (float) 0.5
        flag 8 (0x9L): presenter.currentImage.get() == 1 ? (float) 1.0 : (float) 0.5
        flag 9 (0xaL): presenter.currentImage.get() == 1 ? (float) 1.0 : (float) 0.5
    flag mapping end*/
    //end
}