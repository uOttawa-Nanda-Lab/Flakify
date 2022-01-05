@Test public void invoke() throws Exception {
  List<ThreadInfo> threadInfo=getEndpointBean().invoke();
  assertThat(threadInfo.size(),greaterThan(0));
}
