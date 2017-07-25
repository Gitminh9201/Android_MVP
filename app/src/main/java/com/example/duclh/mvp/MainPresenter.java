package com.example.duclh.mvp;


import com.example.duclh.mvp.data.model.Task;
import com.example.duclh.mvp.data.source.TaskDataSource;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private TaskDataSource taskDataSource;  // connect to model
    private MainContract.View mView;    // thong bao cho view de hien thi


    public MainPresenter(TaskDataSource taskRepository,
                         MainContract.View view) {
        taskDataSource = taskRepository;
        mView = view;
    }

    @Override
    public void addTask(final List<Task> taskList, String taskName, String taskMsg) {
        final Task task = new Task(taskName, taskMsg);
        taskDataSource.addTask(task, new TaskDataSource.Callback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                task.setId(data);
                taskList.add(task);
                mView.onAddSuccess(taskList);
            }

            @Override
            public void onFail() {
                mView.onAddFail();
            }
        });
    }

    @Override
    public void deleteTask(final List<Task> taskList, final int id) {
        taskDataSource.deleteTask(id, new TaskDataSource.Callback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                Task temp = null;
                for (Task t : taskList) {
                    if (t.getId() == id) {
                        temp = t;
                    }
                }
                if (temp != null) {
                    taskList.remove(temp);
                }
                mView.onDeleteSuccess(taskList);
            }

            @Override
            public void onFail() {
                mView.onDeleteFail();
            }
        });
    }

    @Override
    public void finishTask(final List<Task> taskList, final int id, final boolean isFinish) {
        taskDataSource.finishTask(id, isFinish, new TaskDataSource.Callback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                for (Task t : taskList) {
                    if (t.getId() == id) {
                        t.setFinish(isFinish);
                    }
                }
                mView.onFinishSuccess(taskList);
            }

            @Override
            public void onFail() {
                mView.onFinishFail();
            }
        });
    }

    @Override
    public void updateTask(final List<Task> taskList, final int id, final String taskName,
                           final String taskMsg) {
        taskDataSource.updateTask(id, taskName, taskMsg, new TaskDataSource.Callback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                for (Task t : taskList) {
                    if (t.getId() == id) {
                        t.setMessage(taskMsg);
                        t.setName(taskName);
                    }
                }
                mView.onUpdateSuccess(taskList);
            }

            @Override
            public void onFail() {
                mView.onUpdateFail();
            }
        });
    }

    @Override
    public void getTask(int id) {
        taskDataSource.getTaskById(id, new TaskDataSource.Callback<Task>() {
            @Override
            public void onSuccess(Task data) {
                // TODO: 7/8/2017
                mView.onGetSuccess();
            }

            @Override
            public void onFail() {
                mView.onGetFail();
            }
        });
    }

    @Override
    public void getTasks() {
        taskDataSource.getTasks(new TaskDataSource.Callbacks<Task>() {
            @Override
            public void onExistedList(List<Task> data) {
                mView.onListExist(data);
            }

            @Override
            public void onEmptyList() {
                mView.onListEmpty();
            }
        });
    }

    @Override
    public void detachView() {
        this.mView = null;
    }
}