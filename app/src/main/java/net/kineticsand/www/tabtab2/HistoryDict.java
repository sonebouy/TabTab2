package net.kineticsand.www.tabtab2;

import android.content.Context;

import java.util.List;

/**
 * Created by Nut on 30/3/2558.
 */
public class HistoryDict {
    DatabaseHistoryHandler db;

    Context context;
    private static HistoryDict ourInstance = new HistoryDict();
    public static HistoryDict getInstance() {
        return ourInstance;
    }

    public void init(Context context){
        db = new DatabaseHistoryHandler(context);
        this.context = context;
    }

    void addHistory(History newHistory)
    {
        db.addHistory(newHistory);
    }

    public List<History> getHistories(String insert_date)
    {
        return db.getHistories(insert_date);
    }

    public void clearHistories()
    {
        db.clearHistorys();
    }

}
