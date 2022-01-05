@Test public void removeNonExistingAttribute(){
  String name="name";
  this.validate(session -> session.removeAttribute(name));
  SessionManager<LocalSessionContext,Batch> manager=mock(SessionManager.class);
  Batcher<Batch> batcher=mock(Batcher.class);
  BatchContext context=mock(BatchContext.class);
  SessionAttributes attributes=mock(SessionAttributes.class);
  SessionListener listener=mock(SessionListener.class);
  SessionListeners listeners=new SessionListeners();
  listeners.addSessionListener(listener);
  when(this.session.getAttributes()).thenReturn(attributes);
  when(attributes.removeAttribute(name)).thenReturn(null);
  when(this.manager.getSessionListeners()).thenReturn(listeners);
  when(this.manager.getSessionManager()).thenReturn(manager);
  when(manager.getBatcher()).thenReturn(batcher);
  when(batcher.resumeBatch(this.batch)).thenReturn(context);
  Object result=this.adapter.removeAttribute(name);
  assertNull(result);
  verify(listener,never()).attributeRemoved(same(this.adapter),same(name),any());
  verify(context).close();
}
