@Test public void should_get_map_value_from_field() throws Exception {
  CompleteBean entity=new CompleteBean();
  Map<Integer,String> preferences=ImmutableMap.of(1,"FR",2,"Paris");
  entity.setPreferences(preferences);
  PropertyMeta pm=PropertyMetaTestBuilder.completeBean(Integer.class,String.class).field("preferences").accessors().type(MAP).invoker(invoker).build();
  when(invoker.<Integer,String>getMapValueFromField(entity,pm.getField())).thenReturn(preferences);
  Map<Integer,String> actual=pm.getMapValueFromField(entity);
  assertThat(actual).containsKey(1).containsKey(2).containsValue("FR").containsValue("Paris");
}
