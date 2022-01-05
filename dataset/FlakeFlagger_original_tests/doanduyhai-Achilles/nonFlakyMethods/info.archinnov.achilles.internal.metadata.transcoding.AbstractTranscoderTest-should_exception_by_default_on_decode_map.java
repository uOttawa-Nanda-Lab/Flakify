@Test public void should_exception_by_default_on_decode_map() throws Exception {
  Map<Integer,UserBean> map=ImmutableMap.of(1,new UserBean());
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(UserBean.class).type(SIMPLE).build();
  exception.expect(AchillesException.class);
  exception.expectMessage("Transcoder cannot decode value '" + map + "' for type '"+ pm.type().name()+ "'");
  transcoder.decode(pm,map);
}
