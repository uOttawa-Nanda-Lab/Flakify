@Test public void isStarted() throws ServletException {
  ServletContext context=mock(ServletContext.class);
  ApplicationListeners listeners=new ApplicationListeners(Collections.<ManagedListener>emptyList(),context);
  when(this.deployment.getApplicationListeners()).thenReturn(listeners);
  assertFalse(this.context.isStarted());
  listeners.start();
  assertTrue(this.context.isStarted());
  listeners.stop();
  assertFalse(this.context.isStarted());
}
