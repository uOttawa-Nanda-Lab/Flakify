@Test public void should_exception_by_default_on_decode_key() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(UserBean.class).type(SIMPLE).build();
  exception.expect(AchillesException.class);
  exception.expectMessage("Transcoder cannot decode key 'null' for type '" + pm.type().name() + "'");
  transcoder.decodeKey(pm,null);
}
