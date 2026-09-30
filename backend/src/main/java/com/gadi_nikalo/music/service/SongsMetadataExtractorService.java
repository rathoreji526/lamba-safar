package com.gadi_nikalo.music.service;

import com.gadi_nikalo.music.model.Song;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class SongsMetadataExtractorService{

    public Song extractBasicMetadata(String url){
        Song song = new Song();
        File tempFile = null;

        try{
            RestTemplate restTemplate = new RestTemplate();
            tempFile = File.createTempFile("song_", ".mp3");
            tempFile.deleteOnExit();

            final File targetFile = tempFile;
            restTemplate.execute(url, HttpMethod.GET,null, clientHttpResponse->{
                try(InputStream inputStream = clientHttpResponse.getBody()){
                    Files.copy(inputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                return null;
            });
            AudioFile audioFile = AudioFileIO.read(targetFile);
            Tag tag = audioFile.getTag();

            if(tag!=null){
                song.setTitle(tag.getFirst(FieldKey.TITLE));
                song.setArtist(tag.getFirst(FieldKey.ARTIST));
            }

            int trackLengthInSeconds = audioFile.getAudioHeader().getTrackLength();
            song.setDuration(trackLengthInSeconds*1000L);
            song.setUrl(url);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(tempFile!=null && tempFile.exists()){
                tempFile.delete();
            }
        }


        return song;
    }
}
