@Test public void invalidate(){
  HttpServerExchange exchange=new HttpServerExchange(null);
  this.validate(exchange,session -> session.invalidate(exchange));
  SessionManager<LocalSessionContext,Batch> manager=mock(SessionManager.class);
  Batcher<Batch> batcher=mock(Batcher.class);
  BatchContext context=mock(BatchContext.class);
  SessionListener listener=mock(SessionListener.class);
  SessionListeners listeners=new SessionListeners();
  listeners.addSessionListener(listener);
  String sessionId="session";
  when(this.manager.getSessionListeners()).thenReturn(listeners);
  when(this.session.getId()).thenReturn(sessionId);
  when(this.manager.getSessionManager()).thenReturn(manager);
  when(manager.getBatcher()).thenReturn(batcher);
  when(batcher.resumeBatch(this.batch)).thenReturn(context);
  this.adapter.invalidate(exchange);
  verify(this.session).invalidate();
  verify(this.config).clearSession(exchange,sessionId);
  verify(listener).sessionDestroyed(this.adapter,exchange,SessionDestroyedReason.INVALIDATED);
  verify(this.batch).close();
  verify(context).close();
  verify(this.closeTask).accept(exchange);
}
