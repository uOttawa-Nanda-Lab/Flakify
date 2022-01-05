@Test public void should_exception_by_default_on_decode_list() throws Exception {
  List<UserBean> list=Arrays.asList(new UserBean());
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(UserBean.class).type(SIMPLE).build();
  exception.expect(AchillesException.class);
  exception.expectMessage("Transcoder cannot decode value '" + list + "' for type '"+ pm.type().name()+ "'");
  transcoder.decode(pm,list);
}
