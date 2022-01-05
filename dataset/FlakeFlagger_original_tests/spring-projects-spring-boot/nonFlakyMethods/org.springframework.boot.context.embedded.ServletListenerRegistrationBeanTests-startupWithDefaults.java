@Test public void startupWithDefaults() throws Exception {
  ServletListenerRegistrationBean<ServletContextListener> bean=new ServletListenerRegistrationBean<ServletContextListener>(this.listener);
  bean.onStartup(this.servletContext);
  verify(this.servletContext).addListener(this.listener);
}
