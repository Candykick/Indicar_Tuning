package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityTutorialBindingImpl extends ActivityTutorialBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.view_pager_welcome, 6);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    private final android.widget.Button mboundView2;
    @NonNull
    private final android.widget.Button mboundView3;
    @NonNull
    private final android.widget.Button mboundView4;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityTutorialBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }
    private ActivityTutorialBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.Button) bindings[5]
            , (android.widget.Button) bindings[1]
            , (android.support.v4.view.ViewPager) bindings[6]
            );
        this.btnNext.setTag(null);
        this.btnSkip.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView2 = (android.widget.Button) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.Button) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.Button) bindings[4];
        this.mboundView4.setTag(null);
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
        if (BR.activity == variableId) {
            setActivity((com.iindicar.indicar.view.main.tutorial.TutorialActivity) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setActivity(@Nullable com.iindicar.indicar.view.main.tutorial.TutorialActivity Activity) {
        this.mActivity = Activity;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.activity);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeActivityCurrentPage((android.databinding.ObservableInt) object, fieldId);
        }
        return false;
    }
    private boolean onChangeActivityCurrentPage(android.databinding.ObservableInt ActivityCurrentPage, int fieldId) {
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
        boolean activityCurrentPageInt0 = false;
        com.iindicar.indicar.view.main.tutorial.TutorialActivity activity = mActivity;
        int activityCurrentPageInt2ViewINVISIBLEViewVISIBLE = 0;
        java.lang.String activityCurrentPageInt2BtnNextAndroidStringStartBtnNextAndroidStringNext = null;
        int activityCurrentPageGet = 0;
        boolean activityCurrentPageInt1 = false;
        boolean activityCurrentPageInt2 = false;
        float activityCurrentPageInt0FloatFloat10FloatFloat05 = 0f;
        android.databinding.ObservableInt activityCurrentPage = null;
        float activityCurrentPageInt1FloatFloat10FloatFloat05 = 0f;
        float activityCurrentPageInt2FloatFloat10FloatFloat05 = 0f;

        if ((dirtyFlags & 0x7L) != 0) {



                if (activity != null) {
                    // read activity.currentPage
                    activityCurrentPage = activity.currentPage;
                }
                updateRegistration(0, activityCurrentPage);


                if (activityCurrentPage != null) {
                    // read activity.currentPage.get()
                    activityCurrentPageGet = activityCurrentPage.get();
                }


                // read activity.currentPage.get() == 0
                activityCurrentPageInt0 = (activityCurrentPageGet) == (0);
                // read activity.currentPage.get() == 1
                activityCurrentPageInt1 = (activityCurrentPageGet) == (1);
                // read activity.currentPage.get() == 2
                activityCurrentPageInt2 = (activityCurrentPageGet) == (2);
            if((dirtyFlags & 0x7L) != 0) {
                if(activityCurrentPageInt0) {
                        dirtyFlags |= 0x100L;
                }
                else {
                        dirtyFlags |= 0x80L;
                }
            }
            if((dirtyFlags & 0x7L) != 0) {
                if(activityCurrentPageInt1) {
                        dirtyFlags |= 0x400L;
                }
                else {
                        dirtyFlags |= 0x200L;
                }
            }
            if((dirtyFlags & 0x7L) != 0) {
                if(activityCurrentPageInt2) {
                        dirtyFlags |= 0x10L;
                        dirtyFlags |= 0x40L;
                        dirtyFlags |= 0x1000L;
                }
                else {
                        dirtyFlags |= 0x8L;
                        dirtyFlags |= 0x20L;
                        dirtyFlags |= 0x800L;
                }
            }


                // read activity.currentPage.get() == 0 ? (float) 1.0 : (float) 0.5
                activityCurrentPageInt0FloatFloat10FloatFloat05 = ((activityCurrentPageInt0) ? (((float) (1.0))) : (((float) (0.5))));
                // read activity.currentPage.get() == 1 ? (float) 1.0 : (float) 0.5
                activityCurrentPageInt1FloatFloat10FloatFloat05 = ((activityCurrentPageInt1) ? (((float) (1.0))) : (((float) (0.5))));
                // read activity.currentPage.get() == 2 ? View.INVISIBLE : View.VISIBLE
                activityCurrentPageInt2ViewINVISIBLEViewVISIBLE = ((activityCurrentPageInt2) ? (android.view.View.INVISIBLE) : (android.view.View.VISIBLE));
                // read activity.currentPage.get() == 2 ? @android:string/start : @android:string/next
                activityCurrentPageInt2BtnNextAndroidStringStartBtnNextAndroidStringNext = ((activityCurrentPageInt2) ? (btnNext.getResources().getString(R.string.start)) : (btnNext.getResources().getString(R.string.next)));
                // read activity.currentPage.get() == 2 ? (float) 1.0 : (float) 0.5
                activityCurrentPageInt2FloatFloat10FloatFloat05 = ((activityCurrentPageInt2) ? (((float) (1.0))) : (((float) (0.5))));
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.btnNext, activityCurrentPageInt2BtnNextAndroidStringStartBtnNextAndroidStringNext);
            this.btnSkip.setVisibility(activityCurrentPageInt2ViewINVISIBLEViewVISIBLE);
            // api target 11
            if(getBuildSdkInt() >= 11) {

                this.mboundView2.setAlpha(activityCurrentPageInt0FloatFloat10FloatFloat05);
                this.mboundView3.setAlpha(activityCurrentPageInt1FloatFloat10FloatFloat05);
                this.mboundView4.setAlpha(activityCurrentPageInt2FloatFloat10FloatFloat05);
            }
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): activity.currentPage
        flag 1 (0x2L): activity
        flag 2 (0x3L): null
        flag 3 (0x4L): activity.currentPage.get() == 2 ? View.INVISIBLE : View.VISIBLE
        flag 4 (0x5L): activity.currentPage.get() == 2 ? View.INVISIBLE : View.VISIBLE
        flag 5 (0x6L): activity.currentPage.get() == 2 ? @android:string/start : @android:string/next
        flag 6 (0x7L): activity.currentPage.get() == 2 ? @android:string/start : @android:string/next
        flag 7 (0x8L): activity.currentPage.get() == 0 ? (float) 1.0 : (float) 0.5
        flag 8 (0x9L): activity.currentPage.get() == 0 ? (float) 1.0 : (float) 0.5
        flag 9 (0xaL): activity.currentPage.get() == 1 ? (float) 1.0 : (float) 0.5
        flag 10 (0xbL): activity.currentPage.get() == 1 ? (float) 1.0 : (float) 0.5
        flag 11 (0xcL): activity.currentPage.get() == 2 ? (float) 1.0 : (float) 0.5
        flag 12 (0xdL): activity.currentPage.get() == 2 ? (float) 1.0 : (float) 0.5
    flag mapping end*/
    //end
}