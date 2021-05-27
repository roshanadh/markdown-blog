package np.com.roshanadhikary.mdblog.util;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.List;

public class MdToHtmlRenderer {
	/**
	 * Parse List of Markdown lines passed as argument, and render
	 * corresponding HTML
	 */
	public static String renderHtml(List<String> markdownLines) {
		Parser parser = Parser.builder().build();
		HtmlRenderer renderer = HtmlRenderer.builder().build();

		StringBuilder renderedSB = new StringBuilder();
		for (String markdownLine : markdownLines) {
			Node document = parser.parse(markdownLine);
			renderedSB.append(renderer.render(document));
		}
		return new String(renderedSB);
	}
}
