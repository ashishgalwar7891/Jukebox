create database jokebox;
use jokebox;

create table users(
users_id int not null auto_increment primary key,
users_name varchar(30) not null unique,
phoneNo varchar(10) not null unique,
password varchar(30) not null
);

insert into users values(1,'amit','1234567890','amit@123');
select * from users;

drop table users;

create table song(
 song_id int not null auto_increment primary key,
 song_name varchar(30) not null,
 artists varchar(30) not null,
 album varchar(30) not null,
 genres varchar(30) not null,
 duration varchar(30) not null,
 url varchar(1000) not null
);
desc song;
insert into song values(1,'Baarish_Ban_Jaana','Payal Dev, Stebin Ben','album1','bollywood','3.50','D:\\Product Engineering\\java-programming\\java-program\\Project\\song-podcast\\Baarish_Ban_Jaana.wav');
insert into song values(2,'IsQadar-Tumse-Humein','Tulsi Kumar,Darshan Raval','album2','panjabi','3.50','D:\\Product Engineering\\java-programming\\java-program\\Project\\song-podcast\\IsQadar-Tumse-Humein.wav');
insert into song values(3,'Kithe-Chaliye-Tu','jubin nautiyal','album3','Telgu','3.29','D:\Product Engineering\java-programming\java-program\Project\song-podcast\Kithe-Chaliye-Tu.wav');
insert into song values(4,'Mile-Ho-Tum-Tum-Humko','Neha Kakar sings','album4','Tamil','3.16','D:\\Product Engineering\\java-programming\\java-program\\Project\\song-podcast\\Mile-Ho-Tum-Tum-Humko.wav');
insert into song values(5,'Muskurana-Tera',' Saaj Bhatt','album5','gujrati','3.45','D:\\Product Engineering\\java-programming\\java-program\\Project\\song-podcast\\Muskurana-Tera.wav');

select * from song;
drop table song;


create table podcast(
 podcast_id int not null auto_increment primary key,
 podcast_name varchar(30) not null,
 artists varchar(30) not null,
 genres varchar(30) not null,
 podcastEpisode_id int 
);

insert into podcast values(1,'Elon Musk talk','Elon Musk','conversation');
insert into podcast values(2,'Dr.phil talk','Dr.phil','conversation');
insert into podcast values(3,'Michael shermer talk','Michael shermer','conversation');
insert into podcast values(4,'Robert kelly talk','Robert kelly','conversation');
insert into podcast values(5,'Ryan graves talk','Ryan graves','conversation');
-- drop table podcast;
select * from podcast;
drop table podcast;

create table podcastEpisode(
 podcastEpisode_id int not null auto_increment primary key,
 podcastEpisode_name varchar(30) not null,
 duration varchar(30) not null,
 url varchar(1000) not null,
podcast_id int not null,
podcast_name varchar(70) not null
);
-- drop table podcastEpisode;

insert into podcastEpisode values(1,'Elon Musk','3.50','D:\\Product Engineering\\java-programming\\java-program\\Project\\song-podcast\\podcast-1.wav');
insert into podcastEpisode values(2,'Dr.phil','3.50','D:\\Product Engineering\\java-programming\\java-program\\Project\\song-podcast\\podcast-2.wav');
insert into podcastEpisode values(3,'Michael shermer','3.29','D:\\Product Engineering\\java-programming\\java-program\\Project\\song-podcast\\podcast-3.wav');

alter table podcastEpisode add foreign key(podcast_id) references podcast(podcast_id);
select * from podcastEpisode;

create table playlist(
playlist_id int not null auto_increment primary key,
playlist_name varchar(30) not null,
users_id int not null,
song_id int  
);

insert into playlist values(1,'ABC','1','2');
insert into playlist values(2,'PQR','2','5');
insert into playlist values(3,'XYZ','1','4');

-- drop table playlist;
select * from playlist;

alter table playlist add foreign key(users_id) references users(users_id);
alter table playlist add foreign key(song_id) references song(song_id);
-- śalter table playlist add foreign key(podcastEpisode_id) references podcastEpisode(podcastEpisode_id);

