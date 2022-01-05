@Test public void specificName() throws Exception {
  ServletRegistrationBean bean=new ServletRegistrationBean();
  bean.setName("specificName");
  bean.setServlet(this.servlet);
  bean.onStartup(this.servletContext);
  verify(this.servletContext).addServlet("specificName",this.servlet);
}
