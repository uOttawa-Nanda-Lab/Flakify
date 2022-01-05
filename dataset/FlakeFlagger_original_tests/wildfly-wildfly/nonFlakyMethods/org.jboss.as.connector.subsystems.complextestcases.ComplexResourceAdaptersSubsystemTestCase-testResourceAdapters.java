@Test public void testResourceAdapters() throws Exception {
  ModelNode model=getModel("ra.xml","some.rar");
  if (model == null)   return;
  Properties params=ParseUtils.raCommonProperties();
  ModelNode raCommonModel=model.get("subsystem","resource-adapters","resource-adapter","myRA");
  ParseUtils.checkModelParams(raCommonModel,params);
  Assert.assertEquals(raCommonModel.asString(),"A",raCommonModel.get("config-properties","Property","value").asString());
  Assert.assertEquals(raCommonModel.get("beanvalidationgroups").asString(),raCommonModel.get("beanvalidationgroups").asString(),"[\"Class0\",\"Class00\"]");
  params=ParseUtils.raAdminProperties();
  ModelNode raAdminModel=raCommonModel.get("admin-objects","Pool2");
  ParseUtils.checkModelParams(raAdminModel,params);
  Assert.assertEquals(raAdminModel.asString(),"D",raAdminModel.get("config-properties","Property","value").asString());
  params=ParseUtils.raConnectionProperties();
  ModelNode raConnModel=raCommonModel.get("connection-definitions","Pool1");
  ParseUtils.checkModelParams(raConnModel,params);
  Assert.assertEquals(raConnModel.asString(),"B",raConnModel.get("config-properties","Property","value").asString());
  Assert.assertEquals(raConnModel.asString(),"C",raConnModel.get("recovery-plugin-properties","Property").asString());
}
