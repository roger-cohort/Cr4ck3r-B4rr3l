//Robin Rajan
//CSCE 4430: Programming Languages
//12/05/18

import java.io.*;
import java.util.*;

public class CB_Game
{
    public static void Playing(int startpiece)
    {
        ArrayList<Integer> GameB = new ArrayList<Integer>();
        for(int i=1; i<16; i++)
            GameB.add(i);
        RemovePiece(GameB, startpiece);
        solve(GameB);
    }

    public static int[][] Places = {
        {1,2,4}, {1,3,6}, {2,4,7}, {2,5,9}, {3,5,8}, {3,6,10}, {4,2,1}, {4,5,6}, {4,7,11}, 
        {4,8,13}, {5,8,12}, {5,9,14}, {6,3,1}, {6,5,4}, {6,9,13}, {6,10,15}, {7,4,2}, {7,8,9},
        {8,5,3}, {8,9,10}, {9,5,2}, {9,8,7}, {10,9,8}, {11,12,13}, {12,8,5}, {12,13,14}, 
        {13,8,4}, {13,9,6}, {13,12,11}, {13,14,15}, {14,9,5}, {14,13,12}, {15,10,6}, {15,14,13}
    };
    
    public static ArrayList<Integer> Result = new ArrayList<Integer>();
    
    public static void RemovePiece(ArrayList<Integer> GameB, int x)
    {
        GameB.remove(Integer.valueOf(x));
    }
    
    public static void AddPiece(ArrayList<Integer> GameB, int x)
    {
        GameB.add(Integer.valueOf(x));
    }
    
    public static boolean FindPiece(ArrayList<Integer> GameB, int x)
    {
        if(GameB.contains(x))
            return true;
        else
            return false;
    }
    
    public static ArrayList<Integer> solve(ArrayList<Integer> GameB)
    {
        drawGameB(GameB);
        if(GameB.size() == 1)
            return GameB;
        for(int i=1; i<16; i++)
        {
            if(FindPiece(GameB, i))
            {
                for(int j=0; j<34; j++)
                {
                    if(Places[j][0] == i)
                    {
                        int from = Places[j][1];
                        int to = Places[j][2];
                        if(FindPiece(GameB, from) == true && FindPiece(GameB, to) == false)
                        {
                            ArrayList<Integer> saveGameB = new ArrayList<Integer>(GameB);
                            RemovePiece(GameB, i);
                            RemovePiece(GameB, from);
                            AddPiece(GameB, to);
                        
                            Result.add(i);
                            Result.add(from);
                            Result.add(to);
                        
                            GameB = solve(GameB);
                            if(GameB.size() == 1)
                                return GameB;
                                
                            ArrayList<Integer> newGameB = new ArrayList<Integer>(saveGameB);
                            GameB = newGameB;
                            Result.remove(Result.size() - 1);
                            Result.remove(Result.size() - 1);
                            Result.remove(Result.size() - 1);
                        }    
                    }
                }
            }
        }
        return GameB;
    }
    public static void drawGameB(ArrayList<Integer> GameB)
    {
        char piece[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for(int i=1; i<16; i++)
        {
            piece[i] = '0';
            if(GameB.contains(i))
                piece[i] = '1';
        }
        System.out.println("     " + piece[1]);
        System.out.println("    " + piece[2] + " " + piece[3]);
        System.out.println("   " + piece[4] + " " + piece[5] + " " + piece[6]);
        System.out.println("  " + piece[7] + " " + piece[8] + " " + piece[9] + " " + piece[10]);
        System.out.println(" " + piece[11] + " " + piece[12] + " " + piece[13] + " " + piece[14] + " " + piece[15]);
    }
    
    public static void main(String[] Parameters)
    {
        int startpiece = 1;
        Playing(startpiece);
    }
}