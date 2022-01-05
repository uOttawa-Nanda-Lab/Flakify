@Test public void should_add_elements_to_list_with_ttl() throws Exception {
  bean.getFriends().add("qux");
  bean.getFriends().add("tux");
  manager.update(bean,OptionsBuilder.withTtl(1));
  Thread.sleep(1000);
  manager.refresh(bean);
  assertThat(bean.getFriends()).containsExactly("foo","bar");
}
