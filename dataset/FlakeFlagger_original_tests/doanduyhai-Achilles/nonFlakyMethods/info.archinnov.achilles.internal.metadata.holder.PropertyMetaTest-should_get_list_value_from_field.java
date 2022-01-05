@Test public void should_get_list_value_from_field() throws Exception {
  CompleteBean entity=new CompleteBean();
  List<String> friends=Arrays.asList("foo","bar");
  entity.setFriends(friends);
  PropertyMeta pm=PropertyMetaTestBuilder.completeBean(Void.class,String.class).field("friends").accessors().type(LIST).invoker(invoker).build();
  when(invoker.<String>getListValueFromField(entity,pm.getField())).thenReturn(friends);
  assertThat(pm.<String>getListValueFromField(entity)).containsExactly("foo","bar");
}
