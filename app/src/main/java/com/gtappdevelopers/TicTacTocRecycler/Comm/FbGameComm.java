package com.gtappdevelopers.TicTacTocRecycler.Comm;

public class FbGameComm {


    interface FirebaseGameInfo
    {
        void firebaseGameInfo(ResultType result, boolean success, int row, int col);
    }

    private FirebaseGameInfo fbGameInfo;

    public FbGameComm(FirebaseGameInfo fbGameInfo)
    {
        this.fbGameInfo = fbGameInfo;

    }
}
