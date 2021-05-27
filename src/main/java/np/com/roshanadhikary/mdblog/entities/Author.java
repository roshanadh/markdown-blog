package np.com.roshanadhikary.mdblog.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "authors")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String url;

	@Column
	@OneToMany(mappedBy = "author")
	private List<Post> posts;
}