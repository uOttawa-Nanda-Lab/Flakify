@Test public void getAccount(){
  BatchContext context=mock(BatchContext.class);
  Account account=mock(Account.class);
  String mechanism=HttpServletRequest.BASIC_AUTH;
  AuthenticatedSession authentication=new AuthenticatedSession(account,mechanism);
  when(this.batcher.resumeBatch(this.batch)).thenReturn(context);
  when(this.sso.getAuthentication()).thenReturn(authentication);
  Account result=this.subject.getAccount();
  assertSame(account,result);
  verifyZeroInteractions(this.batch);
  verify(context).close();
}
