package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class HolderLanguageSettingItemBindingImpl extends HolderLanguageSettingItemBinding  {

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
    private final android.widget.TextView mboundView1;
    @NonNull
    private final android.widget.ImageButton mboundView2;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public HolderLanguageSettingItemBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private HolderLanguageSettingItemBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.ImageButton) bindings[2];
        this.mboundView2.setTag(null);
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
            setItem((com.iindicar.indicar.view.account.profile.LanguageSettingActivity.LanguageSettingItem) variable);
        }
        else if (BR.language == variableId) {
            setLanguage((android.databinding.ObservableField) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.iindicar.indicar.view.account.profile.LanguageSettingActivity.LanguageSettingItem Item) {
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }
    public void setLanguage(@Nullable android.databinding.ObservableField Language) {
        updateRegistration(0, Language);
        this.mLanguage = Language;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.language);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeLanguage((android.databinding.ObservableField) object, fieldId);
        }
        return false;
    }
    private boolean onChangeLanguage(android.databinding.ObservableField Language, int fieldId) {
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
        com.iindicar.indicar.view.account.profile.LanguageSettingActivity.LanguageSettingItem item = mItem;
        java.lang.String itemLanguage = null;
        java.lang.String itemLanguageString = null;
        android.databinding.ObservableField language = mLanguage;
        java.lang.Object languageGet = null;
        boolean itemLanguageEqualsLanguage = false;

        if ((dirtyFlags & 0x7L) != 0) {



                if (item != null) {
                    // read item.language
                    itemLanguage = item.getLanguage();
                }
                if (language != null) {
                    // read language.get()
                    languageGet = language.get();
                }


                if (itemLanguage != null) {
                    // read item.language.equals(language.get())
                    itemLanguageEqualsLanguage = itemLanguage.equals(languageGet);
                }
            if ((dirtyFlags & 0x6L) != 0) {

                    if (item != null) {
                        // read item.languageString
                        itemLanguageString = item.getLanguageString();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, itemLanguageString);
        }
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            this.mboundView2.setVisibility(com.iindicar.indicar.utils.BindingUtil.setVisibility(itemLanguageEqualsLanguage));
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): language
        flag 1 (0x2L): item
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}