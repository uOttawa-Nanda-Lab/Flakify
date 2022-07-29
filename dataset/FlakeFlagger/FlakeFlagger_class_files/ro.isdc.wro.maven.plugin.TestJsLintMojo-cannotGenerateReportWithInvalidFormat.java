/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.maven.plugin;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Test;

import ro.isdc.wro.extensions.support.lint.ReportXmlFormatter.FormatterType;
import ro.isdc.wro.util.WroTestUtils;
import ro.isdc.wro.util.WroUtil;


/**
 * Test the {@link JsLintMojo} class.
 *
 * @author Alex Objelean
 */
public class TestJsLintMojo
    extends AbstractTestLinterMojo {
  @Test(expected = MojoExecutionException.class)
  public void cannotGenerateReportWithInvalidFormat()
      throws Exception {
    final File reportFile = WroUtil.createTempFile();
    final JsLintMojo mojo = (JsLintMojo) getMojo();
    try {
      mojo.setReportFile(reportFile);
      mojo.setReportFormat("INVALID");
      mojo.setOptions("undef, browser");
      mojo.setTargetGroups(null);
      mojo.setFailNever(true);
      mojo.setIgnoreMissingResources(true);
      mojo.execute();
    } finally {
      FileUtils.deleteQuietly(reportFile);
    }
  }
}
