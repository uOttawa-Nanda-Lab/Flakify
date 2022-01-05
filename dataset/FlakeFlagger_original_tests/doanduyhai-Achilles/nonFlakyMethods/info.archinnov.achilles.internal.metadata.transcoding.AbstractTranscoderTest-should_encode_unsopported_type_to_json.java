@Test public void should_encode_unsopported_type_to_json() throws Exception {
  UserBean bean=new UserBean();
  when(objectMapper.writeValueAsString(bean)).thenReturn("json_bean");
  Object actual=transcoder.encodeInternal(UserBean.class,bean);
  assertThat(actual).isEqualTo("json_bean");
}
