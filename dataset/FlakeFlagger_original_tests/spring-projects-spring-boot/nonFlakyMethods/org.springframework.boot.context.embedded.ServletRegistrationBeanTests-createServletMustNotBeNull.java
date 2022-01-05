@Test public void createServletMustNotBeNull() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("Servlet must not be null");
  new ServletRegistrationBean(null);
}
