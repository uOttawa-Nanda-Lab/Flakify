@Test public void setServletNameMustNotBeNull() throws Exception {
  FilterRegistrationBean bean=new FilterRegistrationBean(this.filter);
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("ServletNames must not be null");
  bean.setServletNames(null);
}
