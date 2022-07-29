package ro.isdc.wro.model.resource.support.change;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Alex Objelean
 */
public class TestResourceChangeInfo {
  private static final String GROUP1_NAME = "g1";
  private static final String GROUP2_NAME = "g2";
  private static final String GROUP3_NAME = "g3";
  private ResourceChangeInfo victim;

  @Before
  public void setUp() {
    victim = new ResourceChangeInfo();
  }


  @Test public void shouldDetectChangeForUpdatedGroupsOnly(){victim.updateHashForGroup("hash1",GROUP1_NAME);victim.updateHashForGroup("hash1",GROUP2_NAME);victim.reset();assertTrue(victim.isChanged(GROUP1_NAME));assertTrue(victim.isChanged(GROUP2_NAME));victim.updateHashForGroup("hash1",GROUP1_NAME);victim.updateHashForGroup("hash1",GROUP2_NAME);assertFalse(victim.isChanged(GROUP1_NAME));assertFalse(victim.isChanged(GROUP2_NAME));assertTrue(victim.isChanged(GROUP3_NAME));}
}
