@Test public void testQueues() throws Exception {
  ConsumerConfig consumer=new ConsumerConfig();
  consumer.setQueues(5);
  assertThat(consumer.getQueues(),equalTo(5));
}
