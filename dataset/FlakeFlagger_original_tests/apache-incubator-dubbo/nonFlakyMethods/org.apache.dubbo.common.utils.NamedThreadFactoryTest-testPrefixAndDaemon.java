@Test public void testPrefixAndDaemon() throws Exception {
  NamedThreadFactory factory=new NamedThreadFactory("prefix",true);
  Thread t=factory.newThread(Mockito.mock(Runnable.class));
  assertThat(t.getName(),allOf(containsString("prefix-"),containsString("-thread-")));
  assertTrue(t.isDaemon());
}
