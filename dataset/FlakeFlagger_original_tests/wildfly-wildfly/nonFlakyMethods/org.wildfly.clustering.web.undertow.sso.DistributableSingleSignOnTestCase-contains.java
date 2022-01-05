@Test public void contains(){
  String deployment="deployment";
  BatchContext context=mock(BatchContext.class);
  Session session=mock(Session.class);
  SessionManager manager=mock(SessionManager.class);
  Sessions<String,String> sessions=mock(Sessions.class);
  when(this.batcher.resumeBatch(this.batch)).thenReturn(context);
  when(session.getSessionManager()).thenReturn(manager);
  when(manager.getDeploymentName()).thenReturn(deployment);
  when(this.sso.getSessions()).thenReturn(sessions);
  when(sessions.getDeployments()).thenReturn(Collections.<String>emptySet());
  boolean result=this.subject.contains(session);
  assertFalse(result);
  verifyZeroInteractions(this.batch);
  verify(context).close();
  reset(context);
  when(sessions.getDeployments()).thenReturn(Collections.singleton(deployment));
  result=this.subject.contains(session);
  assertTrue(result);
  verifyZeroInteractions(this.batch);
  verify(context).close();
}
