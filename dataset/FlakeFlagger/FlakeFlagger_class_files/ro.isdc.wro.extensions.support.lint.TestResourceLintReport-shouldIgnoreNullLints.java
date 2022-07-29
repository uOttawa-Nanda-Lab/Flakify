package ro.isdc.wro.extensions.support.lint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Alex Objelean
 */
public class TestResourceLintReport {
  private ResourceLintReport<String> victim;

  @Before
  public void setUp() {
    victim = new ResourceLintReport<String>();
  }

  @Test public void shouldIgnoreNullLints(){final List<String> lints=new ArrayList<String>();lints.add(null);lints.add(null);final ResourceLintReport<String> report=ResourceLintReport.create("uri",lints);assertTrue(report.getLints().isEmpty());}
}
