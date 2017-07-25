package com.example.duclh.mvp;


import com.example.duclh.mvp.data.model.Task;

import java.util.List;

/**
 * Created by ducpm on 09/07/17.
 */
public interface MainContract {
/** View - Presenter **/


    /**
     * View mandatory methods. Available to Presenter
     *      Presenter -> View
     */
    interface View {
        void onAddSuccess(List<Task> taskList);
        void onAddFail();
        void onDeleteSuccess(List<Task> taskList);
        void onDeleteFail();
        void onFinishSuccess(List<Task> taskList);
        void onFinishFail();
        void onUpdateSuccess(List<Task> taskList);
        void onUpdateFail();
        void onListExist(List<Task> tasks);
        void onListEmpty();
        void onGetSuccess();
        void onGetFail();
    }
     /**
     * Operations offered from Presenter to View
     *      View -> Presenter
     */
    interface Presenter {
        void addTask(List<Task> taskList, String taskName, String taskMsg);
        void deleteTask(List<Task> taskList, int id);
        void finishTask(List<Task> taskList, int id, boolean isFinish);
        void updateTask(List<Task> taskList, int id, String taskName, String taskMsg);
        void getTask(int id);
        void getTasks();
        void detachView();
    }

}
