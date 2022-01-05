@Test() public void testRecursiveResourceRetrievalForSpecifiedRootDir(){
  log.trace("Test recursive listing of resources in specific directory");
  doTestResourceRetrieval(true,"/WEB-INF");
}
