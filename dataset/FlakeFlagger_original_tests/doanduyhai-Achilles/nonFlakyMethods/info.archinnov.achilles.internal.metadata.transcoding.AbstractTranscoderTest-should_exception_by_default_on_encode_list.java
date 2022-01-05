@Test public void should_exception_by_default_on_encode_list() throws Exception {
  List<UserBean> list=Arrays.asList(new UserBean());
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(UserBean.class).type(SIMPLE).build();
  exception.expect(AchillesException.class);
  exception.expectMessage("Transcoder cannot encode value '" + list + "' for type '"+ pm.type().name()+ "'");
  transcoder.encode(pm,list);
}
