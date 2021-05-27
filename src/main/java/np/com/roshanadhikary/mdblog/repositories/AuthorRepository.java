package np.com.roshanadhikary.mdblog.repositories;

import np.com.roshanadhikary.mdblog.entities.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}