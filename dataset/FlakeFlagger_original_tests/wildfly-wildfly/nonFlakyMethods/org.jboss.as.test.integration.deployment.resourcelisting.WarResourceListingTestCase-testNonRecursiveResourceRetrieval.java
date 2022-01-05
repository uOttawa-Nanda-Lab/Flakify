@Test() public void testNonRecursiveResourceRetrieval(){
  log.trace("Test nonrecursive listing of resources");
  doTestResourceRetrieval(false,"/");
}
