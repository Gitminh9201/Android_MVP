package com.example.duclh.mvp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duclh.mvp.data.model.Task;
import com.example.duclh.mvp.data.source.local.TaskLocalDataSource;
import com.example.duclh.mvp.data.source.TaskRepository;
import com.example.duclh.mvp.data.source.remote.TaskRemoteDataSource;
import com.example.duclh.mvp.databinding.ActivityMainBinding;
import com.example.duclh.mvp.databinding.ItemDialogBinding;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View{
    private MainContract.Presenter mPresenter;
    private RecyclerView mRecyclerTask;
    private TaskAdapter mTaskAdapter;
    private List<Task> mTaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMain(this);
        mPresenter = new MainPresenter(new TaskRepository(new TaskLocalDataSource(this),
                new TaskRemoteDataSource()), this);
        mTaskList = new ArrayList<>();
        mRecyclerTask = (RecyclerView) findViewById(R.id.recycler_view);
        mTaskAdapter = new TaskAdapter(mTaskList, mPresenter, mRecyclerTask);
        mRecyclerTask.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerTask.setAdapter(mTaskAdapter);
        mPresenter.getTasks();

    }

    @Override
    public void onAddSuccess(List<Task> taskList) {
        mTaskList = taskList;
        mTaskAdapter.replaceData(taskList);
    }

    @Override
    public void onAddFail() {
        Toast.makeText(this, "add fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteSuccess(List<Task> taskList) {
        mTaskList = taskList;
        mTaskAdapter.replaceData(taskList);
    }

    @Override
    public void onDeleteFail() {
        Toast.makeText(this, "delete fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinishSuccess(List<Task> taskList) {
        mTaskList = taskList;
        mTaskAdapter.replaceData(taskList);
    }

    @Override
    public void onFinishFail() {
        Toast.makeText(this, "finish fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateSuccess(List<Task> taskList) {
        mTaskList = taskList;
        mTaskAdapter.replaceData(taskList);
    }

    @Override
    public void onUpdateFail() {
        Toast.makeText(this, "can update", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListExist(List<Task> taskList) {
        mTaskList = taskList;
        mTaskAdapter.replaceData(taskList);
    }

    @Override
    public void onListEmpty() {
    }

    @Override
    public void onGetSuccess() {
    }

    @Override
    public void onGetFail() {
    }

    public void onClick(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        final ItemDialogBinding dialogBinding = DataBindingUtil.inflate(inflater, R.layout.item_dialog, null, false);
        dialogBinding.setTask(new Task());
        builder.setView(dialogBinding.getRoot())
                .setTitle("Add Task")
                .setPositiveButton(R.string.action_add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter.addTask(mTaskList, dialogBinding.getTask().getName(),
                                dialogBinding.getTask().getMessage());
                    }
                }).setNegativeButton(R.string.action_cancel, null);
        builder.create().show();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }
}