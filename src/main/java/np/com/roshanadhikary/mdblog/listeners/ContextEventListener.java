package np.com.roshanadhikary.mdblog.listeners;

import np.com.roshanadhikary.mdblog.entities.Author;
import np.com.roshanadhikary.mdblog.entities.Post;
import np.com.roshanadhikary.mdblog.repositories.AuthorRepository;
import np.com.roshanadhikary.mdblog.repositories.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static np.com.roshanadhikary.mdblog.util.MdFileReader.*;
import static np.com.roshanadhikary.mdblog.util.PostUtil.*;
import static np.com.roshanadhikary.mdblog.util.AuthorUtil.*;

@Component
public class ContextEventListener implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private PostRepository postRepository;

	@Value("classpath:posts/*")
	private Resource[] postFiles;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		Arrays.stream(postFiles).forEach(postFile -> {
			Optional<String> postFileNameOpt = Optional.ofNullable(postFile.getFilename());
			Post post = new Post();

			if (postFileNameOpt.isPresent()) {
				String postFileName = postFileNameOpt.get();
				String title = getTitleFromFileName(postFileName);
				long id = getIdFromFileName(postFileName);

				List<String> mdLines = readLinesFromMdFile(postFileName);
				String htmlContent = getHtmlContentFromMdLines(mdLines);

				Author author = bootstrapAuthor(authorRepository);

				Optional<Post> postOpt = postRepository.findById(id);
				if (postOpt.isEmpty()) {
					System.out.println("Post with ID: " + id + " does not exist. Creating post...");
					post.setTitle(title);
					post.setAuthor(author);
					post.setContent(htmlContent);
					post.setSynopsis(getSynopsisFromHtmlContent(htmlContent));
					post.setDateTime(LocalDateTime.now());

					postRepository.save(post);
					System.out.println("Post with ID: " + id + " created.");
				} else {
					System.out.println("Post with ID: " + id + " exists.");
				}
			} else {
				System.out.println("postFileName is null, should not be null");
			}
		});
	}
}

