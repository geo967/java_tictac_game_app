package com.example.tictacgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView playerOneScore,playerTwoScore,playerStatus;
    private final Button[] buttons=new Button[9];
    private Button resetButton;

    private int playerOneScoreCount=0,playerTwoScoreCount=0,roundCount=0;
    boolean activePlayer=true;

    // p1 -> 0
    // p2 -> 1
    // empty -> 2
    int[] gameState= {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneScore=findViewById(R.id.score_of_player_one);
        playerTwoScore=findViewById(R.id.score_of_player_two);
        playerStatus=findViewById(R.id.winning_player);

        resetButton=findViewById(R.id.reset_button);

        buttons[0]=findViewById(R.id.btn_one);
        buttons[1]=findViewById(R.id.btn_two);
        buttons[2]=findViewById(R.id.btn_three);
        buttons[3]=findViewById(R.id.btn_four);
        buttons[4]=findViewById(R.id.btn_five);
        buttons[5]=findViewById(R.id.btn_six);
        buttons[6]=findViewById(R.id.btn_seven);
        buttons[7]=findViewById(R.id.btn_eight);
        buttons[8]=findViewById(R.id.btn_nine);

    }

    public void buttonClicked(View view){
        if(((Button)view).getText().toString().equals("")){

            String buttonIdString=view.getResources().getResourceEntryName(view.getId());

            int count=0;
            switch(buttonIdString){
                case "btn_one":{
                    count=1;
                    break;
                }
                case "btn_two":{
                    count=2;
                    break;
                }
                case "btn_three":{
                    count=3;
                    break;
                }
                case "btn_four":{
                    count=4;
                    break;
                }
                case "btn_five":{
                    count=5;
                    break;
                }
                case "btn_six":{
                    count=6;
                    break;
                }
                case "btn_seven":{
                    count=7;
                    break;
                }
                case "btn_eight":{
                    count=8;
                    break;
                }
                case "btn_nine":{
                    count=9;
                    break;
                }
            }

            if(activePlayer){
                ((Button) view).setText("X");
                gameState[count-1]=0;
            }
            else{
                ((Button) view).setText("0");
                gameState[count-1]=1;

            }
        roundCount++;
            if(checkWinner()){
                if(activePlayer){
                    playerOneScoreCount++;
                    updatePlayerScore();
                    Toast.makeText(this,"player one won",Toast.LENGTH_SHORT).show();
                }else{
                    playerTwoScoreCount++;
                    updatePlayerScore();
                    Toast.makeText(this,"player two won",Toast.LENGTH_SHORT).show();
                }
                playAgain();
            }else if(roundCount==9){
                playAgain();
                Toast.makeText(this,"No winner",Toast.LENGTH_SHORT).show();
            }else{
                activePlayer=!activePlayer;
            }

            if(playerOneScoreCount>playerTwoScoreCount){
                playerStatus.setText("Player One is Winning");
            }else if(playerOneScoreCount<playerTwoScoreCount){
                playerStatus.setText("Player Two is Winning");
            }else{
                playerStatus.setText("Tied");
            }

            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playAgain();
                    playerOneScoreCount=0;
                    playerTwoScoreCount=0;
                    playerStatus.setText("");
                    updatePlayerScore();
                }
            });
        }
    }

    public boolean checkWinner(){
        boolean winnerResult=false;

        for (int[] winningPosition:winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 2) {
                winnerResult = true;
                break;
            }
        }
        return winnerResult;
    }

    public void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));

    }

    public void playAgain(){
        roundCount=0;
        activePlayer=true;
        for (int i = 0; i < buttons.length ; i++) {
            gameState[i]=2;
            buttons[i].setText("");
        }
    }

}