@Test public void addSessionListener() throws ServletException {
  HttpSessionListener listener=mock(HttpSessionListener.class);
  ServletContext context=mock(ServletContext.class);
  HttpSession session=mock(HttpSession.class);
  ApplicationListeners listeners=new ApplicationListeners(Collections.<ManagedListener>emptyList(),context);
  ArgumentCaptor<HttpSessionEvent> event=ArgumentCaptor.forClass(HttpSessionEvent.class);
  when(this.deployment.getApplicationListeners()).thenReturn(listeners);
  this.context.addSessionListener(listener);
  listeners.start();
  listeners.sessionCreated(session);
  verify(listener).sessionCreated(event.capture());
  assertSame(session,event.getValue().getSession());
  event=ArgumentCaptor.forClass(HttpSessionEvent.class);
  listeners.sessionDestroyed(session);
  verify(listener).sessionDestroyed(event.capture());
  assertSame(session,event.getValue().getSession());
}
