package se.k3.antonochisak.silverscreen.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import se.k3.antonochisak.silverscreen.R;

/**
 * Created by isak on 2015-04-10.
 */
public class SimpleBaseAdapter extends BaseAdapter {

    ArrayList<String> mList;
    LayoutInflater mLayoutInflater;

    public SimpleBaseAdapter(LayoutInflater layoutInflater, ArrayList<String> list) {
        this.mList = list;
        this.mLayoutInflater = layoutInflater;

    }

    class ViewHolder {
        @InjectView(R.id.item_list_text_view)
        TextView itemTextView;
        public ViewHolder(View base) {
            ButterKnife.inject(this, base);
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(convertView == null) {
            v = mLayoutInflater.inflate(R.layout.drawer_list_item, parent, false);
            holder = new ViewHolder(v);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.itemTextView.setText(mList.get(position));
        return v;
    }
}
