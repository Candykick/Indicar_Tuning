package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityJoinBindingImpl extends ActivityJoinBinding implements com.iindicar.indicar.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(13);
        sIncludes.setIncludes(1, 
            new String[] {"layout_text_toolbar"},
            new int[] {5},
            new int[] {R.layout.layout_text_toolbar});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.joinetName, 6);
        sViewsWithIds.put(R.id.jiN_AlertSpinner, 7);
        sViewsWithIds.put(R.id.email_panel, 8);
        sViewsWithIds.put(R.id.jiN_AlertEmail1, 9);
        sViewsWithIds.put(R.id.tvab, 10);
        sViewsWithIds.put(R.id.jiN_AlertEmail2, 11);
        sViewsWithIds.put(R.id.loadingjoin, 12);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    @NonNull
    private final android.support.design.widget.AppBarLayout mboundView1;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    @Nullable
    private final android.view.View.OnClickListener mCallback3;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityJoinBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private ActivityJoinBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.EditText) bindings[9]
            , (android.widget.EditText) bindings[11]
            , (android.widget.Spinner) bindings[7]
            , (android.widget.TextView) bindings[2]
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.TextView) bindings[3]
            , (android.widget.EditText) bindings[6]
            , (android.widget.LinearLayout) bindings[12]
            , (com.iindicar.indicar.databinding.LayoutTextToolbarBinding) bindings[5]
            , (android.widget.TextView) bindings[10]
            );
        this.joinbtnEmailAuth.setTag(null);
        this.joinbtnJoin.setTag(null);
        this.joinbtnResendEmail.setTag(null);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.support.design.widget.AppBarLayout) bindings[1];
        this.mboundView1.setTag(null);
        setRootTag(root);
        // listeners
        mCallback2 = new com.iindicar.indicar.generated.callback.OnClickListener(this, 2);
        mCallback1 = new com.iindicar.indicar.generated.callback.OnClickListener(this, 1);
        mCallback3 = new com.iindicar.indicar.generated.callback.OnClickListener(this, 3);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        toolbar.invalidateAll();
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
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.activity == variableId) {
            setActivity((com.iindicar.indicar.view.main.join.JoinActivity) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setActivity(@Nullable com.iindicar.indicar.view.main.join.JoinActivity Activity) {
        this.mActivity = Activity;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.activity);
        super.requestRebind();
    }

    @Override
    public void setLifecycleOwner(@Nullable android.arch.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        toolbar.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeToolbar((com.iindicar.indicar.databinding.LayoutTextToolbarBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeToolbar(com.iindicar.indicar.databinding.LayoutTextToolbarBinding Toolbar, int fieldId) {
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
        com.iindicar.indicar.view.main.join.JoinActivity activity = mActivity;
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.joinbtnEmailAuth.setOnClickListener(mCallback1);
            this.joinbtnJoin.setOnClickListener(mCallback3);
            this.joinbtnResendEmail.setOnClickListener(mCallback2);
        }
        executeBindingsOn(toolbar);
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 2: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.view.main.join.JoinActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.emailCheckAndAuth();
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.view.main.join.JoinActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.emailCheckAndAuth();
                }
                break;
            }
            case 3: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.view.main.join.JoinActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.join();
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): toolbar
        flag 1 (0x2L): activity
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}