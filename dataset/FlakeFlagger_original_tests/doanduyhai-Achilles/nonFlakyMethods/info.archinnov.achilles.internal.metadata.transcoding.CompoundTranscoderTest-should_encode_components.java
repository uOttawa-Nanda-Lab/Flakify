@Test public void should_encode_components() throws Exception {
  Long userId=RandomUtils.nextLong();
  String name="name";
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).type(EMBEDDED_ID).compClasses(Long.class,String.class,PropertyType.class).build();
  List<Object> actual=transcoder.encodeToComponents(pm,Arrays.<Object>asList(userId,PropertyType.EMBEDDED_ID,name));
  assertThat(actual).containsExactly(userId,"EMBEDDED_ID",name);
}
