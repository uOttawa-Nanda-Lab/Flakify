@SuppressWarnings("unchecked") @Test public void getLocalContext(){
  Object expected=new Object();
  when(this.localContextFactory.createLocalContext()).thenReturn(expected);
  Object result=this.session.getLocalContext();
  assertSame(expected,result);
  reset(this.localContextFactory);
  result=this.session.getLocalContext();
  verifyZeroInteractions(this.localContextFactory);
  assertSame(expected,result);
}
