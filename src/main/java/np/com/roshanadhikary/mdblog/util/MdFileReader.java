package np.com.roshanadhikary.mdblog.util;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MdFileReader {

	public static List<String> readLinesFromMdFile(String filename) {
		try {
			InputStream iStream = new ClassPathResource("/posts/" + filename)
					.getInputStream();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream));
			return bReader.lines()
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getTitleFromFileName(String filename) {
		String fileNameBeforeExtension = filename.split(".md")[0];
		String[] tokens = fileNameBeforeExtension.split("_");

		String[] titleTokens = Arrays.copyOfRange(tokens, 1, tokens.length);
		return String.join(" ", titleTokens);
	}

	public static long getIdFromFileName(String filename) {
		String fileNameBeforeExtension = filename.split(".md")[0];
		return Long.parseLong(fileNameBeforeExtension.split("_")[0]);
	}
}
