@Test public void testStoredEvents(){
  AuditEventRepository repository=mock(AuditEventRepository.class);
  AuditEvent event=new AuditEvent("principal","type",Collections.<String,Object>emptyMap());
  AuditListener listener=new AuditListener(repository);
  listener.onApplicationEvent(new AuditApplicationEvent(event));
  verify(repository).add(event);
}
