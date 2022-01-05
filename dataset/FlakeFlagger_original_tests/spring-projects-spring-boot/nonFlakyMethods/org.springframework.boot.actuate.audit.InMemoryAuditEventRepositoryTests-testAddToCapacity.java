@Test public void testAddToCapacity() throws Exception {
  this.repository.setCapacity(2);
  this.repository.add(new AuditEvent("phil","UNKNOWN"));
  this.repository.add(new AuditEvent("phil","UNKNOWN"));
  this.repository.add(new AuditEvent("dave","UNKNOWN"));
  this.repository.add(new AuditEvent("dave","UNKNOWN"));
  this.repository.add(new AuditEvent("phil","UNKNOWN"));
  assertEquals(2,this.repository.find("phil",new Date(0L)).size());
}
