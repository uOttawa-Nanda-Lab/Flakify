@Test public void getAuthenticatedSessionAttribute(){
  String name=CachedAuthenticatedSessionHandler.class.getName() + ".AuthenticatedSession";
  this.validate(session -> session.getAttribute(name));
  SessionManager<LocalSessionContext,Batch> manager=mock(SessionManager.class);
  Batcher<Batch> batcher=mock(Batcher.class);
  BatchContext context=mock(BatchContext.class);
  SessionAttributes attributes=mock(SessionAttributes.class);
  Account account=mock(Account.class);
  AuthenticatedSession auth=new AuthenticatedSession(account,HttpServletRequest.FORM_AUTH);
  when(this.manager.getSessionManager()).thenReturn(manager);
  when(manager.getBatcher()).thenReturn(batcher);
  when(batcher.resumeBatch(this.batch)).thenReturn(context);
  when(this.session.getAttributes()).thenReturn(attributes);
  when(attributes.getAttribute(name)).thenReturn(auth);
  AuthenticatedSession result=(AuthenticatedSession)this.adapter.getAttribute(name);
  assertSame(account,result.getAccount());
  assertSame(HttpServletRequest.FORM_AUTH,result.getMechanism());
  verify(context).close();
  reset(context);
  LocalSessionContext localContext=mock(LocalSessionContext.class);
  AuthenticatedSession expected=new AuthenticatedSession(account,HttpServletRequest.BASIC_AUTH);
  when(attributes.getAttribute(name)).thenReturn(null);
  when(this.session.getLocalContext()).thenReturn(localContext);
  when(localContext.getAuthenticatedSession()).thenReturn(expected);
  result=(AuthenticatedSession)this.adapter.getAttribute(name);
  assertSame(expected,result);
  verify(context).close();
}
