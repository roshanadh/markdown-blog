package np.com.roshanadhikary.mdblog.util;

import np.com.roshanadhikary.mdblog.entities.Author;
import np.com.roshanadhikary.mdblog.repositories.AuthorRepository;

import java.util.Optional;

public class AuthorUtil {
	public static Author bootstrapAuthor(AuthorRepository authorRepository) {
		Optional<Author> authorOpt = authorRepository.findById(1L);
		if (authorOpt.isPresent()) {
			return authorOpt.get();
		} else {
			Author roshanAuthor = new Author();
			roshanAuthor.setName("Roshan Adhikari");
			roshanAuthor.setEmail("nahsorad@gmail.com");
			roshanAuthor.setUrl("roshanadhikari.name.np");

			authorRepository.save(roshanAuthor);
			return roshanAuthor;
		}
	}
}
