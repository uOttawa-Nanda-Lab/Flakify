package org.wildfly.clustering.marshalling.jboss;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HashableMarshalledValueFactoryTestCase {
/** 
 * Test method for  {@link org.jboss.ha.framework.server.SimpleMarshalledValue#equals(java.lang.Object)}.
 */
@Test public void equals() throws Exception {
  UUID uuid=UUID.randomUUID();
  SimpleMarshalledValue<UUID> mv=this.factory.createMarshalledValue(uuid);
  assertTrue(mv.equals(mv));
  assertFalse(mv.equals(null));
  SimpleMarshalledValue<UUID> dup=this.factory.createMarshalledValue(uuid);
  assertTrue(mv.equals(dup));
  assertTrue(dup.equals(mv));
  SimpleMarshalledValue<UUID> replica=replicate(mv);
  assertTrue(mv.equals(replica));
  assertTrue(replica.equals(mv));
  SimpleMarshalledValue<UUID> nulled=this.factory.createMarshalledValue(null);
  assertFalse(mv.equals(nulled));
  assertFalse(nulled.equals(mv));
  assertFalse(replica.equals(nulled));
  assertFalse(nulled.equals(replica));
  assertTrue(nulled.equals(nulled));
  assertFalse(nulled.equals(null));
  assertTrue(nulled.equals(this.factory.createMarshalledValue(null)));
}

}