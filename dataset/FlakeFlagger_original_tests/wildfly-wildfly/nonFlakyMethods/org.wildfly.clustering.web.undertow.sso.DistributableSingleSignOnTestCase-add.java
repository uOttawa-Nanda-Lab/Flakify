@Test public void add(){
  String deployment="deployment";
  String sessionId="session";
  BatchContext context=mock(BatchContext.class);
  Session session=mock(Session.class);
  SessionManager manager=mock(SessionManager.class);
  Sessions<String,String> sessions=mock(Sessions.class);
  when(this.batcher.resumeBatch(this.batch)).thenReturn(context);
  when(session.getId()).thenReturn(sessionId);
  when(session.getSessionManager()).thenReturn(manager);
  when(manager.getDeploymentName()).thenReturn(deployment);
  when(this.sso.getSessions()).thenReturn(sessions);
  this.subject.add(session);
  verify(sessions).addSession(deployment,sessionId);
  verifyZeroInteractions(this.batch);
  verify(context).close();
}
