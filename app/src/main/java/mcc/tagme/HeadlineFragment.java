package mcc.tagme;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by suryanarayana on 2/12/17.
 */

public class HeadlineFragment extends ListFragment {
    OnHeadlineSelectedListener mCallback;
    int positionid;
    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }




    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify the parent activity of selected item
        positionid = position;
        mCallback.onArticleSelected(position);

        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layout = android.R.layout.simple_list_item_activated_1;
        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Ipsum.Headlines));
    }

    @Override
    public void onStart() {
        super.onStart();

//        if (getFragmentManager().findFragmentById(R.id.news_fragment) != null) {
//            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        }
    }

}
