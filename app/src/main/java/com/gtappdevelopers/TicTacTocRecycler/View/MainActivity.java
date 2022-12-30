package com.gtappdevelopers.TicTacTocRecycler.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gtappdevelopers.TicTacTocRecycler.Model.AppConstants;
import com.gtappdevelopers.TicTacTocRecycler.Presenter.IPresenter;
import com.gtappdevelopers.TicTacTocRecycler.Presenter.Presenter;
import com.gtappdevelopers.TicTacTocRecycler.R;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener ,IView {

    private static final String TAG = "MAIN Activity";
    private RecyclerView recyclerView;
    private ArrayList<RecyclerData> recyclerDataArrayList;
    RecyclerViewAdapter adapter;
    private IPresenter presenter;
    private static final String FIXED_PASSWORD = "12345678";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        
        createBoard();
        // create presenter
        presenter = new Presenter(this);





    }

    private void createBoard() {
        //created new array list..
        recyclerDataArrayList=new ArrayList<>();

        //added data to array list
        recyclerDataArrayList.add(new RecyclerData(0));
        recyclerDataArrayList.add(new RecyclerData(0));
        recyclerDataArrayList.add(new RecyclerData(0));
        recyclerDataArrayList.add(new RecyclerData(0));
        recyclerDataArrayList.add(new RecyclerData(0));
        recyclerDataArrayList.add(new RecyclerData(0));
        recyclerDataArrayList.add(new RecyclerData(0));
        recyclerDataArrayList.add(new RecyclerData(0));
        recyclerDataArrayList.add(new RecyclerData(0));

        //added data from arraylist to adapter class.
        adapter=new RecyclerViewAdapter(recyclerDataArrayList,this);
        //setting grid layout manager to implement grid view.
        // in this method '2' represents number of colums to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(this,3);
        //at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initViews() {

        EditText etMail = findViewById(R.id.editTextTextEmailAddress);
        recyclerView=findViewById(R.id.idCourseRV);
        Button startJoin =findViewById(R.id.btnStartJoin);
        startJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.startOrJoinGame();
            }
        });

        Button btnReg = findViewById(R.id.btnReg);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(etMail.getText()))
                {

                    int num = (int)(Math.random()*1000);
                    String email = etMail.getText().toString();
                    email = num+email;
                    String password = FIXED_PASSWORD;
                    presenter.userRegister(email,password);                }

            }
        });




    }

    public void changeFromCode(View view) {
      //  recyclerDataArrayList.get(0).setImgid(R.drawable.download);

     //   adapter.notifyDataSetChanged();


    }

    @Override
    public void onItemClicked(int pos) {
        Log.d(TAG, "onItemClicked: " + pos);

        presenter.userClick(pos/ AppConstants.COLS,pos%AppConstants.COLS);




    }

    @Override
    public void markButton(int i, int j, char letter) {

        int imageId = R.drawable.o;
        if(letter==AppConstants.PLAYER)
            imageId = R.drawable.x;

        int position = i*AppConstants.COLS+j;

        recyclerDataArrayList.get(position).setImgid(imageId);

        adapter.notifyDataSetChanged();

    }

    @Override
    public void displayMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void clearBoard() {
        createBoard();
    }

    @Override
    public void displayEndGame(String message) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        // display the message received from the presenter
        alertDialog.setTitle(message);
        alertDialog.setMessage("Would you like to play again?");
        alertDialog.setIcon(R.drawable.trophy1);

        // wait for user feedback
        alertDialog.setCancelable(false);

        // in case clicked YES
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.presenter.restartGame();
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
                dialog.dismiss();
            }
        });

        AlertDialog dialog= alertDialog.create();
        // Setting dialog transparent and bottom
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));


        // show dialog
        dialog.show();
    }
}