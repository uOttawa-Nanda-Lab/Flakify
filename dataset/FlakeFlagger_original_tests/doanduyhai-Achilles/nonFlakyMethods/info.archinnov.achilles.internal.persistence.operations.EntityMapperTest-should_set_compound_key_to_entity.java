@Test public void should_set_compound_key_to_entity() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.completeBean(Void.class,String.class).field("name").accessors().type(EMBEDDED_ID).compNames("name").invoker(invoker).build();
  EmbeddedKey embeddedKey=new EmbeddedKey();
  when(cqlRowInvoker.extractCompoundPrimaryKeyFromRow(row,pm,true)).thenReturn(embeddedKey);
  entityMapper.setPropertyToEntity(row,pm,entity);
  verify(invoker).setValueToField(entity,pm.getField(),embeddedKey);
}
