@Test public void should_not_dirty_check_set_on_iterator_remove() throws Exception {
  final Iterator<String> iterator=bean.getFollowers().iterator();
  iterator.next();
  iterator.remove();
  manager.update(bean);
  Row row=session.execute("select followers from CompleteBean where id=" + bean.getId()).one();
  Set<String> friends=row.getSet("followers",String.class);
  assertThat(friends).containsOnly("Paul","George");
}
