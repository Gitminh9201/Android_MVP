package com.example.duclh.mvp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.duclh.mvp.data.model.Task;
import com.example.duclh.mvp.databinding.ItemTaskBinding;

import java.util.List;


/**
 * Created by ducpm on 10/07/17.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> mTaskList;
    private MainContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;

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
        return new ViewHolder(binding);


    }

  public void onClickView(View view) {
      Log.e("====>","dcmm");
        final int position = mRecyclerView.getChildAdapterPosition(view);
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        View dialogView =
                LayoutInflater.from(view.getContext()).inflate(R.layout.item_dialog, null);
        final EditText editTitle = (EditText) dialogView.findViewById(R.id.edit_title);
        final EditText editMessage = (EditText) dialogView.findViewById(R.id.edit_message);
        editTitle.setText(mTaskList.get(position).getName());
        editMessage.setText(mTaskList.get(position).getMessage());
        builder.setView(dialogView)
                .setTitle("Update Task")
                .setPositiveButton("UPDATE", new DialogInterface.OnClickListener
                        () {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter
                                .updateTask(mTaskList, mTaskList.get(position).getId(), editTitle
                                        .getText()
                                        .toString(), editMessage.getText().toString());
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

    /** ViewHolder
     *
     *
     */


    public class ViewHolder extends RecyclerView.ViewHolder {


        private  ItemTaskBinding viewDataBinding;

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
        public void clickDelete(){
               mPresenter.deleteTask(mTaskList, mTaskList.get(getAdapterPosition()).getId());
        }
        public void clickCheck(){
            Log.e("clickCheck: ",viewDataBinding.checkFinish.isChecked()+"" );
            boolean check = viewDataBinding.checkFinish.isChecked();
            mPresenter.finishTask(mTaskList, mTaskList.get(getAdapterPosition()).getId(), check);
        }

    }
}