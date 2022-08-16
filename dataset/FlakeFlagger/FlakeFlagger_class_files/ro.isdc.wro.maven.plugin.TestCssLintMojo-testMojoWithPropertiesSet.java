/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.maven.plugin;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ro.isdc.wro.extensions.support.lint.ReportXmlFormatter;
import ro.isdc.wro.extensions.support.lint.ReportXmlFormatter.FormatterType;
import ro.isdc.wro.util.WroTestUtils;
import ro.isdc.wro.util.WroUtil;


/**
 * Test class for {@link CssHintMojo}
 *
 * @author Alex Objelean
 */
public class TestCssLintMojo {
  private CssLintMojo mojo;

  @Before
  public void setUp()
      throws Exception {
    mojo = new CssLintMojo();
    mojo.setIgnoreMissingResources(false);
    setWroWithValidResources();
    mojo.setTargetGroups("g1");
    mojo.setMavenProject(Mockito.mock(MavenProject.class));
  }

  private void setWroFile(final String classpathResourceName)
      throws Exception {
    final URL url = getClass().getClassLoader().getResource(classpathResourceName);
    final File wroFile = new File(url.toURI());
    mojo.setWroFile(wroFile);
    mojo.setContextFolder(wroFile.getParentFile().getParentFile());
  }

  private void setWroWithValidResources()
      throws Exception {
    setWroFile("wro.xml");
  }

  private void setWroWithInvalidResources()
      throws Exception {
    setWroFile("wroWithInvalidResources.xml");
  }

  @Test public void testMojoWithPropertiesSet() throws Exception{mojo.setTargetGroups("valid");mojo.setIgnoreMissingResources(true);mojo.execute();}

  void runPluginWithReportFormat(final String format)
      throws MojoExecutionException {
    final File reportFile = WroUtil.createTempFile();
    try {
      mojo.setReportFile(reportFile);
      mojo.setTargetGroups("valid");
      mojo.setReportFormat(format);
      mojo.execute();
    } finally {
      FileUtils.deleteQuietly(reportFile);
    }
  }

  private void generateAndCompareReportUsingFormat(final String reportFormat, final String expectedReportFileName)
      throws Exception {
    final File reportFile = WroUtil.createTempFile();
    try {
      mojo.setReportFile(reportFile);
      if (reportFormat != null) {
        mojo.setReportFormat(reportFormat);
      }
      // mojo.setOptions("undef, browser");
      mojo.setTargetGroups(null);
      mojo.setFailNever(true);
      mojo.setIgnoreMissingResources(true);
      mojo.execute();
    } finally {
      WroTestUtils.compare(getClass().getResourceAsStream("report/" + expectedReportFileName), new FileInputStream(
          reportFile));

      // Assert that file is big enough to prove that it contains serialized errors.
      assertTrue(reportFile.length() > 1000);
      FileUtils.deleteQuietly(reportFile);
    }
  }
}
