package com.example.duclh.mvp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;


import com.example.duclh.mvp.data.model.Task;
import com.example.duclh.mvp.databinding.ItemDialogBinding;
import com.example.duclh.mvp.databinding.ItemTaskBinding;

import java.util.List;


/**
 * Created by ducpm on 10/07/17.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> mTaskList;
    private MainContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private View mView;

    public TaskAdapter(List<Task> taskList, MainContract.Presenter presenter,
                       RecyclerView recyclerView) {
        mPresenter = presenter;
        mTaskList = taskList;
        mRecyclerView = recyclerView;
    }

    public void setTaskList(List<Task> taskList) {
        mTaskList = taskList;
    }

    public void replaceData(List<Task> newData) {
        setTaskList(newData);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemTaskBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_task, parent, false);
        binding.setAdapter(this);
        mView = binding.getRoot();
        return new ViewHolder(binding);


    }

    public void onClickView() {
        final int position = mRecyclerView.getChildAdapterPosition(mView);
        AlertDialog.Builder builder = new AlertDialog.Builder(mView.getContext());
        LayoutInflater inflater = LayoutInflater.from(mView.getContext());
        final ItemDialogBinding dialogBinding = DataBindingUtil.inflate(inflater, R.layout.item_dialog, null, false);
        dialogBinding.setTask(mTaskList.get(position));

        builder.setView(dialogBinding.getRoot())
                .setTitle("Update Task")
                .setPositiveButton("UPDATE", new DialogInterface.OnClickListener
                        () {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter
                                .updateTask(mTaskList, mTaskList.get(position).getId(),
                                        dialogBinding.getTask().getName(), dialogBinding.getTask().getMessage());
                    }
                }).setNegativeButton(R.string.action_cancel, null);
        builder.create().show();

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mTaskList.get(position));
    }


    @Override
    public int getItemCount() {
        return mTaskList.isEmpty() ? 0 : mTaskList.size();
    }

    /**
     * ViewHolder
     */


    public class ViewHolder extends RecyclerView.ViewHolder {


        private ItemTaskBinding viewDataBinding;

        public ViewHolder(ItemTaskBinding binding) {
            super(binding.getRoot());
            viewDataBinding = binding;
        }


        public void bindData(Task task) {
            if (task != null) {
                viewDataBinding.setTask(task);
                viewDataBinding.setHolder(this);
            }
        }

        public void clickDelete() {
            mPresenter.deleteTask(mTaskList, mTaskList.get(getAdapterPosition()).getId());
        }

        public void clickCheck() {
            boolean check = viewDataBinding.checkFinish.isChecked();
            mPresenter.finishTask(mTaskList, mTaskList.get(getAdapterPosition()).getId(), check);
        }

    }
}