package org.musicas.usecase;
import org.musicas.domain.Artist;
import org.musicas.dto.CreateArtistUseCaseDTO;
import org.musicas.port.MusicRepository;

public class CreateArtistUseCase {
	MusicRepository musicRepository;

	public CreateArtistUseCase(MusicRepository musicRepository) {
		this.musicRepository = musicRepository;
	}

	public void execute(CreateArtistUseCaseDTO dto) {
		Artist artist = new Artist(dto.getName());
		musicRepository.createArtist(artist);
	}
}
