@Test public void testClient() throws Exception {
  ConsumerConfig consumer=new ConsumerConfig();
  consumer.setClient("client");
  assertThat(consumer.getClient(),equalTo("client"));
}
