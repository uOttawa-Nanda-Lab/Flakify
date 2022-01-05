@Test public void modifyInitParameters() throws Exception {
  FilterRegistrationBean bean=new FilterRegistrationBean(this.filter);
  bean.addInitParameter("a","b");
  bean.getInitParameters().put("a","c");
  bean.onStartup(this.servletContext);
  verify(this.registration).setInitParameters(Collections.singletonMap("a","c"));
}
