@Test public void testZippedJar() throws Exception {
  File defaultPar=buildDefaultPar();
  addPackageToClasspath(defaultPar);
  final VirtualFile virtualFile=VFS.getChild(defaultPar.getAbsolutePath());
  Closeable closeable=VFS.mountZip(virtualFile,virtualFile,tempFileProvider);
  try {
    ArchiveDescriptor archiveDescriptor=VirtualFileSystemArchiveDescriptorFactory.INSTANCE.buildArchiveDescriptor(virtualFile.toURL());
    AbstractScannerImpl.ResultCollector resultCollector=new AbstractScannerImpl.ResultCollector(new StandardScanOptions());
    archiveDescriptor.visitArchive(new AbstractScannerImpl.ArchiveContextImpl(new PersistenceUnitDescriptorAdapter(),true,resultCollector));
    validateResults(resultCollector,ApplicationServer.class,Version.class);
  }
  finally {
    closeable.close();
  }
}
