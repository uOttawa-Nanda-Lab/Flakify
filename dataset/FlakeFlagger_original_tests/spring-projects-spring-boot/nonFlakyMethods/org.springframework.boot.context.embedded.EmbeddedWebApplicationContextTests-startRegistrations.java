@Test public void startRegistrations() throws Exception {
  addEmbeddedServletContainerFactoryBean();
  this.context.refresh();
  MockEmbeddedServletContainerFactory escf=getEmbeddedServletContainerFactory();
  assertThat(this.context.getServletContext(),equalTo(escf.getServletContext()));
  verify(escf.getServletContext()).setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,this.context);
  assertThat(this.context.getBeanFactory().getRegisteredScope(WebApplicationContext.SCOPE_SESSION),instanceOf(SessionScope.class));
  assertThat(this.context.containsBean(WebApplicationContext.SERVLET_CONTEXT_BEAN_NAME),equalTo(true));
}
