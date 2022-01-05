@Test public void addMappingMustNotBeNull() throws Exception {
  ServletRegistrationBean bean=new ServletRegistrationBean(this.servlet);
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("UrlMappings must not be null");
  bean.addUrlMappings((String[])null);
}
