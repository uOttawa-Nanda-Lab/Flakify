@Test public void getMechanismName(){
  BatchContext context=mock(BatchContext.class);
  Account account=mock(Account.class);
  String mechanism=HttpServletRequest.CLIENT_CERT_AUTH;
  AuthenticatedSession authentication=new AuthenticatedSession(account,mechanism);
  when(this.batcher.resumeBatch(this.batch)).thenReturn(context);
  when(this.sso.getAuthentication()).thenReturn(authentication);
  String result=this.subject.getMechanismName();
  assertEquals(HttpServletRequest.CLIENT_CERT_AUTH,result);
  verifyZeroInteractions(this.batch);
  verify(context).close();
}
