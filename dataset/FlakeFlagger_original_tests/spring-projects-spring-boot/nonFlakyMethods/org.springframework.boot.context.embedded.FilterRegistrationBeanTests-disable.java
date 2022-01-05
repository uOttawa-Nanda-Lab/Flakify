@Test public void disable() throws Exception {
  FilterRegistrationBean bean=new FilterRegistrationBean();
  bean.setFilter(this.filter);
  bean.setEnabled(false);
  bean.onStartup(this.servletContext);
  verify(this.servletContext,times(0)).addFilter("mockFilter",this.filter);
}
