@Test public void should_exception_when_trying_to_modify_primary_key() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("Jonathan").age(40L).addFriends("bob","alice").addFollowers("Billy","Stephen","Jacky").addPreference(1,"US").addPreference(2,"New York").buid();
  entity=manager.persist(entity);
  exception.expect(IllegalAccessException.class);
  exception.expectMessage("Cannot change primary key value for existing entity");
  entity.setId(RandomUtils.nextLong());
}
