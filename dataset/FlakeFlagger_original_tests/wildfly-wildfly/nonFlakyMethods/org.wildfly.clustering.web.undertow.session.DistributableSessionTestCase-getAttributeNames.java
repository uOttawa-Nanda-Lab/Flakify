@Test public void getAttributeNames(){
  this.validate(session -> session.getAttributeNames());
  SessionManager<LocalSessionContext,Batch> manager=mock(SessionManager.class);
  Batcher<Batch> batcher=mock(Batcher.class);
  BatchContext context=mock(BatchContext.class);
  SessionAttributes attributes=mock(SessionAttributes.class);
  Set<String> expected=Collections.singleton("name");
  when(this.session.getAttributes()).thenReturn(attributes);
  when(attributes.getAttributeNames()).thenReturn(expected);
  when(this.manager.getSessionManager()).thenReturn(manager);
  when(manager.getBatcher()).thenReturn(batcher);
  when(batcher.resumeBatch(this.batch)).thenReturn(context);
  Object result=this.adapter.getAttributeNames();
  assertSame(expected,result);
  verify(context).close();
}
