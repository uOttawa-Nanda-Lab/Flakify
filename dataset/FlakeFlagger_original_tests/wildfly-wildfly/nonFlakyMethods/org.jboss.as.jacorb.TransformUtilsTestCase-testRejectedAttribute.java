@Test public void testRejectedAttribute() throws Exception {
  ModelNode model=new ModelNode();
  model.get("queue-min").set(5);
  List<String> result=TransformUtils.validateDeprecatedProperites(model);
  Assert.assertFalse(result.isEmpty());
}
