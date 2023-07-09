package algonquin.cst2335.torunse.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import algonquin.cst2335.torunse.databinding.DetailsLayoutBinding;

public class MessageDetailsFragment extends Fragment {
    ChatMessage selected;
    ArrayList<ChatMessage> messages;
    public int postionTemp;
    public RecyclerView.Adapter myAdapter;


    public MessageDetailsFragment (ChatMessage m, ArrayList<ChatMessage> messages, int postionTemp, RecyclerView.Adapter myAdapter) {

        selected = m;
        this.messages = messages;
        this.postionTemp = postionTemp;
        this.myAdapter = myAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        DetailsLayoutBinding binding = DetailsLayoutBinding.inflate(inflater);

        binding.messageText.setText( selected.message );
        binding.timeText.setText(selected.timeSent);
        binding.databaseText.setText("Id = " + selected.id);

        binding.saveButton.setOnClickListener(click -> {
//            Toast.makeText(getActivity(), "This is my Toast message!",
//                    Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());

            builder.setMessage("Do you want to delete the message: " + binding.messageText.getText())
                    .setTitle("Question: ")
                    .setNegativeButton("No", (dialog, cl) -> { })
                    .setPositiveButton( "Yes", (dialog, cl) -> {

                        ChatMessage removedMessage = messages.get(postionTemp);
                        messages.remove(postionTemp);
                        myAdapter.notifyItemRemoved (postionTemp);

                        Snackbar.make(binding.messageText, "You deleted message #"+ postionTemp, Snackbar.LENGTH_LONG)
                                .setAction( "Undo", clicked -> {
                                    messages.add(postionTemp, removedMessage);
                                    myAdapter.notifyItemInserted (postionTemp);
                                })
                                .show();
                    })
                    .create().show();













        });

        return binding.getRoot();
    }


}
