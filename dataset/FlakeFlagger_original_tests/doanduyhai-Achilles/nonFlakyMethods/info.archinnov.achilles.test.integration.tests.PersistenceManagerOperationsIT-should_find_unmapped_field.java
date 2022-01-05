@Test public void should_find_unmapped_field() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("DuyHai").label("label").age(35L).addFriends("foo","bar").addFollowers("George","Paul").addPreference(1,"FR").addPreference(2,"Paris").addPreference(3,"75014").buid();
  entity=manager.persist(entity);
  assertThat(entity.getLabel()).isEqualTo("label");
}
