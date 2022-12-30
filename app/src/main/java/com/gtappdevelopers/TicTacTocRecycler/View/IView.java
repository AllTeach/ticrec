package com.gtappdevelopers.TicTacTocRecycler.View;

public interface IView
{
    void markButton(int i,int j,char letter);
    void displayMessage(String message);
    void clearBoard();
    void displayEndGame(String message);
}
