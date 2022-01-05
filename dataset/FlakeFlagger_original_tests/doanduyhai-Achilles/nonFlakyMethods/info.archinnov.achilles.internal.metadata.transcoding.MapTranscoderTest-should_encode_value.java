@Test public void should_encode_value() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.keyValueClass(Integer.class,String.class).type(SIMPLE).build();
  assertThat(transcoder.encode(pm,"value")).isEqualTo("value");
}
