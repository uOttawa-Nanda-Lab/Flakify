package ro.isdc.wro.model.resource.locator.wildcard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.isdc.wro.model.resource.locator.ClasspathUriLocator;
import ro.isdc.wro.model.resource.locator.UriLocator;


/**
 * Tests the {@link JarWildcardStreamLocator} class.
 *
 * @author Matias Mirabelli &lt;matias.mirabelli@globant.com&gt;
 * @since 1.3.6
 */
public class TestJarWildcardStreamLocator {
  private static final Logger LOG = LoggerFactory.getLogger(TestJarWildcardStreamLocator.class);

  private static final String SEP = File.separator;

  private JarWildcardStreamLocator jarStreamLocator;

  private final String testInfo = "var foo = 'Hello World';";

  private final String jarFileName = "file:///home/test/myJar.jar!";
  @Mock
  private JarFile jarFile;


  @Before
  public void setUp()
    throws IOException {
    final Vector<JarEntry> vector = new Vector<JarEntry>();

    vector.add(new JarEntry("com/test/app/test-resource.js"));

    when(jarFile.entries()).thenReturn(vector.elements());
    when(jarFile.getInputStream(vector.get(0))).thenReturn(new ByteArrayInputStream(testInfo.getBytes()));

    jarStreamLocator = new JarWildcardStreamLocator() {
      @Override
      protected JarFile open(final File file) {
        return jarFile;
      }
    };
  }


  @Test(expected=IOException.class) public void testLocateJarStreamDelegateFail() throws IOException{jarStreamLocator.locateStream("com/test/app/*.js",new File("test.jpg"));}

  /**
   * @return creates an instance of {@link UriLocator} which uses {@link JarWildcardStreamLocator} for locating
   *         resources containing wildcards. Also it uses a jar file from test resources.
   */
  private UriLocator createJarLocator(final ThreadLocal<Collection<String>> filenameListHolder) {
    final JarWildcardStreamLocator jarStreamLocator = new JarWildcardStreamLocator() {
      @Override
      File getJarFile(final File folder) {
        //Use a jar from test resources
        return new File(TestJarWildcardStreamLocator.class.getResource("resources.jar").getFile());
      }
      @Override
      void triggerWildcardExpander(final Collection<File> allFiles, final WildcardContext wildcardContext)
        throws IOException {
        final Collection<String> filenameList = new ArrayList<String>();
        for (final File entry : allFiles) {
          filenameList.add(entry.getPath().replace("\\", "/"));
        }
        filenameListHolder.set(filenameList);
      }
    };
    final UriLocator uriLocator = new ClasspathUriLocator() {
      @Override
      public WildcardStreamLocator newWildcardStreamLocator() {
        return jarStreamLocator;
      }
    };
    return uriLocator;
  }
}
