@Test public void addRequestListener() throws ServletException {
  ServletRequestListener listener=mock(ServletRequestListener.class);
  ServletContext context=mock(ServletContext.class);
  ServletRequest request=mock(ServletRequest.class);
  ApplicationListeners listeners=new ApplicationListeners(Collections.<ManagedListener>emptyList(),context);
  ArgumentCaptor<ServletRequestEvent> event=ArgumentCaptor.forClass(ServletRequestEvent.class);
  when(this.deployment.getApplicationListeners()).thenReturn(listeners);
  this.context.addRequestListener(listener);
  listeners.start();
  listeners.requestInitialized(request);
  verify(listener).requestInitialized(event.capture());
  assertSame(request,event.getValue().getServletRequest());
  assertSame(context,event.getValue().getServletContext());
  event=ArgumentCaptor.forClass(ServletRequestEvent.class);
  listeners.requestDestroyed(request);
  verify(listener).requestDestroyed(event.capture());
  assertSame(request,event.getValue().getServletRequest());
  assertSame(context,event.getValue().getServletContext());
}
