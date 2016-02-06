package expensemanger.yashodhandivakaran.com.expensemanager.adapter;

import android.app.DialogFragment;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import expensemanger.yashodhandivakaran.com.expensemanager.R;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.Category;

/**
 * Created by webyog on 06/02/16.
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private List<Category> categories;
    private Context mContext;
    private CategorySelectionCallback mListener;
    private DialogFragment dialogFragment;

    public interface CategorySelectionCallback {
        void setSelectedCategory(Category category);
    }

    public void setUpListener(CategorySelectionCallback listener){
        mListener = listener;
    }

    public CategoryListAdapter(List<Category> categories, Context mContext,DialogFragment dialogFragment) {
        this.categories = categories;
        this.mContext = mContext;
        this.dialogFragment = dialogFragment;
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView)itemView;
            text.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //TODO: close dialog and pass category object
            mListener.setSelectedCategory(categories.get(getAdapterPosition()));
            dialogFragment.dismiss();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_row,parent,false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.text.setText(category.getSummary());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
