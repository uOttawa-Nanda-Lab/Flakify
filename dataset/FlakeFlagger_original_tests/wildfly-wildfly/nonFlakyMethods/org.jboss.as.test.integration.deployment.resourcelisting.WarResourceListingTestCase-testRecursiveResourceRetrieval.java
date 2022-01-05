@Test() public void testRecursiveResourceRetrieval(){
  log.trace("Test recursive listing of resources");
  doTestResourceRetrieval(true,"/");
}
