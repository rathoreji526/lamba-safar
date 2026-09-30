package com.gadi_nikalo.music.controller;

import com.gadi_nikalo.music.DTO.SongRequestDTO;
import com.gadi_nikalo.music.service.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/song")
public class SongsController {
    @Autowired
    private SongsService songsService;

    @PostMapping("/getMetadata")
    public String getSongData(@RequestBody SongRequestDTO dto) {
        return songsService.getSongMetadata(dto.getSongURL());
    }
}
