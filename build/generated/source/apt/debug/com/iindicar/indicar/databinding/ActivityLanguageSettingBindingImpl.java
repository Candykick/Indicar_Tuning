package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityLanguageSettingBindingImpl extends ActivityLanguageSettingBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(4);
        sIncludes.setIncludes(0, 
            new String[] {"layout_text_toolbar"},
            new int[] {2},
            new int[] {R.layout.layout_text_toolbar});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.recycler_language, 3);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityLanguageSettingBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private ActivityLanguageSettingBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (android.widget.Button) bindings[1]
            , (android.support.v7.widget.RecyclerView) bindings[3]
            , (com.iindicar.indicar.databinding.LayoutTextToolbarBinding) bindings[2]
            );
        this.btnDone.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
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
            setActivity((com.iindicar.indicar.view.account.profile.LanguageSettingActivity) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setActivity(@Nullable com.iindicar.indicar.view.account.profile.LanguageSettingActivity Activity) {
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
        toolbar.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeActivityOriginalLanguage((android.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeActivityLanguage((android.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeToolbar((com.iindicar.indicar.databinding.LayoutTextToolbarBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeActivityOriginalLanguage(android.databinding.ObservableField<java.lang.String> ActivityOriginalLanguage, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeActivityLanguage(android.databinding.ObservableField<java.lang.String> ActivityLanguage, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeToolbar(com.iindicar.indicar.databinding.LayoutTextToolbarBinding Toolbar, int fieldId) {
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
        java.lang.String activityOriginalLanguageGet = null;
        com.iindicar.indicar.view.account.profile.LanguageSettingActivity activity = mActivity;
        android.databinding.ObservableField<java.lang.String> activityOriginalLanguage = null;
        boolean activityOriginalLanguageEqualsActivityLanguage = false;
        android.databinding.ObservableField<java.lang.String> activityLanguage = null;
        boolean ActivityOriginalLanguageEqualsActivityLanguage1 = false;
        java.lang.String activityLanguageGet = null;

        if ((dirtyFlags & 0x1bL) != 0) {



                if (activity != null) {
                    // read activity.originalLanguage
                    activityOriginalLanguage = activity.originalLanguage;
                    // read activity.language
                    activityLanguage = activity.language;
                }
                updateRegistration(0, activityOriginalLanguage);
                updateRegistration(1, activityLanguage);


                if (activityOriginalLanguage != null) {
                    // read activity.originalLanguage.get()
                    activityOriginalLanguageGet = activityOriginalLanguage.get();
                }
                if (activityLanguage != null) {
                    // read activity.language.get()
                    activityLanguageGet = activityLanguage.get();
                }


                if (activityOriginalLanguageGet != null) {
                    // read activity.originalLanguage.get().equals(activity.language.get())
                    ActivityOriginalLanguageEqualsActivityLanguage1 = activityOriginalLanguageGet.equals(activityLanguageGet);
                }


                // read !activity.originalLanguage.get().equals(activity.language.get())
                activityOriginalLanguageEqualsActivityLanguage = !ActivityOriginalLanguageEqualsActivityLanguage1;
        }
        // batch finished
        if ((dirtyFlags & 0x1bL) != 0) {
            // api target 1

            this.btnDone.setEnabled(activityOriginalLanguageEqualsActivityLanguage);
        }
        executeBindingsOn(toolbar);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): activity.originalLanguage
        flag 1 (0x2L): activity.language
        flag 2 (0x3L): toolbar
        flag 3 (0x4L): activity
        flag 4 (0x5L): null
    flag mapping end*/
    //end
}