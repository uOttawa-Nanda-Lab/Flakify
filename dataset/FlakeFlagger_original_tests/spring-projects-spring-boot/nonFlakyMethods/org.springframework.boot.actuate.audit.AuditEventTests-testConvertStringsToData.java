@Test public void testConvertStringsToData() throws Exception {
  AuditEvent event=new AuditEvent("phil","UNKNOWN","a=b","c=d");
  assertEquals("b",event.getData().get("a"));
  assertEquals("d",event.getData().get("c"));
}
