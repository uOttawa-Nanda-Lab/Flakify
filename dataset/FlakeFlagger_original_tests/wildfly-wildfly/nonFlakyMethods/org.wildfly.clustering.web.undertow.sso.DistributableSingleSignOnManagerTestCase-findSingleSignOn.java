@Test public void findSingleSignOn(){
  String id="sso";
  Batcher<Batch> batcher=mock(Batcher.class);
  Batch batch=mock(Batch.class);
  SSO<AuthenticatedSession,String,String,Void> sso=mock(SSO.class);
  when(this.manager.getBatcher()).thenReturn(batcher);
  when(batcher.createBatch()).thenReturn(batch);
  when(this.manager.findSSO(id)).thenReturn(sso);
  SingleSignOn result=this.subject.findSingleSignOn(id);
  assertNotNull(result);
  verify(batcher).suspendBatch();
}
