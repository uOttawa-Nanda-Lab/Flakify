@Test public void findSingleSignOnNotExists(){
  String id="sso";
  Batcher<Batch> batcher=mock(Batcher.class);
  Batch batch=mock(Batch.class);
  when(this.manager.getBatcher()).thenReturn(batcher);
  when(batcher.createBatch()).thenReturn(batch);
  when(this.manager.findSSO(id)).thenReturn(null);
  SingleSignOn result=this.subject.findSingleSignOn(id);
  assertNull(result);
  verify(batch).close();
  verify(batcher,never()).suspendBatch();
}
