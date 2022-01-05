@Test public void getSessionByIdentifierNotExists(){
  String id="session";
  Batcher<Batch> batcher=mock(Batcher.class);
  Batch batch=mock(Batch.class);
  when(this.manager.getBatcher()).thenReturn(batcher);
  when(this.manager.viewSession(id)).thenReturn(null);
  when(batcher.createBatch()).thenReturn(batch);
  io.undertow.server.session.Session result=this.adapter.getSession(id);
  assertNull(result);
  verify(batch).close();
}
