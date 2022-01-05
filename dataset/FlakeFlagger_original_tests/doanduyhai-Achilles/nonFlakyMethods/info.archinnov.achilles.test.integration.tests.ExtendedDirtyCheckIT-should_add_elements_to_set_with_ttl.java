@Test public void should_add_elements_to_set_with_ttl() throws Exception {
  bean.getFollowers().add("Sylvain");
  bean.getFollowers().add("Jonathan");
  manager.update(bean,OptionsBuilder.withTtl(1));
  Thread.sleep(1000);
  manager.refresh(bean);
  assertThat(bean.getFollowers()).containsOnly("George","Paul");
}
