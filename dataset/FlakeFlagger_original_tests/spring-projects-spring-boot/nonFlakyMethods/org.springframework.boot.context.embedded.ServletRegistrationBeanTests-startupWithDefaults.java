@Test public void startupWithDefaults() throws Exception {
  ServletRegistrationBean bean=new ServletRegistrationBean(this.servlet);
  bean.onStartup(this.servletContext);
  verify(this.servletContext).addServlet("mockServlet",this.servlet);
  verify(this.registration).setAsyncSupported(true);
  verify(this.registration).addMapping("/*");
}
