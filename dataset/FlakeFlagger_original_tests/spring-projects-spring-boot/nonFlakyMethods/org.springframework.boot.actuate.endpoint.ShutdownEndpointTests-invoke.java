@Test public void invoke() throws Exception {
  CountDownLatch latch=this.context.getBean(Config.class).latch;
  assertThat((String)getEndpointBean().invoke().get("message"),startsWith("Shutting down"));
  assertTrue(this.context.isActive());
  assertTrue(latch.await(10,TimeUnit.SECONDS));
}
