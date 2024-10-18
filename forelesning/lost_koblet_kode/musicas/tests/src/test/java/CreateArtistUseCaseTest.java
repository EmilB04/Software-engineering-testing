import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.musicas.repository.MusicRepository;
import org.musicas.usecase.CreateArtistUseCase;

public class CreateArtistUseCaseTest {
    @Mock
    MusicRepository mockMusicRepository;

    @Test
    @DisplayName("Successfully create an artist")
    public void createArtistSuccess() {
        // Arrange
        CreateArtistUseCase createArtistUseCase = new CreateArtistUseCase(mockMusicRepository);
        CreateArtistUseCaseDTO dto = new CreateArtistUseCaseDTO("radiohead");


        // Act
        createArtistUseCase.execute(dto);

        // Assert
        Mockito.verify(mockMusicRepository, Mockito.times(1)).
                createArtist(Mockito.any(Artist.class));
    }
}
