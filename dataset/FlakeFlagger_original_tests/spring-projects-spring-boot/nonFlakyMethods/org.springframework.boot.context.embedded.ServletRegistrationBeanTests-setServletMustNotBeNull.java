@Test public void setServletMustNotBeNull() throws Exception {
  ServletRegistrationBean bean=new ServletRegistrationBean();
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("Servlet must not be null");
  bean.onStartup(this.servletContext);
}
