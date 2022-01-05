@Test public void setServletRegistrationBeanReplacesValue() throws Exception {
  FilterRegistrationBean bean=new FilterRegistrationBean(this.filter,mockServletRegistation("a"));
  bean.setServletRegistrationBeans(new LinkedHashSet<ServletRegistrationBean>(Arrays.asList(mockServletRegistation("b"))));
  bean.onStartup(this.servletContext);
  verify(this.registration).addMappingForServletNames(FilterRegistrationBean.ASYNC_DISPATCHER_TYPES,false,"b");
}
