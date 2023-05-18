import java.sql.*;
import java.util.*;
import java.io.*;

public class podcast extends jokeboxDemo{
    public void getPodcast(int user_id) throws SQLException,Exception{
        Statement statement = getConnection().createStatement();
        System.out.println("For Search :\npress 1 for podcast Name   :\npress 2 for podcast Episode     :");       
        Scanner sc=new Scanner(System.in);   
        List<String> list=new LinkedList<String>();
        List<String> list1=new LinkedList<String>();
        int n=sc.nextInt();
        int d=n;
        do{
        switch(d){
        case 1:{
            ResultSet res1=statement.executeQuery("select podcast_name,url from podcastEpisode");
            while(res1.next()){
                list.add(res1.getString("podcast_name"));
                list1.add(res1.getString("url"));
                System.out.println(res1.getString("podcast_name")); 
            }
            Scanner scan=new Scanner(System.in);
            System.out.println("1)  Play podcast with Name   :");
            String podcastname=scan.nextLine();
            ResultSet res=statement.executeQuery("select podcast_id,url from podcastEpisode where podcast_name='"+podcastname+"'");
            if(res.next())
                {
                    int podid=res.getInt("podcast_id");
                    String url=res.getString("url");
                    playsound(podid,url,"normal"); 
                }else{
                    System.out.println("Podcast is not Exist .....");
                }
            break;
        }case 2:{
            ResultSet res1=statement.executeQuery("select podcastEpisode_name from podcastEpisode");
            while(res1.next()){
                list.add(res1.getString("podcastEpisode_name"));
                list1.add(res1.getString("url"));
                System.out.println(res1.getString("podcastEpisode_name")); 
            }
            System.out.println("2)  Play podcast with Episode   :");
            Scanner scan=new Scanner(System.in);
            String podcastEpisodename=scan.nextLine();
            ResultSet res=statement.executeQuery("select podcastEpisode_id,url from podcastEpisode where podcastEpisode_name='"+podcastEpisodename+"'");
            if(res.next())
                {
                    int podid=res.getInt("podcastEpisode_id");
                    String url=res.getString("url");
                    playsound(podid,url,"normal"); 
                }else{
                    System.out.println("Podcast is not Exist .....");
                }
            break;
        }
    }
    System.out.println("press 1 => search Podcast by Name :\npress 2 => search Podcast by Episode   :\npress 0 => Not search Podcast    :");
    d=sc.nextInt();
    }while(d!=0);
     } 
}
