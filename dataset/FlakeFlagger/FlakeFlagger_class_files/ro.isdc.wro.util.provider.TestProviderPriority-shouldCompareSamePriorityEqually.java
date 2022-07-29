package ro.isdc.wro.util.provider;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import ro.isdc.wro.util.Ordered;


public class TestProviderPriority {

  private static Ordered HIGH = new Ordered() {
    public int getOrder() {
      return Ordered.HIGHEST;
    }
  };
  private static Object MEDIUM = new Object();
  private static Ordered MEDIUM_HIGH = new Ordered() {
    public int getOrder() {
      return 10;
    }
  };
  private static Ordered LOW = new Ordered() {
    public int getOrder() {
      return Ordered.LOWEST;
    }
  };
  
  @Test public void shouldCompareSamePriorityEqually(){assertEquals(0,Ordered.ASCENDING_COMPARATOR.compare(LOW,LOW));assertEquals(0,Ordered.ASCENDING_COMPARATOR.compare(MEDIUM,MEDIUM));assertEquals(0,Ordered.ASCENDING_COMPARATOR.compare(HIGH,HIGH));}
}
