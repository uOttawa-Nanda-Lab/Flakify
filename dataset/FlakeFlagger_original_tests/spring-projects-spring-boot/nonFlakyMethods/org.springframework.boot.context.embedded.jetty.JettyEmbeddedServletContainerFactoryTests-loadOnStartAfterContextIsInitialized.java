@Test public void loadOnStartAfterContextIsInitialized() throws Exception {
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  final InitCountingServlet servlet=new InitCountingServlet();
  this.container=factory.getEmbeddedServletContainer(new ServletContextInitializer(){
    @Override public void onStartup(    ServletContext servletContext) throws ServletException {
      servletContext.addServlet("test",servlet).setLoadOnStartup(1);
    }
  }
);
  assertThat(servlet.getInitCount(),equalTo(0));
  this.container.start();
  assertThat(servlet.getInitCount(),equalTo(1));
}
