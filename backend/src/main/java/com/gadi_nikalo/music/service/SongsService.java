package com.gadi_nikalo.music.service;

import com.gadi_nikalo.music.model.Song;
import com.gadi_nikalo.music.repository.SongsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongsService {
    @Autowired
    private SongsRepository songsRepository;
    @Autowired
    private SongsMetadataExtractorService songsMetadataExtractorService;

    public String getSongMetadata(String songURL){
        return songsMetadataExtractorService.extractBasicMetadata(songURL).toString();
    }

    String URL = "https://drive.google.com/file/d/1Z7gyeR-UJ8km_rAwfs92BWJDXWYUKM6z/view?usp=drivesdk";
}
