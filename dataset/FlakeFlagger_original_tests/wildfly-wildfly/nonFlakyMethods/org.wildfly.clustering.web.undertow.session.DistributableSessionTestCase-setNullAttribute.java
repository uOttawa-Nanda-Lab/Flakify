@Test public void setNullAttribute(){
  String name="name";
  Object value=null;
  this.validate(session -> session.setAttribute(name,value));
  SessionManager<LocalSessionContext,Batch> manager=mock(SessionManager.class);
  Batcher<Batch> batcher=mock(Batcher.class);
  BatchContext context=mock(BatchContext.class);
  SessionAttributes attributes=mock(SessionAttributes.class);
  SessionListener listener=mock(SessionListener.class);
  SessionListeners listeners=new SessionListeners();
  listeners.addSessionListener(listener);
  Object expected=new Object();
  when(this.session.getAttributes()).thenReturn(attributes);
  when(attributes.removeAttribute(name)).thenReturn(expected);
  when(this.manager.getSessionListeners()).thenReturn(listeners);
  when(this.manager.getSessionManager()).thenReturn(manager);
  when(manager.getBatcher()).thenReturn(batcher);
  when(batcher.resumeBatch(this.batch)).thenReturn(context);
  Object result=this.adapter.setAttribute(name,value);
  assertSame(expected,result);
  verify(listener,never()).attributeAdded(this.adapter,name,value);
  verify(listener,never()).attributeUpdated(same(this.adapter),same(name),same(value),any());
  verify(listener).attributeRemoved(this.adapter,name,expected);
  verify(context).close();
}
