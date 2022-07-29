/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.maven.plugin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.plexus.build.incremental.BuildContext;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.manager.WroManager.Builder;
import ro.isdc.wro.manager.factory.WroManagerFactory;
import ro.isdc.wro.manager.factory.WroManagerFactoryDecorator;
import ro.isdc.wro.manager.factory.standalone.DefaultStandaloneContextAwareManagerFactory;
import ro.isdc.wro.maven.plugin.manager.factory.ConfigurableWroManagerFactory;
import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.model.group.Group;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.locator.UriLocator;
import ro.isdc.wro.model.resource.locator.factory.UriLocatorFactory;
import ro.isdc.wro.model.resource.processor.ResourcePostProcessor;
import ro.isdc.wro.model.resource.processor.factory.ConfigurableProcessorsFactory;
import ro.isdc.wro.model.resource.processor.factory.ProcessorsFactory;
import ro.isdc.wro.model.resource.processor.factory.SimpleProcessorsFactory;
import ro.isdc.wro.model.resource.processor.impl.css.CssUrlRewritingProcessor;
import ro.isdc.wro.model.resource.support.hash.HashStrategy;
import ro.isdc.wro.model.resource.support.naming.ConfigurableNamingStrategy;
import ro.isdc.wro.model.resource.support.naming.DefaultHashEncoderNamingStrategy;
import ro.isdc.wro.model.resource.support.naming.FolderHashEncoderNamingStrategy;
import ro.isdc.wro.model.resource.support.naming.NamingStrategy;
import ro.isdc.wro.util.WroTestUtils;
import ro.isdc.wro.util.WroUtil;
import ro.isdc.wro.util.concurrent.TaskExecutor;


/**
 * Test class for {@link Wro4jMojo}
 *
 * @author Alex Objelean
 */
public class TestWro4jMojo {
  private static final Logger LOG = LoggerFactory.getLogger(TestWro4jMojo.class);
  @Mock
  private BuildContext mockBuildContext;
  @Mock
  private HashStrategy mockHashStrategy;
  private UriLocatorFactory mockLocatorFactory;
  @Mock
  private UriLocator mockLocator;
  private File cssDestinationFolder;
  private File jsDestinationFolder;
  private File destinationFolder;
  private File extraConfigFile;
  private Wro4jMojo victim;

  @Before
  public void setUp()
      throws Exception {
    MockitoAnnotations.initMocks(this);
    mockLocatorFactory = new UriLocatorFactory() {
      public InputStream locate(final String uri)
          throws IOException {
        return mockLocator.locate(uri);
      }

      public UriLocator getInstance(final String uri) {
        return mockLocator;
      }
    };
    Context.set(Context.standaloneContext());
    victim = new Wro4jMojo();
    setUpMojo(victim);
  }

  /**
   * Perform basic initialization with valid values of the provided mojo.
   */
  private void setUpMojo(final Wro4jMojo mojo)
      throws Exception {
    mojo.setIgnoreMissingResources(false);
    mojo.setMinimize(true);
    setWroWithValidResources();
    destinationFolder = new File(FileUtils.getTempDirectory(), "wroTemp-" + new Date().getTime());
    destinationFolder.mkdir();
    cssDestinationFolder = new File(FileUtils.getTempDirectory(), "wroTemp-css-" + new Date().getTime());
    destinationFolder.mkdir();
    jsDestinationFolder = new File(FileUtils.getTempDirectory(), "wroTemp-js-" + new Date().getTime());
    destinationFolder.mkdir();
    extraConfigFile = new File(FileUtils.getTempDirectory(), "extraConfig-" + new Date().getTime());
    extraConfigFile.createNewFile();
    mojo.setBuildDirectory(destinationFolder);
    mojo.setExtraConfigFile(extraConfigFile);
    mojo.setDestinationFolder(destinationFolder);
    mojo.setMavenProject(Mockito.mock(MavenProject.class));
    mojo.setBuildContext(mockBuildContext);
  }

  private void setWroFile(final String classpathResourceName)
      throws URISyntaxException {
    final URL url = getClass().getClassLoader().getResource(classpathResourceName);
    final File wroFile = new File(url.toURI());
    victim.setWroFile(wroFile);
    victim.setContextFolder(wroFile.getParentFile().getParentFile());
  }

  private void setWroWithValidResources()
      throws Exception {
    setWroFile("wro.xml");
  }

  private void setWroWithInvalidResources()
      throws Exception {
    setWroFile("wroWithInvalidResources.xml");
  }

  public static final class ExceptionThrowingWroManagerFactory
      extends DefaultStandaloneContextAwareManagerFactory {
    @Override
    protected ProcessorsFactory newProcessorsFactory() {
      final SimpleProcessorsFactory factory = new SimpleProcessorsFactory();
      final ResourcePostProcessor postProcessor = Mockito.mock(ResourcePostProcessor.class);
      try {
        Mockito.doThrow(new RuntimeException()).when(postProcessor).process(Mockito.any(Reader.class),
            Mockito.any(Writer.class));
      } catch (final IOException e) {
        Assert.fail("never happen");
      }
      factory.addPostProcessor(postProcessor);
      return factory;
    }
  }

  public static class CustomManagerFactory
      extends DefaultStandaloneContextAwareManagerFactory {
  }

  public static final class CustomNamingStrategyWroManagerFactory
      extends DefaultStandaloneContextAwareManagerFactory {
    public static final String PREFIX = "renamed";
    {
      setNamingStrategy(new NamingStrategy() {
        public String rename(final String originalName, final InputStream inputStream)
            throws IOException {
          return PREFIX + originalName;
        }
      });
    }
  }

  public static final class CssUrlRewriterWroManagerFactory
      extends DefaultStandaloneContextAwareManagerFactory {
    @Override
    protected ProcessorsFactory newProcessorsFactory() {
      final SimpleProcessorsFactory factory = new SimpleProcessorsFactory();
      factory.addPreProcessor(new CssUrlRewritingProcessor());
      return factory;
    }
  }

  @Test public void shouldDetectIncrementalChange() throws Exception{victim=new Wro4jMojo(){@Override protected WroManagerFactory getManagerFactory(){return new WroManagerFactoryDecorator(super.getManagerFactory()){@Override protected void onBeforeBuild(final Builder builder){builder.setHashStrategy(mockHashStrategy);}};}};setUpMojo(victim);final String hashValue="SomeHashValue";when(mockHashStrategy.getHash(Mockito.any(InputStream.class))).thenReturn(hashValue);when(mockBuildContext.isIncremental()).thenReturn(true);when(mockBuildContext.getValue(Mockito.anyString())).thenReturn(hashValue);victim.setIgnoreMissingResources(true);assertTrue(victim.getTargetGroupsAsList().isEmpty());when(mockHashStrategy.getHash(Mockito.any(InputStream.class))).thenReturn("TotallyDifferentValue");assertFalse(victim.getTargetGroupsAsList().isEmpty());}

  private void configureMojoForModelWithImportedCssResource(final String importResource) throws Exception {
    final String parentResource = "parent.css";

    final WroModel model = new WroModel();
    model.addGroup(new Group("g1").addResource(Resource.create(parentResource)));
    when(mockLocator.locate(Mockito.anyString())).thenAnswer(answerWithContent(""));
    final String parentContent = String.format("@import url(%s)", importResource);
    when(mockLocator.locate(Mockito.eq(parentResource))).thenAnswer(answerWithContent(parentContent));

    victim = new Wro4jMojo() {
      @Override
      protected WroManagerFactory newWroManagerFactory()
          throws MojoExecutionException {
        final DefaultStandaloneContextAwareManagerFactory managerFactory = new DefaultStandaloneContextAwareManagerFactory();
        managerFactory.setUriLocatorFactory(mockLocatorFactory);
        managerFactory.setModelFactory(WroTestUtils.simpleModelFactory(model));
        return managerFactory;
      }
    };
    final HashStrategy hashStrategy = victim.getManagerFactory().create().getHashStrategy();
    setUpMojo(victim);

    final String importedInitialContent = "initial";

    when(mockLocator.locate(Mockito.eq(importResource))).thenAnswer(answerWithContent(importedInitialContent));
    when(mockBuildContext.isIncremental()).thenReturn(true);
    when(mockBuildContext.getValue(Mockito.eq(parentResource))).thenReturn(
        hashStrategy.getHash(new ByteArrayInputStream(parentContent.getBytes())));
    when(mockBuildContext.getValue(Mockito.eq(importResource))).thenReturn(
        hashStrategy.getHash(new ByteArrayInputStream(importedInitialContent.getBytes())));
    victim.setIgnoreMissingResources(true);
  }

  private Answer<InputStream> answerWithContent(final String content) {
    return new Answer<InputStream>() {
      public InputStream answer(final InvocationOnMock invocation)
          throws Throwable {
        return new ByteArrayInputStream(content.getBytes());
      }
    };
  }
}
