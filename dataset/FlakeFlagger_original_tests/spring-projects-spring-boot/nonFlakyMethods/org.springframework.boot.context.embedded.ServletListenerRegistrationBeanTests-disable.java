@Test public void disable() throws Exception {
  ServletListenerRegistrationBean<ServletContextListener> bean=new ServletListenerRegistrationBean<ServletContextListener>(this.listener);
  bean.setEnabled(false);
  bean.onStartup(this.servletContext);
  verify(this.servletContext,times(0)).addListener(any(ServletContextListener.class));
}
