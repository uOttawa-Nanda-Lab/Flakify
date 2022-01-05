@Test public void testTransformation(){
  ModelNode legacyModel=new ModelNode();
  legacyModel.get("name").set("test");
  legacyModel.get("giop-minor-version").set(2);
  legacyModel.get("transactions").set("on");
  legacyModel.get("security").set("off");
  legacyModel.get("export-corbaloc").set("on");
  legacyModel.get("support-ssl").set("off");
  legacyModel.get("sun").set("on");
  legacyModel.get("comet").set("off");
  legacyModel.get("iona").set("on");
  ModelNode newModel=TransformUtils.transformModel(legacyModel);
  Assert.assertTrue(newModel.get("name").equals(new ModelNode("test")));
  Assert.assertTrue(newModel.get("giop-version").equals(new ModelNode("1.2")));
  Assert.assertTrue(newModel.get("security").equals(new ModelNode("none")));
  Assert.assertTrue(newModel.get("transactions").equals(new ModelNode("full")));
  Assert.assertTrue(newModel.get("export-corbaloc").equals(new ModelNode(true)));
  Assert.assertTrue(newModel.get("support-ssl").equals(new ModelNode(false)));
  Assert.assertTrue(newModel.get("iona").equals(new ModelNode(true)));
}
