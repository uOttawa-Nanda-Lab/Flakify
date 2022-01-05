@Test public void should_decode_from_components_with_injection_by_setters() throws Exception {
  Long userId=RandomUtils.nextLong();
  String name="name";
  Field userIdField=EmbeddedKey.class.getDeclaredField("userId");
  Field nameField=EmbeddedKey.class.getDeclaredField("name");
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).type(EMBEDDED_ID).compClasses(Long.class,String.class).compFields(userIdField,nameField).invoker(invoker).build();
  when(invoker.instantiate(EmbeddedKey.class)).thenReturn(new EmbeddedKey());
  Object actual=transcoder.decodeFromComponents(pm,Arrays.<Object>asList(userId,name));
  assertThat(actual).isInstanceOf(EmbeddedKey.class);
  EmbeddedKey compound=(EmbeddedKey)actual;
  assertThat(compound.getUserId()).isEqualTo(userId);
  assertThat(compound.getName()).isEqualTo(name);
}
