@Test public void should_exception_by_default_on_encode_set() throws Exception {
  Set<UserBean> set=Sets.newHashSet(new UserBean());
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(UserBean.class).type(SIMPLE).build();
  exception.expect(AchillesException.class);
  exception.expectMessage("Transcoder cannot encode value '" + set + "' for type '"+ pm.type().name()+ "'");
  transcoder.encode(pm,set);
}
