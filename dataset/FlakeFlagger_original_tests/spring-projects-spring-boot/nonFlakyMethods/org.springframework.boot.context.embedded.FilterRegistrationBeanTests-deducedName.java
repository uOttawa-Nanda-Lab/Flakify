@Test public void deducedName() throws Exception {
  FilterRegistrationBean bean=new FilterRegistrationBean();
  bean.setFilter(this.filter);
  bean.onStartup(this.servletContext);
  verify(this.servletContext).addFilter("mockFilter",this.filter);
}
