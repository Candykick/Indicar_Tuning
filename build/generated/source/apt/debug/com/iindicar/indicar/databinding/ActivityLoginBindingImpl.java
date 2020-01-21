package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityLoginBindingImpl extends ActivityLoginBinding implements com.iindicar.indicar.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.view_pager_welcome, 12);
        sViewsWithIds.put(R.id.loadinglogin, 13);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    private final android.widget.Button mboundView1;
    @NonNull
    private final android.widget.ImageButton mboundView11;
    @NonNull
    private final android.widget.Button mboundView2;
    @NonNull
    private final android.widget.Button mboundView3;
    @NonNull
    private final android.widget.ImageButton mboundView5;
    @NonNull
    private final android.widget.ImageButton mboundView7;
    @NonNull
    private final android.widget.ImageButton mboundView9;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback15;
    @Nullable
    private final android.view.View.OnClickListener mCallback16;
    @Nullable
    private final android.view.View.OnClickListener mCallback13;
    @Nullable
    private final android.view.View.OnClickListener mCallback14;
    @Nullable
    private final android.view.View.OnClickListener mCallback11;
    @Nullable
    private final android.view.View.OnClickListener mCallback12;
    @Nullable
    private final android.view.View.OnClickListener mCallback17;
    @Nullable
    private final android.view.View.OnClickListener mCallback10;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityLoginBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private ActivityLoginBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.LinearLayout) bindings[13]
            , (android.support.v4.view.ViewPager) bindings[12]
            );
        this.btnLoginFacebook.setTag(null);
        this.btnLoginGoogle.setTag(null);
        this.btnLoginKakao.setTag(null);
        this.btnLoginLine.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.Button) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView11 = (android.widget.ImageButton) bindings[11];
        this.mboundView11.setTag(null);
        this.mboundView2 = (android.widget.Button) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.Button) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView5 = (android.widget.ImageButton) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView7 = (android.widget.ImageButton) bindings[7];
        this.mboundView7.setTag(null);
        this.mboundView9 = (android.widget.ImageButton) bindings[9];
        this.mboundView9.setTag(null);
        setRootTag(root);
        // listeners
        mCallback15 = new com.iindicar.indicar.generated.callback.OnClickListener(this, 6);
        mCallback16 = new com.iindicar.indicar.generated.callback.OnClickListener(this, 7);
        mCallback13 = new com.iindicar.indicar.generated.callback.OnClickListener(this, 4);
        mCallback14 = new com.iindicar.indicar.generated.callback.OnClickListener(this, 5);
        mCallback11 = new com.iindicar.indicar.generated.callback.OnClickListener(this, 2);
        mCallback12 = new com.iindicar.indicar.generated.callback.OnClickListener(this, 3);
        mCallback17 = new com.iindicar.indicar.generated.callback.OnClickListener(this, 8);
        mCallback10 = new com.iindicar.indicar.generated.callback.OnClickListener(this, 1);
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
        if (BR.activity == variableId) {
            setActivity((com.iindicar.indicar.view.main.login.LoginActivity) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setActivity(@Nullable com.iindicar.indicar.view.main.login.LoginActivity Activity) {
        this.mActivity = Activity;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.activity);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeActivityIsKorean((android.databinding.ObservableBoolean) object, fieldId);
            case 1 :
                return onChangeActivityCurrentPage((android.databinding.ObservableInt) object, fieldId);
        }
        return false;
    }
    private boolean onChangeActivityIsKorean(android.databinding.ObservableBoolean ActivityIsKorean, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeActivityCurrentPage(android.databinding.ObservableInt ActivityCurrentPage, int fieldId) {
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
        boolean activityCurrentPageInt0 = false;
        com.iindicar.indicar.view.main.login.LoginActivity activity = mActivity;
        int activityCurrentPageGet = 0;
        boolean activityCurrentPageInt1 = false;
        android.databinding.ObservableBoolean activityIsKorean = null;
        boolean activityCurrentPageInt2 = false;
        boolean ActivityIsKorean1 = false;
        boolean activityIsKoreanGet = false;
        float activityCurrentPageInt0FloatFloat10FloatFloat05 = 0f;
        android.databinding.ObservableInt activityCurrentPage = null;
        float activityCurrentPageInt1FloatFloat10FloatFloat05 = 0f;
        float activityCurrentPageInt2FloatFloat10FloatFloat05 = 0f;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (activity != null) {
                        // read activity.isKorean
                        activityIsKorean = activity.isKorean;
                    }
                    updateRegistration(0, activityIsKorean);


                    if (activityIsKorean != null) {
                        // read activity.isKorean.get()
                        activityIsKoreanGet = activityIsKorean.get();
                    }


                    // read !activity.isKorean.get()
                    ActivityIsKorean1 = !activityIsKoreanGet;
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (activity != null) {
                        // read activity.currentPage
                        activityCurrentPage = activity.currentPage;
                    }
                    updateRegistration(1, activityCurrentPage);


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
                if((dirtyFlags & 0xeL) != 0) {
                    if(activityCurrentPageInt0) {
                            dirtyFlags |= 0x20L;
                    }
                    else {
                            dirtyFlags |= 0x10L;
                    }
                }
                if((dirtyFlags & 0xeL) != 0) {
                    if(activityCurrentPageInt1) {
                            dirtyFlags |= 0x80L;
                    }
                    else {
                            dirtyFlags |= 0x40L;
                    }
                }
                if((dirtyFlags & 0xeL) != 0) {
                    if(activityCurrentPageInt2) {
                            dirtyFlags |= 0x200L;
                    }
                    else {
                            dirtyFlags |= 0x100L;
                    }
                }


                    // read activity.currentPage.get() == 0 ? (float) 1.0 : (float) 0.5
                    activityCurrentPageInt0FloatFloat10FloatFloat05 = ((activityCurrentPageInt0) ? (((float) (1.0))) : (((float) (0.5))));
                    // read activity.currentPage.get() == 1 ? (float) 1.0 : (float) 0.5
                    activityCurrentPageInt1FloatFloat10FloatFloat05 = ((activityCurrentPageInt1) ? (((float) (1.0))) : (((float) (0.5))));
                    // read activity.currentPage.get() == 2 ? (float) 1.0 : (float) 0.5
                    activityCurrentPageInt2FloatFloat10FloatFloat05 = ((activityCurrentPageInt2) ? (((float) (1.0))) : (((float) (0.5))));
            }
        }
        // batch finished
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            this.btnLoginFacebook.setOnClickListener(mCallback12);
            this.btnLoginGoogle.setOnClickListener(mCallback10);
            this.btnLoginKakao.setOnClickListener(mCallback14);
            this.btnLoginLine.setOnClickListener(mCallback16);
            this.mboundView11.setOnClickListener(mCallback17);
            this.mboundView5.setOnClickListener(mCallback11);
            this.mboundView7.setOnClickListener(mCallback13);
            this.mboundView9.setOnClickListener(mCallback15);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            this.btnLoginKakao.setVisibility(com.iindicar.indicar.utils.BindingUtil.setVisibility(activityIsKoreanGet));
            this.btnLoginLine.setVisibility(com.iindicar.indicar.utils.BindingUtil.setVisibility(ActivityIsKorean1));
        }
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 11
            if(getBuildSdkInt() >= 11) {

                this.mboundView1.setAlpha(activityCurrentPageInt0FloatFloat10FloatFloat05);
                this.mboundView2.setAlpha(activityCurrentPageInt1FloatFloat10FloatFloat05);
                this.mboundView3.setAlpha(activityCurrentPageInt2FloatFloat10FloatFloat05);
            }
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 6: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.view.main.login.LoginActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.kakaoLogin();
                }
                break;
            }
            case 7: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.view.main.login.LoginActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.lineLogin();
                }
                break;
            }
            case 4: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.view.main.login.LoginActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.fbLogin();
                }
                break;
            }
            case 5: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.view.main.login.LoginActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.kakaoLogin();
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.view.main.login.LoginActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.googleLogin();
                }
                break;
            }
            case 3: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.view.main.login.LoginActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.fbLogin();
                }
                break;
            }
            case 8: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.view.main.login.LoginActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.lineLogin();
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.view.main.login.LoginActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.googleLogin();
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): activity.isKorean
        flag 1 (0x2L): activity.currentPage
        flag 2 (0x3L): activity
        flag 3 (0x4L): null
        flag 4 (0x5L): activity.currentPage.get() == 0 ? (float) 1.0 : (float) 0.5
        flag 5 (0x6L): activity.currentPage.get() == 0 ? (float) 1.0 : (float) 0.5
        flag 6 (0x7L): activity.currentPage.get() == 1 ? (float) 1.0 : (float) 0.5
        flag 7 (0x8L): activity.currentPage.get() == 1 ? (float) 1.0 : (float) 0.5
        flag 8 (0x9L): activity.currentPage.get() == 2 ? (float) 1.0 : (float) 0.5
        flag 9 (0xaL): activity.currentPage.get() == 2 ? (float) 1.0 : (float) 0.5
    flag mapping end*/
    //end
}