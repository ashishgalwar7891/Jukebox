import java.sql.*;
import java.util.*;
import java.io.*;
//TODO: NEXT and previous song
//TODO: P
public class Song extends jokeboxDemo{
    public Object obj;

    public List<String> getSearchSongByNameArtistsGenres(int user_id) throws SQLException,Exception{
        System.out.println("1)  Search Song by Name   :");
        System.out.println("2)  Search Song by Genres   :");
        System.out.println("3)  Search Song by Artist   :");
        Statement statement = getConnection().createStatement();
        Scanner sc=new Scanner(System.in);      
        int n=sc.nextInt();
        int c=n;
        List<String> list=new ArrayList<String>();
        List<String> list1=new ArrayList<>();
        do{
            switch(c){
                case 1:{
                    try{
                    ResultSet res1=statement.executeQuery("select song_name,url from song");
                    while(res1.next()){
                        list.add(res1.getString("song_name")); 
                        list1.add(res1.getString("url"));
                        System.out.println(res1.getString("song_name"));
                    }
                    System.out.println("Enter Song Name that you want to play");
                    Scanner scan=new Scanner(System.in);
                    String songname=scan.nextLine();
                    ResultSet res=statement.executeQuery("select song_id,url from song where song_name='"+songname+"'");
                    if(res.next())
                    {
                        int songid=res.getInt("song_id");
                        String url=res.getString("url");
                        playsound(songid,url,"normal"); 
                    }else{
                        System.out.println("Song is not Exist .....");
                    }
                }catch(SQLException e){
                    e.getMessage();
                }
                break;
            }    
            case 2:{
                try{
                    ResultSet res1=statement.executeQuery("select genres,url from song");
                    while(res1.next()){
                        list.add(res1.getString("genres"));
                        list1.add(res1.getString("url"));
                        System.out.println(res1.getString("genres")); 
                    }
                    System.out.println("Enter Genres Name that you want to play");
                    Scanner scan=new Scanner(System.in);
                    String genresname=scan.next();
                    ResultSet res=statement.executeQuery("select song_id,song_name,genres,url from song where genres='"+genresname+"'");
                    while(res.next()) {
                    System.out.println(res.getInt("song_id")+"      "+res.getString("song_name")+"      "+res.getString("genres"));
                    }
                    System.out.println("select one song id");
                    int a=sc.nextInt();
                    ResultSet res2=statement.executeQuery("select song_id,url from song where song_id="+a);
                    if(res2.next())
                    {
                        int songid=res2.getInt("song_id");
                        String url=res2.getString("url");
                         playsound(songid,url,"normal"); 
                    }else{
                        System.out.println("Song is not Exist .....");
                    }
                }catch(SQLException e){
                    e.getMessage();
                }
                break;
            }
            case 3:{
                try{
                    ResultSet res1=statement.executeQuery("select artists,url from song");
                    while(res1.next()){
                        list.add(res1.getString("artists"));
                        list1.add(res1.getString("url"));
                        System.out.println(res1.getString("artists")); 
                    }
                    System.out.println("Enter Artist Name that you want to play");
                    Scanner scan=new Scanner(System.in);
                    String artistname=scan.nextLine();
                    ResultSet res=statement.executeQuery("select song_id,url from song where artists='"+artistname+"'");
                    if(res.next()==true)
                    {
                        int songid=res.getInt("song_id");
                        String url=res.getString("url");
                        playsound(songid,url,"normal"); 
                    }else{
                        System.out.println("Song is not Exist .....");
                    }
                }catch(SQLException e){
                    e.getMessage();
                }
                break;
            }
        }
        System.out.println("press 1 for Again Search Song by Name:\npress 2 for Again Search song by Genres :\npress 3 for Again Search song by Artist:\npress 0 for Exit");
        c=sc.nextInt();
        }while(c!=0);
        return list1;
     }
     
}
