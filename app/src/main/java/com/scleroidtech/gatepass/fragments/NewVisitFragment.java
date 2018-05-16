package com.scleroidtech.gatepass.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.scleroidtech.gatepass.R;
import com.scleroidtech.gatepass.base.BaseFragment;
import com.scleroidtech.gatepass.base.BaseViewModel;
import com.scleroidtech.gatepass.fragments.dialogs.DatePickerDialogFragment;
import com.scleroidtech.gatepass.fragments.dialogs.TimePickerDialogFragment;
import com.scleroidtech.gatepass.utils.ui.DateUtils;
import com.scleroidtech.gatepass.utils.ui.ToastUtils;

import java.util.Date;

import javax.inject.Inject;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;
import hugo.weaving.DebugLog;

import static com.basgeekball.awesomevalidation.ValidationStyle.TEXT_INPUT_LAYOUT;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewVisitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewVisitFragment extends BaseFragment {

    private static final int REQUEST_DATE = 1;
    private static final int REQUEST_TIME = 2;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Inject
    DateUtils dateUtils;
    @Inject
    ToastUtils toastUtils;
    //Initializing String array adapter for the spinner
    ArrayAdapter<String> spinnerArrayAdapter;
    @BindView(R.id.name_edit_text)
    EditText nameEditText;
    @BindView(R.id.name_text_input_layout)
    TextInputLayout nameTextInputLayout;
    @BindView(R.id.mobile_text_input_layout)
    TextInputLayout mobileTextInputLayout;
    @BindView(R.id.address_edit_text)
    EditText addressEditText;
    @BindView(R.id.address_text_input_layout)
    TextInputLayout addressTextInputLayout;
    @BindView(R.id.company_edit_text)
    EditText companyEditText;
    @BindView(R.id.company_text_input_layout)
    TextInputLayout companyTextInputLayout;
    @BindView(R.id.time_date_text_view)
    TextView timeDateTextView;
    @BindView(R.id.entry_time_text_input_layout)
    TextInputLayout entryTimeTextInputLayout;
    @BindView(R.id.mode_of_entry_spinner)
    Spinner modeOfEntrySpinner;
    @BindView(R.id.mode_of_entry_text_input_layout)
    TextInputLayout modeOfEntryTextInputLayout;
    @BindView(R.id.vehicle_number_plate_edit_text)
    EditText vehicleNumberPlateEditText;
    @BindView(R.id.vehicle_number_plate_text_input_layout)
    TextInputLayout vehicleNumberPlateTextInputLayout;
    @BindView(R.id.camera_profile_button)
    Button cameraProfileButton;
    @BindView(R.id.camera_proof_id)
    Button cameraProofId;
    @BindView(R.id.person_to_visit_edit_text)
    EditText personToVisitEditText;
    @BindView(R.id.person_to_visit_text_input_layout)
    TextInputLayout personToVisitTextInputLayout;
    @BindView(R.id.purpose_for_visit_edit_text)
    EditText purposeForVisitEditText;
    @BindView(R.id.purpose_for_visit_text_input_layout)
    TextInputLayout purposeForVisitTextInputLayout;
    @BindView(R.id.register_button)
    Button registerButton;
    @BindView(R.id.email_login_form)
    LinearLayout emailLoginForm;
    @BindView(R.id.register_form)
    ScrollView registerForm;

    @BindArray(R.array.mode_of_travel_names)
    String[] travelMode;
    @BindView(R.id.mobile_edit_text)
    EditText mobileEditText;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;


    private Unbinder unbinder;
    private AwesomeValidation mAwesomeValidation;

    public NewVisitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewVisitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewVisitFragment newInstance(String param1, String param2) {
        NewVisitFragment fragment = new NewVisitFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @OnItemSelected(R.id.mode_of_entry_spinner)
    public void onModeOfEntrySpinnerClicked(int position) {
    }

    @OnClick(R.id.camera_profile_button)
    public void onCameraProfileButtonClicked() {
    }

    @OnClick(R.id.camera_proof_id)
    public void onCameraProofIdClicked() {
    }

    @OnClick(R.id.register_button)
    public void onRegisterButtonClicked() {
        boolean noSubmit = false;
        if (mAwesomeValidation.validate()) {

            if (setErrorEnabledTextInputLayout(addressEditText, addressTextInputLayout) || setErrorEnabledTextInputLayout(purposeForVisitEditText, purposeForVisitTextInputLayout)) {
                toastUtils.errorToast(this.getContext(), "Some fields are missing");
                return;
            }
            if (modeOfEntrySpinner.getSelectedItemPosition() == 0) {
                setErrorEnabledTextInputLayout(true, modeOfEntryTextInputLayout);
            } else {
                setErrorEnabledTextInputLayout(false, modeOfEntryTextInputLayout);
            }


        }

    }

    /**
     * It enables the Error capability of the text input layout
     *
     * @param editText        the editText to be checked
     * @param textInputLayout the layout to be set the error upon
     * @return
     */
    private boolean setErrorEnabledTextInputLayout(EditText editText, TextInputLayout textInputLayout) {
        boolean noSubmit;
        if (isEmpty(editText)) {
            noSubmit = setEmptyError(textInputLayout);
            textInputLayout.setErrorEnabled(true);
            return noSubmit;
        } else
            textInputLayout.setErrorEnabled(false);
        return false;
    }

    /**
     * If the error is empty, it should print it
     *
     * @param textInputLayout the layout which displays the relevant error
     * @return true that the data should not be submitted
     */
    private boolean setEmptyError(TextInputLayout textInputLayout) {
        textInputLayout.setError(String.valueOf(R.string.null_error));
        return true;
    }

    private boolean isEmpty(TextView text) {
        return TextUtils.isEmpty(text.getText());
    }

    /**
     * It enables the Error capability of the text input layout
     *
     * @param flag            true if not selected, false otherwise
     * @param textInputLayout the layout to be set the error upon
     * @return
     */
    private void setErrorEnabledTextInputLayout(boolean flag, TextInputLayout textInputLayout) {
        if (flag) {
            textInputLayout.setError(String.valueOf(R.string.no_vehicle_selected));
            textInputLayout.setErrorEnabled(true);
        } else
            textInputLayout.setErrorEnabled(false);
    }

    /**
     * Prints error toast
     *
     * @param context context of the current view
     * @param msg     the error message to be printed
     */
    private void errorToast(Context context, String msg) {
        toastUtils.errorToast(context, msg);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) intent.getSerializableExtra(DatePickerDialogFragment.EXTRA_DATE);
            dateUtils.setDate(date);
        } else if ((requestCode == REQUEST_TIME)) {
            Date date = (Date) intent.getSerializableExtra(TimePickerDialogFragment.EXTRA_TIME);
            dateUtils.setTime(date);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        View view = getRootView();
        initializeSpinner();
        initializeValidation();
        return view;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_new_visit;
    }

    @DebugLog
    private void initializeValidation() {
        mAwesomeValidation = new AwesomeValidation(TEXT_INPUT_LAYOUT);

        mAwesomeValidation.addValidation(nameTextInputLayout, "^[\\p{L} .'-]+$", String.valueOf(R.string.nameerror));
        mAwesomeValidation.addValidation(personToVisitTextInputLayout, "^[\\p{L} .'-]+$", String.valueOf(R.string.nameerror));
        mAwesomeValidation.addValidation(mobileTextInputLayout, "^[789]\\d{9}$", String.valueOf(R.string.mobileerror));


    }

    /**
     * Initializing the {@link Spinner}
     */
    private void initializeSpinner() {

        spinnerArrayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, travelMode) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View dropDownView = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) dropDownView;
                if (position == 0) textView.setTextColor(Color.GRAY);
                return dropDownView;
            }

            @Override
            public boolean isEnabled(int position) {
                return position != 0;

            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        //TODO set Default Spinner selection to 0

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    protected void subscribeToLiveData() {

    }

    @Override
    public BaseViewModel getViewModel() {
        return null;
    }

    private void setEntryTime() {
        if (dateUtils.getDate() == null || dateUtils.getTime() == null) {
            toastUtils.errorToast(this.getContext(), String.valueOf(R.string.dateError));
            return;
        }
        timeDateTextView.setText(dateUtils.getFormattedDate(dateUtils.combineDateAndTime()));


    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
