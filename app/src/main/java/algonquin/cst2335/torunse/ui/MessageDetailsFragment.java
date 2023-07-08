package algonquin.cst2335.torunse.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import algonquin.cst2335.torunse.databinding.DetailsLayoutBinding;

public class MessageDetailsFragment extends Fragment {
    ChatMessage selected;

    public MessageDetailsFragment (ChatMessage m) {
        selected = m;
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
            AlertDialog.Builder builder = new AlertDialog.Builder( ChatRoom.this);

            builder.setMessage("Do you want to delete the message: " + binding.messageText.getText())
                    .setTitle("Question: ")
                    .setNegativeButton("No", (dialog, cl) -> { })
                    .setPositiveButton( "Yes", (dialog, cl) -> {

                        ChatMessage removedMessage = messages.get(position);
                        messages.remove(position);
                        myAdapter.notifyItemRemoved (position);

                        Snackbar.make(messageText, "You deleted message #"+ position, Snackbar.LENGTH_LONG)
                                .setAction( "Undo", clicked -> {
                                    messages.add(position, removedMessage);
                                    myAdapter.notifyItemInserted (position);
                                })
                                .show();
                    })
                    .create().show();













        });

        return binding.getRoot();
    }


}
