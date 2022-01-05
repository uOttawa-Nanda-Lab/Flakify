@Test public void testNowEvent() throws Exception {
  AuditEvent event=new AuditEvent("phil","UNKNOWN",Collections.singletonMap("a",(Object)"b"));
  assertEquals("b",event.getData().get("a"));
  assertEquals("UNKNOWN",event.getType());
  assertEquals("phil",event.getPrincipal());
  assertNotNull(event.getTimestamp());
}
