@Test public void testNewThread() throws Exception {
  NamedThreadFactory factory=new NamedThreadFactory();
  Thread t=factory.newThread(Mockito.mock(Runnable.class));
  assertThat(t.getName(),allOf(containsString("pool-"),containsString("-thread-")));
  assertFalse(t.isDaemon());
  assertSame(t.getThreadGroup(),Thread.currentThread().getThreadGroup());
}
