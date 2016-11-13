/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Gabriel
 */
public class Time {
    
    int hours, minutes, seconds, milis;
    
    public Time(long milisecs){
        milis = (int) milisecs;
        seconds = milis / 1000;
        minutes = seconds / 60;
        hours = minutes / 60;
        
        milis = milis % 1000;
        seconds = seconds % 60;
        minutes = minutes % 60;
    }
    
    public String getTimeString(){
        if(hours>0){
            return(String.format("%02d", hours) + ":"
                    + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));
        }else{
            return(String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));
        }
    }
    
    public void addSec(){
        seconds++;
        if(seconds == 60){
            seconds = 0;
            minutes++;
            if(minutes == 60){
                minutes = 0;
                hours++;
            }
        }
    }
    
    public void reset(){
        milis = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
    }
}
