@Test public void should_decode_unsopported_type_to_json() throws Exception {
  UserBean bean=new UserBean();
  when(objectMapper.readValue("json_bean",UserBean.class)).thenReturn(bean);
  Object actual=transcoder.decodeInternal(UserBean.class,"json_bean");
  assertThat(actual).isEqualTo(bean);
}
