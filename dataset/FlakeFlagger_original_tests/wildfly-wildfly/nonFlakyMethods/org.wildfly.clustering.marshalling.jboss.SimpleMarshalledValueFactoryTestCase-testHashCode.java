/** 
 * Test method for  {@link org.jboss.ha.framework.server.SimpleMarshalledValue#hashCode()}.
 */
@Test public void testHashCode() throws Exception {
  UUID uuid=UUID.randomUUID();
  SimpleMarshalledValue<UUID> mv=this.factory.createMarshalledValue(uuid);
  assertEquals(uuid.hashCode(),mv.hashCode());
  SimpleMarshalledValue<UUID> copy=replicate(mv);
  this.validateHashCode(uuid,copy);
  mv=this.factory.createMarshalledValue(null);
  assertEquals(0,mv.hashCode());
}
