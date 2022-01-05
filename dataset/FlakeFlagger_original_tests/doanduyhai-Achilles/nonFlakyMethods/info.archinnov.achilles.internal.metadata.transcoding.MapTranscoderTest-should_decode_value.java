@Test public void should_decode_value() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.keyValueClass(Integer.class,String.class).type(SIMPLE).build();
  assertThat(transcoder.decode(pm,"value")).isEqualTo("value");
}
