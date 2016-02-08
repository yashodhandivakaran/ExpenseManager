package expensemanger.yashodhandivakaran.com.expensemanager.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import expensemanger.yashodhandivakaran.com.expensemanager.BillActivity;
import expensemanger.yashodhandivakaran.com.expensemanager.R;
import expensemanger.yashodhandivakaran.com.expensemanager.adapter.CategoryListAdapter;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.Category;
import expensemanger.yashodhandivakaran.com.expensemanager.helper.RecyclerViewWrapHeightLinearLayoutManager;

/**
 * Created by webyog on 06/02/16.
 */
public class CategoryPickerDialog extends DialogFragment {

    public final static String CATEGORY_LIST = "category_list";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.category_list, null);
        alertBuilder.setView(view);

        Bundle args = getArguments();
        List<Category> categoryList = args.getParcelableArrayList(CATEGORY_LIST);

        CategoryListAdapter adapter = new CategoryListAdapter(categoryList,getActivity(),this);

        RecyclerView categoryRecyclerView = (RecyclerView)view.findViewById(R.id.category_list);

        categoryRecyclerView.setLayoutManager(new RecyclerViewWrapHeightLinearLayoutManager(getActivity()));

        adapter.setUpListener((BillActivity)getActivity());

        categoryRecyclerView.setAdapter(adapter);
        AlertDialog alertDialog = alertBuilder.create();

        return  alertDialog;
    }
}
