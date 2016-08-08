package com.tutu.pestcs.fragment.progress;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TableRow;

import com.tutu.pestcs.R;

/**
 * Created by 47066 on 2016/8/8.
 */
public class TableRowFactory {
    public static TableRow create(Context context) {
        return (TableRow) LayoutInflater.from(context).inflate(R.layout.table_row, null);
    }
}
