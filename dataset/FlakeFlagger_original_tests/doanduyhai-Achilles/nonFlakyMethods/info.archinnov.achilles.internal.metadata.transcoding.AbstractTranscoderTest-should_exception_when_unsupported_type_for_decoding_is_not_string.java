@Test public void should_exception_when_unsupported_type_for_decoding_is_not_string() throws Exception {
  UserBean bean=new UserBean();
  exception.expect(AchillesException.class);
  exception.expectMessage("Error while decoding value '" + bean + "' to type '"+ UserBean.class.getCanonicalName()+ "'");
  transcoder.decodeInternal(UserBean.class,bean);
}
