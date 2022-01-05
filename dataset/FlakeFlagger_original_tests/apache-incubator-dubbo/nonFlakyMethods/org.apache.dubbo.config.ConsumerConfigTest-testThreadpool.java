@Test public void testThreadpool() throws Exception {
  ConsumerConfig consumer=new ConsumerConfig();
  consumer.setThreadpool("fixed");
  assertThat(consumer.getThreadpool(),equalTo("fixed"));
}
