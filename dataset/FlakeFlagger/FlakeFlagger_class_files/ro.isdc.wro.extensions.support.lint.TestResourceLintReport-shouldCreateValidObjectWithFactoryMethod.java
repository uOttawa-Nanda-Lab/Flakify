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

  @Test public void shouldCreateValidObjectWithFactoryMethod(){victim=ResourceLintReport.create("path",Arrays.asList("1","2"));assertEquals("path",victim.getResourcePath());assertEquals(2,victim.getLints().size());}
}
