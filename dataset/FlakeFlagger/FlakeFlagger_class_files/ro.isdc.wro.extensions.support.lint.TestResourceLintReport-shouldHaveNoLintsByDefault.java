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

  @Test public void shouldHaveNoLintsByDefault(){assertTrue(victim.getLints().isEmpty());}
}
