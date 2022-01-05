@Test() public void testNonRecursiveResourceRetrievalForSpecifiedRootDir(){
  log.trace("Test recursive listing of resources in specific directory");
  doTestResourceRetrieval(false,"/WEB-INF");
}
