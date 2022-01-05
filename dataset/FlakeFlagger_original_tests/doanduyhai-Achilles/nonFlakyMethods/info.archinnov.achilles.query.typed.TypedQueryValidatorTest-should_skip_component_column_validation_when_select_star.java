@Test public void should_skip_component_column_validation_when_select_star() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,EmbeddedKey.class).field("id").type(PropertyType.EMBEDDED_ID).compNames("id","name").build();
  EntityMeta meta=new EntityMeta();
  meta.setAllMetasExceptIdAndCounters(new ArrayList<PropertyMeta>());
  meta.setTableName("table");
  meta.setIdMeta(idMeta);
  String queryString="SELECT * from table";
  validator.validateTypedQuery(CompleteBean.class,queryString,meta);
}
