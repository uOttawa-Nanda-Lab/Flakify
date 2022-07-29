package org.apache.ambari.server.controller;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class ClusterResponseTest {

  @Test
  public void testToString() {
    ClusterResponse r = new ClusterResponse(null, null, null, null);
    r.toString();
  }
}
