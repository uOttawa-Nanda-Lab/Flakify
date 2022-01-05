@SuppressWarnings("unchecked") @Test public void getLocalContext(){
  Object expected=new Object();
  when(this.localContextFactory.createLocalContext()).thenReturn(expected);
  Object result=this.sso.getLocalContext();
  assertSame(expected,result);
  reset(this.localContextFactory);
  result=this.sso.getLocalContext();
  verifyZeroInteractions(this.localContextFactory);
  assertSame(expected,result);
}
