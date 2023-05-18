import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.*;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.SourceDataLine;

import com.mysql.cj.xdevapi.Result;

public class jokeboxDemo{
    Connection connection=null;
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jokebox","root","toor");
        }
        catch (SQLException e){
            e.getMessage();
        }
        catch (ClassNotFoundException ex){
            ex.getMessage();
        }
        return connection;
    }

    public void display() 
    {
    System.out.println();    
    System.out.println();    
    System.out.println("                     ##  ##     ##   ######   ##    ##  ########    #######   ##     ##"); 
    System.out.println("                     ##  ##     ##  ##    ##  ##   ##   ##     ##  ##     ##   ##   ## "); 
    System.out.println("                     ##  ##     ##  ##        ##  ##    ##     ##  ##     ##    ## ##  "); 
    System.out.println("                     ##  ##     ##  ##        #####     ########   ##     ##     ###   "); 
    System.out.println("               ##    ##  ##     ##  ##        ##  ##    ##     ##  ##     ##    ## ##  "); 
    System.out.println("               ##    ##  ##     ##  ##    ##  ##   ##   ##     ##  ##     ##   ##   ## "); 
    System.out.println("                ######    #######    ######   ##    ##  ########    #######   ##     ##");
    System.out.println();
    System.out.println("==============================================================================================================");
    System.out.println("                                            Welcome to Jukebox"                                                );
    System.out.println("==============================================================================================================");
    System.out.println("    Press 1 For Registration" + "        " + "Press 2 For Login" + "        "+"press 3 For Exit the Juckbox"      );
    System.out.println("==============================================================================================================");
    }
    public void SignUP() throws SQLException, ClassNotFoundException,NullPointerException,Exception
    {
        Statement statement = getConnection().createStatement();
        Scanner scan = new Scanner(System.in);  // Scan use for nextLine()input
        Scanner sc = new Scanner(System.in);    // sc use for remaining inputs
        int a = sc.nextInt();
        int b=a;
        do{
            switch(b){
                case 1:{
                    try{
                        System.out.println("Enter urer_name : ");
                        String username = scan.nextLine();
                        System.out.println("Welcome to JukeBox " + username);
                        System.out.println("Enter phoneNo : ");
                        String phoneNo = scan.nextLine();
                        System.out.println("Enter password : ");
                        String password = scan.nextLine();
                        int result=statement.executeUpdate("insert into users(users_name,phoneNo,Password) VALUES ('"+username+"','" + phoneNo + "','" + password + "')");
                        System.out.println( "====================================================================");
                        System.out.println("===========YOUR REGISTRATION IS SUCCESSFULLY COMPLETED ============");
                        System.out.println( "====================================================================");
                    }catch(Exception e){
                        System.out.println("This User Is Already Exist ");
                    }
                    break;
                }
                case 2:{
                    System.out.println("Enter your user_name : ");
                    String username = scan.nextLine();
                    System.out.println("Enter password : ");
                    String password = scan.nextLine();
                    ResultSet result=statement.executeQuery("select users_name,password from users where users_name='"+username+"' AND password='"+password+"'");
                    if(result.next()==true)
                    {
                        String usname=result.getString("users_name");
                        String uspwd=result.getString("password");
                        if(password.equals(uspwd) && username.equals(usname))
                        {
                            System.out.println( "================================");
                            System.out.println("login Successfully .... ");
                            System.out.println( "================================");
                            System.out.println("What you want to play : ===>:\n-------------------------------------------------------------------------");
                            System.out.println("Press 1 for song\tpress 2 for podcast:\tpress 3 for playlist:");
                            System.out.println("-------------------------------------------------------------------------");
                            int d=sc.nextInt();
                            if(d==1){
                                ResultSet res=statement.executeQuery("select users_id from users where users_name = '"+usname+"'");
                                if(res.next()){
                                    int userid=res.getInt("users_id");
                                    Song song=new Song();
                                    song.getSearchSongByNameArtistsGenres(userid);
                                }
                            }
                            if(d==2){
                                podcast pc=new podcast();
                                ResultSet res=statement.executeQuery("select users_id from users where users_name = '"+usname+"'");
                                if(res.next()){
                                    int userid=res.getInt("users_id");
                                    pc.getPodcast(userid);
                                }
                            }
                            if(d==3){
                                ResultSet res=statement.executeQuery("select users_id from users where users_name = '"+usname+"'");
                                if(res.next()){
                                    playlist1 p2=new playlist1();
                                    int userid=res.getInt("users_id");
                                    p2.getPlaylistDetails(userid);
                                }
                            }
                        }else{
                            System.out.println("wrong username or password");
                        }            
                    }else{
                        System.out.println("invalid User name or password  ");
                    }
                break;
                }
                case 3:{
                    System.exit(0);
                }
            }
            System.out.println("Press 1 for Another Registration\tpress 2 for Loging\tpress 0 for Exit");
            b=sc.nextInt();
        }while(b!=0);
    }

    public int playsound(int id,String url,String var)throws Exception,NullPointerException{
        // Song obj = new Song();
        // List<String> list = new ArrayList<String>();
        AudioInputStream audioInputStream=AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
        Clip clip=AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
        System.out.println("1. Stop");
        System.out.println("2. Replay");
        System.out.println("3. Resume");
        System.out.println("4. Forward/Backward");
        System.out.println("5. Next");
        System.out.println("6. Previous");
        System.out.println("7. Stop And Go To Song Menu");
        System.out.println("8. Stop And Go To podcast Menu");
        System.out.println("9. Stop And Go To playlist Menu");
        System.out.println("10. Exit");
        Scanner sc=new Scanner(System.in);
        long timeposition=0L;
        long tot=0L;
        long micro=0L;
        int key=sc.nextInt();
        int c=key;
        do{
        switch(c){
            case 1:{
                clip.stop();
                tot = clip.getMicrosecondLength();
                micro = clip.getMicrosecondPosition();
                System.out.println("Total Time of the Music => " + clip.getMicrosecondLength() / 1000000);
                System.out.println("played in seconds => " + micro / 1000000);
                System.out.println("remaining time for this song => " + (tot - micro) / 1000000);
                break;
            }
            case 2:{
                clip.stop();
                clip.setMicrosecondPosition(0L);
                clip.start();
                tot = clip.getMicrosecondLength();
                micro = clip.getMicrosecondPosition();
                System.out.println("Total Time of the Music => " + clip.getMicrosecondLength() / 1000000);
                System.out.println("played in seconds => " + micro / 1000000);
                System.out.println("remaining time for this song => " + (tot - micro) / 1000000);
                break;
            }
            case 3:{
                clip.stop();
                timeposition = clip.getMicrosecondPosition();
                clip.setMicrosecondPosition(timeposition);
                clip.start();
                tot = clip.getMicrosecondLength();
                micro = clip.getMicrosecondPosition();
                System.out.println("Total Time of the Music => " + clip.getMicrosecondLength() / 1000000);
                System.out.println("played in seconds => " + micro / 1000000);
                System.out.println("remaining time for this song => " + (tot - micro) / 1000000);
                break;
            }
            case 4:{
            System.out.println("|Enter 1 for Forward |       |Enter 2 for Backword|");
            int p=sc.nextInt();
            timeposition = clip.getMicrosecondPosition();
            if(p==1){
                System.out.println("Enter How Many Second you want to Forward Song  :");
                long s = sc.nextLong();
                clip.setMicrosecondPosition(timeposition + s * 1000000);
            }
            if(p==2){
                System.out.println("Enter How Many Second you want to Backword Song :");
                long s = sc.nextLong();
                clip.setMicrosecondPosition(timeposition - s * 1000000);
            }
            tot = clip.getMicrosecondLength();
            micro = clip.getMicrosecondPosition();
            clip.start();
            System.out.println("Total Time of the Music => " + clip.getMicrosecondLength() / 1000000);
            System.out.println("played in seconds => " + micro / 1000000);
            System.out.println("remaining time for this song => " + (tot - micro) / 1000000);
            break;
            }
            case 5:{
                clip.stop();
                if(var.equals("normal")){
                    id++;
                    Statement statement=getConnection().createStatement();
                    ResultSet result=statement.executeQuery("select url from song where song_id="+id+"");
                    if(result.next()){
                        String url1=result.getString(1);
                        playsound(id, url1,"normal");
                    }else{
                        System.out.println("------------------------------------");
                        System.out.println("No song found with this id :"+id);
                        System.out.println("------------------------------------");
                        return id;
                    }
                }
                else if(var.equals("playlist")){
                    return id;
                }
                break;
            } case 6:{
                clip.stop();
                if(var=="normal"){
                    id--;
                    Statement statement=getConnection().createStatement();
                    ResultSet result=statement.executeQuery("select url from song where song_id="+id+"");
                    if(result.next()){
                        String url1=result.getString(1);
                        playsound(id, url1,"normal");
                    }else{
                        System.out.println("------------------------------------");
                        System.out.println("No song found with this id :"+id);
                        System.out.println("------------------------------------");
                    }
                }else if(var=="playlist"){
                    return -1;
                }
                break;
            } 
            case 7:{
                clip.stop();
                Song song=new Song();
                song.getSearchSongByNameArtistsGenres(id);
            }
            case 8:{
                clip.stop();
                podcast podcast=new podcast();
                podcast.getPodcast(id);
            }
            case 9:{
                clip.stop();
                playlist1 playlist1=new playlist1();
                playlist1.getPlaylistDetails(id);
            }
            case 10:{
                System.exit(0);
            } 
            default: System.out.println("Invalid Entry");
        }
    System.out.println("1. stop\n2. Replay\n3. Resume\n4. Forward/backward\n5. Next\n6. Previous\n7. Stop And Go To Song Menu\n8. Stop And Go To podcast Menu \n9. Stop And Go To playlist Menu\n10. Exit from Juckbox");    
    c=sc.nextInt();
    }while(c!=0);
    return id;
   }


public static void main(String[] args) throws ClassNotFoundException,SQLException,Exception
{
    jokeboxDemo jbd=new jokeboxDemo();
    jbd.display();
    jbd.SignUP();

}
}