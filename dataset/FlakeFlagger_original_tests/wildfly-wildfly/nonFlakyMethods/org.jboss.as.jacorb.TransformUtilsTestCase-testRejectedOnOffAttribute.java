@Test public void testRejectedOnOffAttribute() throws Exception {
  ModelNode model=new ModelNode();
  model.get("monitoring").set("on");
  List<String> result=TransformUtils.validateDeprecatedProperites(model);
  Assert.assertFalse(result.isEmpty());
}
