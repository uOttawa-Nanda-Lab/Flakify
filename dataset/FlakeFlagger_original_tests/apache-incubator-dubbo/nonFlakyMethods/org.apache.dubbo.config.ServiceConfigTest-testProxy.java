@Test public void testProxy() throws Exception {
  service2.export();
  assertThat(service2.getExportedUrls(),hasSize(1));
  assertEquals(2,TestProxyFactory.count);
}
