@Test public void should_add_elements_to_list_with_timestamp() throws Exception {
  bean.getFriends().add("qux");
  bean.getFriends().add("tux");
  manager.update(bean,OptionsBuilder.withTimestamp((System.currentTimeMillis() - 100000) * 1000));
  manager.refresh(bean);
  assertThat(bean.getFriends()).containsExactly("foo","bar");
}
