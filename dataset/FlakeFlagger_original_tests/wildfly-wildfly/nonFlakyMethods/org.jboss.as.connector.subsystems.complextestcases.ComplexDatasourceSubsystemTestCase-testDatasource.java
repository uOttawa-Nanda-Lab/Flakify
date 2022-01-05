@Test public void testDatasource() throws Exception {
  ModelNode model=getModel("datasource.xml",false,null);
  final String complexDs="complexDs";
  final String complexDsJndi="java:jboss/datasources/" + complexDs;
  Properties params=ParseUtils.nonXaDsProperties(complexDsJndi);
  ModelNode modelDs=model.get("subsystem","datasources","data-source",complexDs + "_Pool");
  ParseUtils.checkModelParams(modelDs,params);
  Assert.assertEquals(modelDs.asString(),"UTF-8",modelDs.get("connection-properties","char.encoding","value").asString());
  Assert.assertEquals(modelDs.asString(),"Property2",modelDs.get("valid-connection-checker-properties","name").asString());
  Assert.assertEquals(modelDs.asString(),"Property4",modelDs.get("exception-sorter-properties","name").asString());
  Assert.assertEquals(modelDs.asString(),"Property3",modelDs.get("stale-connection-checker-properties","name").asString());
  Assert.assertEquals(modelDs.asString(),"Property1",modelDs.get("reauth-plugin-properties","name").asString());
  final String complexXaDs="complexXaDs";
  final String complexXaDsJndi="java:jboss/xa-datasources/" + complexXaDs;
  params=ParseUtils.xaDsProperties(complexXaDsJndi);
  ModelNode modelXaDs=model.get("subsystem","datasources","xa-data-source",complexXaDs + "_Pool");
  ParseUtils.checkModelParams(modelXaDs,params);
  Assert.assertEquals(modelXaDs.asString(),"jdbc:h2:mem:test",modelXaDs.get("xa-datasource-properties","URL","value").asString());
  Assert.assertEquals(modelXaDs.asString(),"Property2",modelXaDs.get("valid-connection-checker-properties","name").asString());
  Assert.assertEquals(modelXaDs.asString(),"Property4",modelXaDs.get("exception-sorter-properties","name").asString());
  Assert.assertEquals(modelXaDs.asString(),"Property3",modelXaDs.get("stale-connection-checker-properties","name").asString());
  Assert.assertEquals(modelXaDs.asString(),"Property1",modelXaDs.get("reauth-plugin-properties","name").asString());
  Assert.assertEquals(modelXaDs.asString(),"Property5",modelXaDs.get("recovery-plugin-properties","name").asString());
  Assert.assertEquals(modelXaDs.asString(),"Property6",modelXaDs.get("recovery-plugin-properties","name1").asString());
}
