@Test public void should_remove_property_after_merge() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("Jonathan").age(40L).addFriends("bob","alice").addFollowers("Billy","Stephen","Jacky").addPreference(1,"US").addPreference(2,"New York").buid();
  manager.persist(entity);
  CompleteBean found=manager.find(CompleteBean.class,entity.getId());
  found.setName(null);
  found.setFriends(null);
  found.setFollowers(null);
  found.setPreferences(null);
  manager.update(found);
  found=manager.find(CompleteBean.class,entity.getId());
  assertThat(found.getName()).isNull();
  assertThat(found.getFriends()).isNotNull().isEmpty();
  assertThat(found.getFollowers()).isNull();
  assertThat(found.getPreferences()).isNull();
}
