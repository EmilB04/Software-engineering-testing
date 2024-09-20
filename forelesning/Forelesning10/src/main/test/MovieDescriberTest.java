import model.Movie;
import model.MovieDescriber;
import repository.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MovieDescriberTest {

    @Mock
    MovieRepository mockMovieRepo;

    @Mock
    Movie mockMovie;

    @Test
    @DisplayName("Descrbibe movie")
    public void testDescribeMovie() {
        MovieDescriber movieDescriber = new MovieDescriber(MockMovieRepo);
        Mockito.when(mockMovieRepo.getMovieByTitle("The Matrix")).thenReturn(mockMovie);
        Mockito.when(mockMovie.getTitle()).thenReturn("The Matrix");
        Mockito.when(mock.getRuntimeInMinutes()).thenReturn(136);
    
        String result = movieDescriber.describeMovie("The Matrix");

        String expected = "The movie The Matrix has a runtime of 136 minutes.";
        Assertions.assertEquals(expected, result);
    }
}
