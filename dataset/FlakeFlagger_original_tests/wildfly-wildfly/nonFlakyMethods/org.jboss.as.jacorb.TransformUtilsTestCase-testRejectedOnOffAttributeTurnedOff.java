@Test public void testRejectedOnOffAttributeTurnedOff() throws Exception {
  ModelNode model=new ModelNode();
  model.get("monitoring").set("off");
  List<String> result=TransformUtils.validateDeprecatedProperites(model);
  Assert.assertTrue(result.isEmpty());
}
