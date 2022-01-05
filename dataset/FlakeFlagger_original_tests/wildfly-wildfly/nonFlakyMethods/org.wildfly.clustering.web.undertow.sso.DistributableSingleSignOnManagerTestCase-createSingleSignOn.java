@Test public void createSingleSignOn(){
  String id="sso";
  Batcher<Batch> batcher=mock(Batcher.class);
  Batch batch=mock(Batch.class);
  Account account=mock(Account.class);
  String mechanism=HttpServletRequest.BASIC_AUTH;
  SSO<AuthenticatedSession,String,String,Void> sso=mock(SSO.class);
  ArgumentCaptor<AuthenticatedSession> authenticationCaptor=ArgumentCaptor.forClass(AuthenticatedSession.class);
  when(this.manager.createIdentifier()).thenReturn(id);
  when(this.manager.getBatcher()).thenReturn(batcher);
  when(batcher.createBatch()).thenReturn(batch);
  when(this.manager.createSSO(same(id),authenticationCaptor.capture())).thenReturn(sso);
  SingleSignOn result=this.subject.createSingleSignOn(account,mechanism);
  verify(batcher).suspendBatch();
  assertNotNull(result);
  AuthenticatedSession capturedAuthentication=authenticationCaptor.getValue();
  assertNotNull(capturedAuthentication);
  assertSame(capturedAuthentication.getAccount(),account);
  assertSame(capturedAuthentication.getMechanism(),mechanism);
}
