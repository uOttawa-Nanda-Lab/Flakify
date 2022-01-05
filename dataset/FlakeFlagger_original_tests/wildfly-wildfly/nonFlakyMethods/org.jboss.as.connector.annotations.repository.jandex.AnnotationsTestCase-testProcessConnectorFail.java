/** 
 * Process: Connector -- verification of the processConnector method
 * @throws Throwable throwable exception
 */
@Test public void testProcessConnectorFail() throws Throwable {
  try {
    URI uri=getURI("/rafail2connector.rar");
    final VirtualFile virtualFile=VFS.getChild(uri);
    final Indexer indexer=new Indexer();
    final List<VirtualFile> classChildren=virtualFile.getChildren(new SuffixMatchFilter(".class",VisitorAttributes.RECURSE_LEAVES_ONLY));
    for (    VirtualFile classFile : classChildren) {
      InputStream inputStream=null;
      try {
        inputStream=classFile.openStream();
        indexer.index(inputStream);
      }
  finally {
        VFSUtils.safeClose(inputStream);
      }
    }
    final Index index=indexer.complete();
    AnnotationRepository ar=new JandexAnnotationRepositoryImpl(index,Thread.currentThread().getContextClassLoader());
    annotations.process(ar,null,Thread.currentThread().getContextClassLoader());
    fail("Success");
  }
 catch (  Throwable t) {
  }
}
