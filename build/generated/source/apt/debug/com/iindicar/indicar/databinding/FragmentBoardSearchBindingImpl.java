package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentBoardSearchBindingImpl extends FragmentBoardSearchBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.btn_filter, 5);
        sViewsWithIds.put(R.id.et_search, 6);
        sViewsWithIds.put(R.id.btn_submit, 7);
        sViewsWithIds.put(R.id.recycler_board_list, 8);
        sViewsWithIds.put(R.id.filter_panel, 9);
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
    private OnClickListenerImpl mPresenterOnBoardFilterClickedAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public FragmentBoardSearchBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private FragmentBoardSearchBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.ImageButton) bindings[5]
            , (android.widget.Button) bindings[1]
            , (android.widget.Button) bindings[7]
            , (android.widget.EditText) bindings[6]
            , (android.widget.LinearLayout) bindings[9]
            , (android.support.v7.widget.RecyclerView) bindings[8]
            );
        this.btnFilterAll.setTag(this.btnFilterAll.getResources().getString(com.iindicar.indicar.R.string.all));
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView2 = (android.widget.Button) bindings[2];
        this.mboundView2.setTag(this.mboundView2.getResources().getString(com.iindicar.indicar.R.string.daylife));
        this.mboundView3 = (android.widget.Button) bindings[3];
        this.mboundView3.setTag(this.mboundView3.getResources().getString(com.iindicar.indicar.R.string.market));
        this.mboundView4 = (android.widget.Button) bindings[4];
        this.mboundView4.setTag(this.mboundView4.getResources().getString(com.iindicar.indicar.R.string.diy));
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
        if (BR.presenter == variableId) {
            setPresenter((com.iindicar.indicar.view.community.boardList.BoardSearchPresenter) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setPresenter(@Nullable com.iindicar.indicar.view.community.boardList.BoardSearchPresenter Presenter) {
        this.mPresenter = Presenter;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.presenter);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangePresenterBoardType((android.databinding.ObservableField<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangePresenterBoardType(android.databinding.ObservableField<java.lang.String> PresenterBoardType, int fieldId) {
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
        boolean btnFilterAllAndroidStringAllPresenterBoardType = false;
        com.iindicar.indicar.view.community.boardList.BoardSearchPresenter presenter = mPresenter;
        android.view.View.OnClickListener presenterOnBoardFilterClickedAndroidViewViewOnClickListener = null;
        boolean mboundView4AndroidStringDiyPresenterBoardType = false;
        boolean mboundView3AndroidStringMarketPresenterBoardType = false;
        android.databinding.ObservableField<java.lang.String> presenterBoardType = null;
        java.lang.String presenterBoardTypeGet = null;
        boolean mboundView2AndroidStringDaylifePresenterBoardType = false;

        if ((dirtyFlags & 0x7L) != 0) {


            if ((dirtyFlags & 0x6L) != 0) {

                    if (presenter != null) {
                        // read presenter::onBoardFilterClicked
                        presenterOnBoardFilterClickedAndroidViewViewOnClickListener = (((mPresenterOnBoardFilterClickedAndroidViewViewOnClickListener == null) ? (mPresenterOnBoardFilterClickedAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mPresenterOnBoardFilterClickedAndroidViewViewOnClickListener).setValue(presenter));
                    }
            }

                if (presenter != null) {
                    // read presenter.boardType
                    presenterBoardType = presenter.boardType;
                }
                updateRegistration(0, presenterBoardType);


                if (presenterBoardType != null) {
                    // read presenter.boardType.get()
                    presenterBoardTypeGet = presenterBoardType.get();
                }


                // read @android:string/all == presenter.boardType.get()
                btnFilterAllAndroidStringAllPresenterBoardType = (btnFilterAll.getResources().getString(R.string.all)) == (presenterBoardTypeGet);
                // read @android:string/diy == presenter.boardType.get()
                mboundView4AndroidStringDiyPresenterBoardType = (mboundView4.getResources().getString(R.string.diy)) == (presenterBoardTypeGet);
                // read @android:string/market == presenter.boardType.get()
                mboundView3AndroidStringMarketPresenterBoardType = (mboundView3.getResources().getString(R.string.market)) == (presenterBoardTypeGet);
                // read @android:string/daylife == presenter.boardType.get()
                mboundView2AndroidStringDaylifePresenterBoardType = (mboundView2.getResources().getString(R.string.daylife)) == (presenterBoardTypeGet);
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            com.iindicar.indicar.utils.BindingUtil.setBoardFilter(this.btnFilterAll, btnFilterAllAndroidStringAllPresenterBoardType);
            com.iindicar.indicar.utils.BindingUtil.setBoardFilter(this.mboundView2, mboundView2AndroidStringDaylifePresenterBoardType);
            com.iindicar.indicar.utils.BindingUtil.setBoardFilter(this.mboundView3, mboundView3AndroidStringMarketPresenterBoardType);
            com.iindicar.indicar.utils.BindingUtil.setBoardFilter(this.mboundView4, mboundView4AndroidStringDiyPresenterBoardType);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            this.btnFilterAll.setOnClickListener(presenterOnBoardFilterClickedAndroidViewViewOnClickListener);
            this.mboundView2.setOnClickListener(presenterOnBoardFilterClickedAndroidViewViewOnClickListener);
            this.mboundView3.setOnClickListener(presenterOnBoardFilterClickedAndroidViewViewOnClickListener);
            this.mboundView4.setOnClickListener(presenterOnBoardFilterClickedAndroidViewViewOnClickListener);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.iindicar.indicar.view.community.boardList.BoardSearchPresenter value;
        public OnClickListenerImpl setValue(com.iindicar.indicar.view.community.boardList.BoardSearchPresenter value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onBoardFilterClicked(arg0); 
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): presenter.boardType
        flag 1 (0x2L): presenter
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}