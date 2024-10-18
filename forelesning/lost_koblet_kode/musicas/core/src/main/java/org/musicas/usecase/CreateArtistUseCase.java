package org.musicas.usecase;
import org.musicas.core.Artist;
import org.musicas.dto.CreateArtistUseCaseDTO;

public class CreateArtistUseCase {

	public void execute(CreateArtistUseCaseDTO dto) {
		Artist artist = new Artist(dto.getName());
	}
}
