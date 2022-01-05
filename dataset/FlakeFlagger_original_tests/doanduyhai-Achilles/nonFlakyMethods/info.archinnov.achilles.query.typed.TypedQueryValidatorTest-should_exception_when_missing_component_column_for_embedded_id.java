@Test public void should_exception_when_missing_component_column_for_embedded_id() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,EmbeddedKey.class).field("id").type(PropertyType.EMBEDDED_ID).compNames("id","name").build();
  EntityMeta meta=new EntityMeta();
  meta.setAllMetasExceptIdAndCounters(new ArrayList<PropertyMeta>());
  meta.setTableName("table");
  meta.setIdMeta(idMeta);
  String queryString="SELECT id,age from table";
  exception.expect(AchillesException.class);
  exception.expectMessage("The typed query [SELECT id,age from table] should contain the component column 'name' for embedded id type '" + EmbeddedKey.class.getCanonicalName() + "'");
  validator.validateTypedQuery(CompleteBean.class,queryString,meta);
}
