@Test public void testExpandWildcardWithMultipleResources(){
  final WroModel model=new WroModel();
  final String uri=String.format(ClasspathUriLocator.PREFIX + "%s/expander/*.js",WroUtil.toPackageAsFolder(getClass()));
  model.addGroup(new Group("group").addResource(Resource.create(uri,ResourceType.JS)));
  Mockito.when(decoratedFactory.create()).thenReturn(model);
  final WroModel changedModel=transformer.transform(model);
  LOG.debug("model: {}",changedModel);
  Assert.assertEquals(3,changedModel.getGroupByName("group").getResources().size());
}
