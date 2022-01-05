@Test public void testThreads() throws Exception {
  ConsumerConfig consumer=new ConsumerConfig();
  consumer.setThreads(20);
  assertThat(consumer.getThreads(),equalTo(20));
}
