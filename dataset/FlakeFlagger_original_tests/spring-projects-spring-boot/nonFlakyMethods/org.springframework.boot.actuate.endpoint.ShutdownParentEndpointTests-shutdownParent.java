@Test public void shutdownParent() throws Exception {
  this.context=new SpringApplicationBuilder(Empty.class).child(Config.class).web(false).run();
  CountDownLatch latch=this.context.getBean(Config.class).latch;
  assertThat((String)getEndpointBean().invoke().get("message"),startsWith("Shutting down"));
  assertTrue(this.context.isActive());
  assertTrue(latch.await(10,TimeUnit.SECONDS));
}
