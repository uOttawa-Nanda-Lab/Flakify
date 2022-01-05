@Test public void should_set_value_to_field() throws Exception {
  CompleteBean entity=new CompleteBean();
  PropertyMeta pm=PropertyMetaTestBuilder.completeBean(Void.class,String.class).field("name").accessors().type(SIMPLE).invoker(invoker).build();
  pm.setValueToField(entity,"name");
  verify(invoker).setValueToField(entity,pm.getField(),"name");
}
