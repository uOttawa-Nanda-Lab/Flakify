@Test public void wrappedHandlers() throws Exception {
  JettyEmbeddedServletContainerFactory factory=getFactory();
  factory.setServerCustomizers(Arrays.asList(new JettyServerCustomizer(){
    @Override public void customize(    Server server){
      Handler handler=server.getHandler();
      HandlerWrapper wrapper=new HandlerWrapper();
      wrapper.setHandler(handler);
      HandlerCollection collection=new HandlerCollection();
      collection.addHandler(wrapper);
      server.setHandler(collection);
    }
  }
));
  this.container=factory.getEmbeddedServletContainer(exampleServletRegistration());
  this.container.start();
  assertThat(getResponse(getLocalUrl("/hello")),equalTo("Hello World"));
}
