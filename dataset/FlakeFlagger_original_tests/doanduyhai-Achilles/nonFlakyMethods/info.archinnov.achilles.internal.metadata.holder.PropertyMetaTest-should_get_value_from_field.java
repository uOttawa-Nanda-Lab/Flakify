@Test public void should_get_value_from_field() throws Exception {
  CompleteBean entity=new CompleteBean();
  entity.setName("name");
  PropertyMeta pm=PropertyMetaTestBuilder.completeBean(Void.class,String.class).field("name").accessors().type(SIMPLE).invoker(invoker).build();
  when(invoker.getValueFromField(entity,pm.getField())).thenReturn("name");
  assertThat(pm.getValueFromField(entity)).isEqualTo("name");
}
