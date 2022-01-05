@Test public void startBlocksUntilReadyToServe() throws Exception {
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  final Date[] date=new Date[1];
  this.container=factory.getEmbeddedServletContainer(new ServletContextInitializer(){
    @Override public void onStartup(    ServletContext servletContext) throws ServletException {
      try {
        Thread.sleep(500);
        date[0]=new Date();
      }
 catch (      InterruptedException ex) {
        throw new ServletException(ex);
      }
    }
  }
);
  this.container.start();
  assertThat(date[0],notNullValue());
}
