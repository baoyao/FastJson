package com.example.testfastjson;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by iwan on 16/1/6.
 */
public class BookListAdapter extends BaseAdapter{

    private Context c;
    private ArrayList<Book> list;

    public BookListAdapter(Context context,ArrayList<Book> books) {
        this.c = context;
        this.list = books;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (holder==null){
            convertView=View.inflate(c, R.layout.list_item,null);
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Book b = list.get(position);
        holder.tv.setText(b.getCatalog() + "\t\t\t\t"+ b.getId());
        return convertView;
    }

    class ViewHolder{
        TextView tv;
    }
}
