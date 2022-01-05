@Test public void should_add_elements_to_set_with_timestamp() throws Exception {
  bean.getFollowers().add("Sylvain");
  bean.getFollowers().add("Jonathan");
  manager.update(bean,OptionsBuilder.withTimestamp((System.currentTimeMillis() - 100000) * 1000));
  manager.refresh(bean);
  assertThat(bean.getFollowers()).containsOnly("George","Paul");
}
