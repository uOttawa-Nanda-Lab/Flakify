@Test public void disable() throws Exception {
  ServletRegistrationBean bean=new ServletRegistrationBean();
  bean.setServlet(this.servlet);
  bean.setEnabled(false);
  bean.onStartup(this.servletContext);
  verify(this.servletContext,times(0)).addServlet("mockServlet",this.servlet);
}
