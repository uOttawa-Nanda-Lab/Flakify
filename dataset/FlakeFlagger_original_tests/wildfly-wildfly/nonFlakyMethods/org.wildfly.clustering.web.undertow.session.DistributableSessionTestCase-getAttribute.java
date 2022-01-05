@Test public void getAttribute(){
  String name="name";
  this.validate(session -> session.getAttribute(name));
  SessionManager<LocalSessionContext,Batch> manager=mock(SessionManager.class);
  Batcher<Batch> batcher=mock(Batcher.class);
  BatchContext context=mock(BatchContext.class);
  SessionAttributes attributes=mock(SessionAttributes.class);
  Object expected=new Object();
  when(this.session.getAttributes()).thenReturn(attributes);
  when(attributes.getAttribute(name)).thenReturn(expected);
  when(this.manager.getSessionManager()).thenReturn(manager);
  when(manager.getBatcher()).thenReturn(batcher);
  when(batcher.resumeBatch(this.batch)).thenReturn(context);
  Object result=this.adapter.getAttribute(name);
  assertSame(expected,result);
  verify(context).close();
}
