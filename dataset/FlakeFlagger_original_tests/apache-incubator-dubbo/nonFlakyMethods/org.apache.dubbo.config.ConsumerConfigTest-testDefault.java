@Test public void testDefault() throws Exception {
  ConsumerConfig consumer=new ConsumerConfig();
  consumer.setDefault(true);
  assertThat(consumer.isDefault(),is(true));
}
