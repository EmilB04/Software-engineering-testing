package org.musicas.port;
import org.musicas.domain.Artist;

public interface MusicRepository {
    void createArtist(Artist artist);
}
