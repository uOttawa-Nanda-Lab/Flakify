@Test public void should_exception_when_wrong_table() throws Exception {
  EntityMeta meta=new EntityMeta();
  meta.setPropertyMetas(new HashMap<String,PropertyMeta>());
  meta.setTableName("table");
  String queryString="SELECT * from test";
  exception.expect(AchillesException.class);
  exception.expectMessage("The typed query [SELECT * from test] should contain the ' from table' clause if type is '" + CompleteBean.class.getCanonicalName() + "'");
  validator.validateRawTypedQuery(CompleteBean.class,queryString,meta);
}
