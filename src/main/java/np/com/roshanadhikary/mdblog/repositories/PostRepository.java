package np.com.roshanadhikary.mdblog.repositories;

import np.com.roshanadhikary.mdblog.entities.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
}
