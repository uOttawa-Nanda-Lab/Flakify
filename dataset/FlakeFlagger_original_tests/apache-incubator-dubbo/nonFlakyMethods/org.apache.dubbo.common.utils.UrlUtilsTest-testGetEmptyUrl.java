@Test public void testGetEmptyUrl() throws Exception {
  URL url=UrlUtils.getEmptyUrl("dubbo/a.b.c.Foo:1.0.0","test");
  assertThat(url.toFullString(),equalTo("empty://0.0.0.0/a.b.c.Foo?category=test&group=dubbo&version=1.0.0"));
}
