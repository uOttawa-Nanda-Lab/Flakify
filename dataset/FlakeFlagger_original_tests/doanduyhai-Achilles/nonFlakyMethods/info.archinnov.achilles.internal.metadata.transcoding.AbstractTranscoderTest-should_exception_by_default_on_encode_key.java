@Test public void should_exception_by_default_on_encode_key() throws Exception {
  UserBean bean=new UserBean();
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(UserBean.class).type(SIMPLE).build();
  exception.expect(AchillesException.class);
  exception.expectMessage("Transcoder cannot encode key '" + bean + "' for type '"+ pm.type().name()+ "'");
  transcoder.encodeKey(pm,bean);
}
