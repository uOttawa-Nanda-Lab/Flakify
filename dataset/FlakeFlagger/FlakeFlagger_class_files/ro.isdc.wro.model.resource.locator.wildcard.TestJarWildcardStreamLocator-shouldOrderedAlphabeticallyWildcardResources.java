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


  @Test public void shouldOrderedAlphabeticallyWildcardResources() throws IOException{final ThreadLocal<Collection<String>> filenameListHolder=new ThreadLocal<Collection<String>>();final UriLocator uriLocator=createJarLocator(filenameListHolder);uriLocator.locate("classpath:com/app/**.css");final Collection<String> filenameList=filenameListHolder.get();Assert.assertNotNull(filenameList);Assert.assertEquals(Arrays.toString(new String[]{"com/app/level1/level2/styles/style.css","com/app/level1/level2/level2.css","com/app/level1/level1.css"}),Arrays.toString(filenameList.toArray()));}

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
