package board.evappdev.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avram on 12.08.2017.
 * Этот адаптер предназначен для формироввания списка спиннеров
 */

public class SimpleBaseAdapter extends BaseAdapter {

    private Context ctx;
    private LayoutInflater lInflater;
    private List<String> listStrings;
    private int setTextSize = 22;

    public SimpleBaseAdapter(Context context) {
        ctx = context;
        this.listStrings = new ArrayList<>();
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getSetTextSize() {
        return setTextSize;
    }

    public void setSetTextSize(int setTextSize) {
        this.setTextSize = setTextSize;
    }

    /**
     * Получить количество элементов
     *
     * @return  количество элементов
     */
    @Override
    public int getCount() {
        return listStrings.size();
    }

    /**
     * Получить элемент по позиции
     *
     * @return  элемент по позиции
     */
    @Override
    public Object getItem(int position) {
        return listStrings.get(position);
    }

    /**
     * Получить ID по позиции
     *
     * @return  ID по позиции
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Получить вид пункта списка
     *
     * @return  вид пункта списка
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view

        if (convertView == null) {
            convertView = lInflater.inflate(R.layout.list_item_simple, parent, false);
        }
        TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
        txtName.setTextSize(getSetTextSize());
        txtName.setText(listStrings.get(position));
        return convertView;
    }

    public List<String> getListStrings() {
        return listStrings;
    }

    public void setList(List<String> strList) {
        listStrings = strList;
        notifyDataSetChanged();

    }
    public void add(String str) {
        listStrings.add(str);
        notifyDataSetChanged();
    }


}