package ro.isdc.wro.model.resource.processor.support;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.model.resource.locator.ClasspathUriLocator;
import ro.isdc.wro.model.resource.processor.support.ImageUrlRewriter.RewriterContext;


/**
 * @author Alex Objelean
 */
public class TestImageUrlRewriter {
  private static final String DEFAULT_PREFIX = "[prefix]";
  private static final String DEFAULT_CONTEXT_PATH = "/";

  private static final String DEFAULT_CSS_URI = "/path/to/style.css";
  private static final String PROTECTED_CSS_URI = "/WEB-INF/path/to/style.css";
  private static final String DEFAULT_IMAGE_URL = "/img/image.png";
  private static final String RELATIVE_IMAGE_URL = "img/image.png";
  private RewriterContext context;
  private ImageUrlRewriter victim;

  @Before
  public void setUp() {
    context = new RewriterContext();
    context.setProxyPrefix(DEFAULT_PREFIX);
    context.setContextPath(DEFAULT_CONTEXT_PATH);
    victim = new ImageUrlRewriter(context);
  }

  @Test public void checkRelativeWithDotsImageUrlInClasspathCssResource(){final String actual=victim.rewrite(ClasspathUriLocator.createUri(DEFAULT_CSS_URI),"../" + RELATIVE_IMAGE_URL);final String expected=DEFAULT_PREFIX + "classpath:/path/" + RELATIVE_IMAGE_URL;assertEquals(expected,actual);}
}
