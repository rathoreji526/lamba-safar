package com.gadi_nikalo.music.repository;

import com.gadi_nikalo.music.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SongsRepository extends JpaRepository<Song, UUID> {
}

