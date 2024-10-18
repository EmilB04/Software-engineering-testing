package org.musicas.usecase;
import org.musicas.domain.Artist;
import org.musicas.dto.CreateArtistUseCaseDTO;

public class CreateArtistUseCase {
	MusicRepository musicRepository;

	public void execute(CreateArtistUseCaseDTO dto) {
		Artist artist = new Artist(dto.getName());
		musicRepository.createArtist(artist);
	}
}
