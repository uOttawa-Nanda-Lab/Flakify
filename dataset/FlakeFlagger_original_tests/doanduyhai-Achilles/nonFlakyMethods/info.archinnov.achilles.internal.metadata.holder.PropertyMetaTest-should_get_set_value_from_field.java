@Test public void should_get_set_value_from_field() throws Exception {
  CompleteBean entity=new CompleteBean();
  Set<String> followers=Sets.newHashSet("George","Paul");
  entity.setFollowers(followers);
  PropertyMeta pm=PropertyMetaTestBuilder.completeBean(Void.class,String.class).field("followers").accessors().type(SET).invoker(invoker).build();
  when(invoker.<String>getSetValueFromField(entity,pm.getField())).thenReturn(followers);
  assertThat(pm.<String>getSetValueFromField(entity)).containsOnly("George","Paul");
}
