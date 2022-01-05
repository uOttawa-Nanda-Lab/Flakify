@Test public void addServletRegistrationBeanMustNotBeNull() throws Exception {
  FilterRegistrationBean bean=new FilterRegistrationBean(this.filter);
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("ServletRegistrationBeans must not be null");
  bean.addServletRegistrationBeans((ServletRegistrationBean[])null);
}
