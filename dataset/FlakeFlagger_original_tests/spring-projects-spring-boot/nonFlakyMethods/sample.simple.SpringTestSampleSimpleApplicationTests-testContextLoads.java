@Test public void testContextLoads() throws Exception {
  assertNotNull(this.ctx);
  assertTrue(this.ctx.containsBean("helloWorldService"));
  assertTrue(this.ctx.containsBean("sampleSimpleApplication"));
}
