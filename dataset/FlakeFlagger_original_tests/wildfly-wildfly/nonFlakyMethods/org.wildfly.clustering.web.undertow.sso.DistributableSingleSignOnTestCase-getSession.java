@Test public void getSession(){
  String deployment="deployment";
  String sessionId="session";
  BatchContext context=mock(BatchContext.class);
  SessionManager manager=mock(SessionManager.class);
  Sessions<String,String> sessions=mock(Sessions.class);
  when(this.batcher.resumeBatch(this.batch)).thenReturn(context);
  when(manager.getDeploymentName()).thenReturn(deployment);
  when(this.sso.getSessions()).thenReturn(sessions);
  when(sessions.getSession(deployment)).thenReturn(sessionId);
  Session result=this.subject.getSession(manager);
  assertSame(sessionId,result.getId());
  assertSame(manager,result.getSessionManager());
  verifyZeroInteractions(this.batch);
  verify(context).close();
}
