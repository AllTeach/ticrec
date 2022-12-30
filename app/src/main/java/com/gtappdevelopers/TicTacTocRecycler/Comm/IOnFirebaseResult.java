package com.gtappdevelopers.TicTacTocRecycler.Comm;

public interface IOnFirebaseResult {
    void firebaseResult(ResultType result,boolean success);
    void firebaseGameInfo(ResultType result,boolean success,int row, int col);
}
