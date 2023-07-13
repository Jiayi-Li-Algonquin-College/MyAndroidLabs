package algonquin.cst2335.torunse.ui;
import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.torunse.R;
import algonquin.cst2335.torunse.data.ChatRoomViewModel;
import algonquin.cst2335.torunse.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.torunse.databinding.ActivityMainBinding;
import algonquin.cst2335.torunse.databinding.ReceiveMessageBinding;
import algonquin.cst2335.torunse.databinding.SentMessageBinding;

public class ChatRoom extends AppCompatActivity {



    public ActivityChatRoomBinding binding;
    public ArrayList<ChatMessage> messages = new ArrayList<>();
    public ChatRoomViewModel chatModel ;
    public RecyclerView.Adapter myAdapter;

    public int position;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item_1) {//put your ChatMessage deletion code here. If you select this item, you should show the alert dialog
            //asking if the user wants to delete this message.
            AlertDialog.Builder builder = new AlertDialog.Builder(ChatRoom.this);

            builder.setMessage("Do you want to delete the message: ")
                    .setTitle("Question: ")
                    .setNegativeButton("No", (dialog, cl) -> {
                    })
                    .setPositiveButton("Yes", (dialog, cl) -> {

                        ChatMessage removedMessage = messages.get(position);
                        messages.remove(position);
                        myAdapter.notifyItemRemoved(position);

                        Snackbar.make(messageText, "You deleted message #" + position, Snackbar.LENGTH_LONG)
                                .setAction("Undo", click -> {
                                    messages.add(position, removedMessage);
                                    myAdapter.notifyItemInserted(position);
                                })
                                .show();
                    })
                    .create().show();
        } else if (itemId == R.id.about) {
            Toast.makeText(getApplicationContext(), "STRING MESSAGE", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //这两个必须放在onCreate里😅😅😅😅😅😅😅😅😅😅😅😅😅😅😅😅😅😅
         MessageDatabase db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "database-name").build();
         ChatMessageDAO mDAO = db.cmDAO();

         chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();
        if(messages == null) {
            chatModel.messages.postValue( messages = new ArrayList<ChatMessage>());

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(() ->
            {
                messages.addAll( mDAO.getAllMessages() ); //Once you get the data from database

                runOnUiThread( () ->  binding.recycleView.setAdapter( myAdapter )); //You can then load the RecyclerView
            });


        }

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.myToolbar);



        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

        binding.sendButton.setOnClickListener(click -> {
            String message = binding.textInput.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            ChatMessage chatMessage = new ChatMessage(message, currentDateandTime, true);



            messages.add(chatMessage);
            myAdapter.notifyDataSetChanged();
            binding.textInput.setText("");
        });

        binding.receiveButton.setOnClickListener(click -> {
            String message = binding.textInput.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            ChatMessage chatMessage = new ChatMessage(message, currentDateandTime, false);



            messages.add(chatMessage);
            myAdapter.notifyDataSetChanged();
            binding.textInput.setText("");
        });

        binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                if (viewType == 0) {
                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder( binding.getRoot() );
                } else {
                    ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder( binding.getRoot() );
                }


            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                ChatMessage chatMessage = messages.get(position);
                holder.messageText.setText(chatMessage.getMessage());
                holder.timeText.setText(chatMessage.getTimeSent());
            }

            @Override
            public int getItemCount() {
                return messages.size();
            }

            @Override
            public int getItemViewType(int position) {
                ChatMessage chatMessage = messages.get(position);
                if (chatMessage.isSentButton()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        chatModel.selectedMessage.observe(this, newMessageValue -> {
            // Handle selected message change
            // Create a new fragment to show the details for the selected message
            MessageDetailsFragment chatFragment = new MessageDetailsFragment(newMessageValue);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentLocation, chatFragment)
                    .addToBackStack("")
                    .commit();
        });
    }

    public class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;
        public MyRowHolder( View itemView) {
            super(itemView);

            itemView.setOnClickListener(clk ->{

                position = getAbsoluteAdapterPosition();

                /*
                AlertDialog.Builder builder = new AlertDialog.Builder( ChatRoom.this);

                builder.setMessage("Do you want to delete the message: " + messageText.getText())
                        .setTitle("Question: ")
                        .setNegativeButton("No", (dialog, cl) -> { })
                        .setPositiveButton( "Yes", (dialog, cl) -> {

                            ChatMessage removedMessage = messages.get(position);
                            messages.remove(position);
                            myAdapter.notifyItemRemoved (position);

                            Snackbar.make(messageText, "You deleted message #"+ position, Snackbar.LENGTH_LONG)
                                    .setAction( "Undo", click -> {
                                        messages.add(position, removedMessage);
                                        myAdapter.notifyItemInserted (position);
                                    })
                                    .show();
                        })
                        .create().show();*/

                ChatMessage selected = messages.get(position);
                chatModel.selectedMessage.postValue(selected);




            });

            messageText = itemView.findViewById(R.id.messageText);
            timeText = itemView.findViewById(R.id.timeText);
        }


    }


}