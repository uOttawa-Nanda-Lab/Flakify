@Test public void modifyInitParameters() throws Exception {
  ServletRegistrationBean bean=new ServletRegistrationBean(this.servlet,"/a","/b");
  bean.addInitParameter("a","b");
  bean.getInitParameters().put("a","c");
  bean.onStartup(this.servletContext);
  verify(this.registration).setInitParameters(Collections.singletonMap("a","c"));
}
