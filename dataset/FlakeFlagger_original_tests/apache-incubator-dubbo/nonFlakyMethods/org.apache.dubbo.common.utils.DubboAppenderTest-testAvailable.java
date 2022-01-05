@Test public void testAvailable() throws Exception {
  assertThat(DubboAppender.available,is(false));
  DubboAppender.doStart();
  assertThat(DubboAppender.available,is(true));
  DubboAppender.doStop();
  assertThat(DubboAppender.available,is(false));
}
