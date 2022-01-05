@Test public void should_return_object_when_get_real_object_called_on_non_proxified_entity() throws Exception {
  UserBean realObject=new UserBean();
  UserBean actual=proxifier.getRealObject(realObject);
  assertThat(actual).isSameAs(realObject);
}
