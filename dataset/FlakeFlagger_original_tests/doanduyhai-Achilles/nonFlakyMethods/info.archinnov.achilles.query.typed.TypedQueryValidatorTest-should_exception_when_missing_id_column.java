@Test public void should_exception_when_missing_id_column() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,Long.class).field("id").type(PropertyType.ID).build();
  EntityMeta meta=new EntityMeta();
  meta.setTableName("table");
  meta.setIdMeta(idMeta);
  String queryString="SELECT name,age from table";
  exception.expect(AchillesException.class);
  exception.expectMessage("The typed query [SELECT name,age from table] should contain the id column 'id'");
  validator.validateTypedQuery(CompleteBean.class,queryString,meta);
}
