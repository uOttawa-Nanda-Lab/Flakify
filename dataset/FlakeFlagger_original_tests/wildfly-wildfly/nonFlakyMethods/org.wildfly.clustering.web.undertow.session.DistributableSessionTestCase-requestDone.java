@Test public void requestDone(){
  SessionManager<LocalSessionContext,Batch> manager=mock(SessionManager.class);
  Batcher<Batch> batcher=mock(Batcher.class);
  BatchContext context=mock(BatchContext.class);
  HttpServerExchange exchange=new HttpServerExchange(null);
  when(this.session.isValid()).thenReturn(true);
  when(this.manager.getSessionManager()).thenReturn(manager);
  when(manager.getBatcher()).thenReturn(batcher);
  when(batcher.resumeBatch(this.batch)).thenReturn(context);
  this.adapter.requestDone(exchange);
  verify(this.session).close();
  verify(this.batch).close();
  verify(context).close();
  verify(this.closeTask).accept(exchange);
  reset(this.batch,this.session,context,this.closeTask);
  when(this.session.isValid()).thenReturn(false);
  this.adapter.requestDone(exchange);
  verify(this.session,never()).close();
  verify(this.batch,never()).close();
  verify(context,never()).close();
  verify(this.closeTask).accept(exchange);
}
