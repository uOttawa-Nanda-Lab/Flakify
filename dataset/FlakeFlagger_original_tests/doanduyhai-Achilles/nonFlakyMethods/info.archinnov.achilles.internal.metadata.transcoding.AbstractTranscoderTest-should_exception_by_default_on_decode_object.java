@Test public void should_exception_by_default_on_decode_object() throws Exception {
  UserBean bean=new UserBean();
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(UserBean.class).type(SIMPLE).build();
  exception.expect(AchillesException.class);
  exception.expectMessage("Transcoder cannot decode value '" + bean + "' for type '"+ pm.type().name()+ "'");
  transcoder.decode(pm,bean);
}
