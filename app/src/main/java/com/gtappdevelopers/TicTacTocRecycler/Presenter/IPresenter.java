package com.gtappdevelopers.TicTacTocRecycler.Presenter;

public interface IPresenter
{
    void userClick(int row,int col);
    void restartGame();
    void userRegister(String email,String password);
    void userLogin(String email,String password);
    void startOrJoinGame();

}
