@Test public void remove(){
  String deployment="deployment";
  BatchContext context=mock(BatchContext.class);
  Session session=mock(Session.class);
  SessionManager manager=mock(SessionManager.class);
  Sessions<String,String> sessions=mock(Sessions.class);
  when(this.batcher.resumeBatch(this.batch)).thenReturn(context);
  when(session.getSessionManager()).thenReturn(manager);
  when(manager.getDeploymentName()).thenReturn(deployment);
  when(this.sso.getSessions()).thenReturn(sessions);
  this.subject.remove(session);
  verify(sessions).removeSession(deployment);
  verifyZeroInteractions(this.batch);
  verify(context).close();
}
