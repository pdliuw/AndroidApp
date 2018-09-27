package com.air.permission.rationale;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.air.permission.R;

import java.io.Serializable;

/**
 * @author pd_liu 2018/9/18
 * <p>
 *
 * </p>
 */
public class AbstractRationaleFragment extends AppCompatDialogFragment {

    private static final String KEY_RATIONALE_TITLE = "rationale_title";
    private static final String KEY_RATIONALE_CONTENT = "rationale_content";
    private static final String KEY_RATIONALE = "rationale";

    /**
     * The view that permission's rationale.
     */
    private TextView mRationaleConfirmTv;
    private TextView mRationaleCancelTv;

    private DefaultRationale mDefaultRationale;

    private View.OnClickListener mRationaleConfirmListener;
    private View.OnClickListener mRationaleCancelListener;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (mRationaleConfirmTv == v) {
                if (mRationaleConfirmListener != null) {
                    mRationaleConfirmListener.onClick(v);
                }
            } else if (mRationaleCancelTv == v) {
                if (mRationaleCancelListener != null) {
                    mRationaleCancelListener.onClick(v);
                }
            }
        }
    };

    /**
     * Create a instance with the parameter {@link DefaultRationale}
     *
     * @param rationale
     *         permission's rational.
     *
     * @return rationale's instance.
     */
    public static AbstractRationaleFragment newInstance(DefaultRationale rationale) {

        Bundle args = new Bundle();
        args.putString(KEY_RATIONALE_TITLE, rationale.getTitle());
        args.putString(KEY_RATIONALE_CONTENT, rationale.getContent());
        args.putSerializable(KEY_RATIONALE, rationale);

        AbstractRationaleFragment fragment = new AbstractRationaleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Create a instance.
     * <p>
     * {@link #newInstance(DefaultRationale)} instance of this method.
     */
    public AbstractRationaleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Bundle bundle = getArguments();

        if (bundle != null) {
            Serializable serializable = bundle.getSerializable(KEY_RATIONALE);

            if (serializable != null && (serializable instanceof DefaultRationale)) {

                mDefaultRationale = (DefaultRationale) serializable;
            }
        }


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.abstract_rationale_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView titleTv = view.findViewById(R.id.permission_rationale_title_tv);
        TextView contentTv = view.findViewById(R.id.permission_rationale_tip_content);


        mRationaleConfirmTv = view.findViewById(R.id.permission_rationale_confirm_tv);
        mRationaleCancelTv = view.findViewById(R.id.permission_rationale_cancel_tv);

        setCommonClickListener(mRationaleConfirmTv);
        setCommonClickListener(mRationaleCancelTv);


        if (mDefaultRationale != null) {
            /*
            Update ui.
             */
            titleTv.setText(mDefaultRationale.getTitle());
            contentTv.setText(mDefaultRationale.getContent());
            mRationaleConfirmTv.setText(mDefaultRationale.getConfirm());
            mRationaleCancelTv.setText(mDefaultRationale.getCancel());
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*
        Set touchOutside is false.
         */
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
    }

    public void setConfirmListener(@Nullable View.OnClickListener onClickListener) {
        this.mRationaleConfirmListener = onClickListener;
    }

    public void setCancelListener(@Nullable View.OnClickListener onClickListener) {
        this.mRationaleCancelListener = onClickListener;
    }

    private void setCommonClickListener(@Nullable View view) {
        if (view == null) {
            return;
        }

        view.setOnClickListener(onClickListener);
    }
}
