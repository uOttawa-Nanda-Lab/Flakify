@Test public void setMappingReplacesValue() throws Exception {
  ServletRegistrationBean bean=new ServletRegistrationBean(this.servlet,"/a","/b");
  bean.setUrlMappings(new LinkedHashSet<String>(Arrays.asList("/c","/d")));
  bean.onStartup(this.servletContext);
  verify(this.registration).addMapping("/c","/d");
}
