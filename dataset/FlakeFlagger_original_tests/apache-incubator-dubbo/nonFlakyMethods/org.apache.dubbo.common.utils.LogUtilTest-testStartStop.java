@Test public void testStartStop() throws Exception {
  LogUtil.start();
  assertThat(DubboAppender.available,is(true));
  LogUtil.stop();
  assertThat(DubboAppender.available,is(false));
}
