@Test public void should_encode_to_components() throws Exception {
  Long userId=RandomUtils.nextLong();
  String name="name";
  EmbeddedKey compound=new EmbeddedKey(userId,name);
  Field userIdField=EmbeddedKey.class.getDeclaredField("userId");
  Field nameField=EmbeddedKey.class.getDeclaredField("name");
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).type(EMBEDDED_ID).compClasses(Long.class,String.class).compFields(userIdField,nameField).build();
  when(invoker.getValueFromField(compound,userIdField)).thenReturn(userId);
  when(invoker.getValueFromField(compound,nameField)).thenReturn(name);
  List<Object> actual=transcoder.encodeToComponents(pm,compound);
  assertThat(actual).containsExactly(userId,name);
}
