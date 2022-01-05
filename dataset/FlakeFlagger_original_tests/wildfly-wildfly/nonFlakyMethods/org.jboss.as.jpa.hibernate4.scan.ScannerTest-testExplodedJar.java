@Test public void testExplodedJar() throws Exception {
  File explodedPar=buildExplodedPar();
  addPackageToClasspath(explodedPar);
  String dirPath=explodedPar.getAbsolutePath();
  if (dirPath.endsWith("/")) {
    dirPath=dirPath.substring(0,dirPath.length() - 1);
  }
  final VirtualFile virtualFile=VFS.getChild(dirPath);
  ArchiveDescriptor archiveDescriptor=VirtualFileSystemArchiveDescriptorFactory.INSTANCE.buildArchiveDescriptor(virtualFile.toURL());
  AbstractScannerImpl.ResultCollector resultCollector=new AbstractScannerImpl.ResultCollector(new StandardScanOptions());
  archiveDescriptor.visitArchive(new AbstractScannerImpl.ArchiveContextImpl(new PersistenceUnitDescriptorAdapter(),true,resultCollector));
  assertEquals(1,resultCollector.getClassDescriptorSet().size());
  assertEquals(1,resultCollector.getPackageDescriptorSet().size());
  assertEquals(1,resultCollector.getMappingFileSet().size());
  assertTrue(resultCollector.getClassDescriptorSet().contains(new ClassDescriptorImpl(Carpet.class.getName(),null)));
  for (  MappingFileDescriptor mappingFileDescriptor : resultCollector.getMappingFileSet()) {
    assertNotNull(mappingFileDescriptor.getStreamAccess());
    final InputStream stream=mappingFileDescriptor.getStreamAccess().accessInputStream();
    assertNotNull(stream);
    stream.close();
  }
}
