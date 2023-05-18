import java.sql.*;
import java.util.*;

import com.mysql.cj.xdevapi.Result;

import java.io.*;

public class playlist1 extends jokeboxDemo
{
    public void getPlaylistDetails(int id)throws Exception{
        Statement statement = getConnection().createStatement(); 
        Scanner sc = new Scanner(System.in);
        System.out.println("press 1 ==> Create new playlist :\npress 2 ==> Add song to playlist    :\npress 3==> play song to playlist\npress 0 ==> Exit to Juckbox");
        int a=sc.nextInt();
        int c=a;
        do{
            switch(c){
            case 1:{
                userplaylist(id);
                createPlaylist(id);
            }
            case 2:{
                userplaylist(id);
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter the playlist name in which you want to enter the Song:");
                String Playlistname=scan.nextLine();
                addSongToPlaylist(id,Playlistname);
            }
            case 3 :{
                List<String> list = showPlaylistSong(id);
                PlayInPlaylist obj = new PlayInPlaylist();
                System.out.println("Enter the no of Song :");
                int  count1=sc.nextInt();
                obj.playsong(list,count1);
            }
            case 0:{
                System.exit(0);
            }
        System.out.println("press 1 ==> Create new playlist :\npress 2 ==> Add song to playlist    :\npress 3==> play song to playlist\npress 0 ==> Exit to Juckbox");
        c=sc.nextInt();
        }
    }while(c!=4);
}
    public void createPlaylist(int us) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the name of new playlist => ");  
        String newplaylist = scan.nextLine();
        Statement statement = getConnection().createStatement();
        statement.executeUpdate("insert into playlist(playlist_name,users_id) values('" + newplaylist + "'," + us + ")");
        System.out.println("-------- playlist is created Successfully------------");
        System.out.println("============================================================");
        userplaylist(us);
    }

    public void userplaylist(int us) throws Exception {
        Statement statement = getConnection().createStatement();
        try {
            ResultSet rs = statement.executeQuery("select distinct(playlist_name) from playlist where users_id=" + us );
            System.out.println("----- Your Existing PlayLists -------");
            while (rs.next()) {
                System.out.println(rs.getString("playlist_name"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addToPlaylist(int us) throws Exception {
        Statement statement = getConnection().createStatement();
        Scanner scan = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the playlist that you want to open ==> ");
        String playlistname = scan.nextLine();
        showPlaylistSong(us);
        System.out.println("Press 1 for Insert Song in playlist\nPress 0 for Exit in Jukebox");
        int count = sc.nextInt();
        if (count == 1) {
            int a;
            do {
                addSongToPlaylist(us, playlistname);
                System.out.println("Press 1 if you want to insert more songs\nIf not then press 0");
                a = sc.nextInt();
            } while (a == 1);
        }
        else if (count == 0) {
            System.exit(count);
        } else {
            System.out.println("Enter the correct Input");
        }
    }

    public List<String> showPlaylistSong(int us) throws Exception {
        Statement statement = getConnection().createStatement();
        List<String> list = new ArrayList<>();
        try {
            Scanner sc=new Scanner(System.in);
            System.out.println("Name of your Exiting Playlist");
            ResultSet rs = statement.executeQuery("select distinct(playlist_name) from playlist where users_id= '" + us + "'");
            while (rs.next()) {
                System.out.println(rs.getString("playlist_name"));
            }
            System.out.println("Enter the name of the playlist :");
            String playlistname=sc.nextLine();
            System.out.println("No. " + "     " + "Song Name");
            ResultSet rs1 = statement.executeQuery("select distinct(playlist.song_id), song.song_name, song.url from playlist Right join song on song.song_id = playlist.song_id where playlist_name ='"+ playlistname + "'and users_id= '" + us + "'");
            while (rs1.next()) {
                System.out.println(rs1.getInt(1) + "  " + rs1.getString(2));
                list.add(rs1.getInt(1) + "," + rs1.getString(3));
            }
            return list;
        }
        catch (Exception e) {
            System.out.println("There has an error");
            e.getMessage();
        }
        return list;
    }


    //create method add song to playlist
    public void addSongToPlaylist(int us, String name) throws Exception{
        Scanner sc = new Scanner(System.in);
        Statement statement = getConnection().createStatement();
        try {
            int b;
            do{
                System.out.println("No." + "     " + "Song Name");
                ResultSet rs = statement.executeQuery("select song_id, song_name from song");
                while (rs.next()) {
                    System.out.println(rs.getInt("song_id") + "    " + rs.getString("song_name"));
                }
                System.out.println("Enter the song number you want to Insert");
                int songid = sc.nextInt();
                statement.executeUpdate("insert into playlist(playlist_name,users_id,song_id) values('"+ name + "'," + us + "," + songid + ")");
                System.out.println("---------------Song is Successfully inserted into playlist-------------------");
            System.out.println("If you want toInsert more Song then press 1");
            b=sc.nextInt();
            }while(b==1);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


}
