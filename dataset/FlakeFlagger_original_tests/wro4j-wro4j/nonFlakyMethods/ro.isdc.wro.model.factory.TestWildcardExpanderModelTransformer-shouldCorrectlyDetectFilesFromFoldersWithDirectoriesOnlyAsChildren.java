@Test public void shouldCorrectlyDetectFilesFromFoldersWithDirectoriesOnlyAsChildren(){
  final WroModel model=new WroModel();
  final String uri=String.format(ClasspathUriLocator.PREFIX + "%s/expander/subfolder/**.js",WroUtil.toPackageAsFolder(getClass()));
  model.addGroup(new Group("group").addResource(Resource.create(uri,ResourceType.JS)));
  Mockito.when(decoratedFactory.create()).thenReturn(model);
  final WroModel changedModel=transformer.transform(model);
  LOG.debug("model: {}",changedModel);
  final String resultPathPrefix=String.format("%s%s/expander/subfolder",ClasspathUriLocator.PREFIX,WroUtil.toPackageAsFolder(getClass()));
  Assert.assertEquals(2,changedModel.getGroupByName("group").getResources().size());
  Assert.assertEquals(resultPathPrefix + "/folder1/script1.js",changedModel.getGroupByName("group").getResources().get(0).getUri());
  Assert.assertEquals(resultPathPrefix + "/folder2/script2.js",changedModel.getGroupByName("group").getResources().get(1).getUri());
}
