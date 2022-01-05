@Test public void createServletRegistrationBeanMustNotBeNull() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("ServletRegistrationBeans must not be null");
  new FilterRegistrationBean(this.filter,(ServletRegistrationBean[])null);
}
