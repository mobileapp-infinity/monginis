/*
package com.infinity.monginis.dashboard.dao;


import android.content.Context;
import android.os.AsyncTask;

import com.infinity.monginis.dashboard.model.SaveOrder;

import java.lang.ref.WeakReference;

public  class SaveOrderTask extends AsyncTask<Void,Void,Boolean> {

        private WeakReference<Context> activityReference;
        private SaveOrder saveOrder;

        // only retain a weak reference to the activity
        SaveOrderTask(Context context, SaveOrder saveOrder) {
            activityReference = new WeakReference<>(context);
            this.saveOrder = saveOrder;
        }

        // doInBackground methods runs on a worker thread
        @Override
        protected Boolean doInBackground(Void... objs) {
            activityReference.get()..getNoteDao().insertNote(note);
            return true;
        }

        // onPostExecute runs on main thread
        @Override
        protected void onPostExecute(Boolean bool) {
            if (bool){
                activityReference.get().setResult(note,1);
            }
        }

    }

*/
