/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.maven.plugin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Test;

import ro.isdc.wro.extensions.support.lint.LintReport;
import ro.isdc.wro.extensions.support.lint.ReportXmlFormatter.FormatterType;
import ro.isdc.wro.util.WroTestUtils;
import ro.isdc.wro.util.WroUtil;


/**
 * Test the {@link JsHintMojo} class.
 *
 * @author Alex Objelean
 */
public class TestJsHintMojo
    extends AbstractTestLinterMojo {
  @Test public void shouldGenerateXmlReportFile() throws Exception{final File reportFile=WroUtil.createTempFile();try {((JsHintMojo)getMojo()).setReportFile(reportFile);getMojo().setOptions("undef, browser");getMojo().setTargetGroups(null);getMojo().setFailNever(true);getMojo().setIgnoreMissingResources(true);getMojo().execute();}  finally {assertTrue(reportFile.length() > 1000);FileUtils.deleteQuietly(reportFile);}}

  private void executeResourcesWithErrors()
      throws MojoExecutionException {
    getMojo().setTargetGroups("invalidResources");
    getMojo().setOptions("undef, browser");
    getMojo().execute();
  }

  private void generateAndCompareReportFile(final String reportFormat, final String expectedFileName)
      throws Exception {
    final File reportFile = WroUtil.createTempFile();
    final JsHintMojo mojo = (JsHintMojo) getMojo();
    try {
      mojo.setReportFile(reportFile);
      if (reportFormat != null) {
        mojo.setReportFormat(reportFormat);
      }
      mojo.setOptions("undef, browser");
      mojo.setTargetGroups(null);
      mojo.setFailNever(true);
      mojo.setIgnoreMissingResources(true);
      mojo.execute();
    } finally {
      // Assert that file is big enough to prove that it contains serialized errors.
      WroTestUtils.compare(getClass().getResourceAsStream("report/" + expectedFileName),
          new FileInputStream(reportFile));
      FileUtils.deleteQuietly(reportFile);
    }
  }
}
