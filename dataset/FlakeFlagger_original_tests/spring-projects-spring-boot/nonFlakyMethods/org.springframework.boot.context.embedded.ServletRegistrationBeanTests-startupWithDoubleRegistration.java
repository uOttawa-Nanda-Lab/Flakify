@Test public void startupWithDoubleRegistration() throws Exception {
  ServletRegistrationBean bean=new ServletRegistrationBean(this.servlet);
  given(this.servletContext.addServlet(anyString(),(Servlet)anyObject())).willReturn(null);
  bean.onStartup(this.servletContext);
  verify(this.servletContext).addServlet("mockServlet",this.servlet);
  verify(this.registration,never()).setAsyncSupported(true);
}
