@Test public void testCorethreads() throws Exception {
  ConsumerConfig consumer=new ConsumerConfig();
  consumer.setCorethreads(10);
  assertThat(consumer.getCorethreads(),equalTo(10));
}
