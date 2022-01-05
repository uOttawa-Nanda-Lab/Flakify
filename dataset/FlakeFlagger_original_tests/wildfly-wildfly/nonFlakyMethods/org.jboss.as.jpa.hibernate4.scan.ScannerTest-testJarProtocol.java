@Test public void testJarProtocol() throws Exception {
  File war=buildWar();
  addPackageToClasspath(war);
  final VirtualFile warVirtualFile=VFS.getChild(war.getAbsolutePath());
  Closeable closeable=VFS.mountZip(warVirtualFile,warVirtualFile,tempFileProvider);
  try {
    ArchiveDescriptor archiveDescriptor=VirtualFileSystemArchiveDescriptorFactory.INSTANCE.buildArchiveDescriptor(warVirtualFile.toURL());
    AbstractScannerImpl.ResultCollector resultCollector=new AbstractScannerImpl.ResultCollector(new StandardScanOptions());
    archiveDescriptor.visitArchive(new AbstractScannerImpl.ArchiveContextImpl(new PersistenceUnitDescriptorAdapter(),true,resultCollector));
    validateResults(resultCollector,org.hibernate.jpa.test.pack.war.ApplicationServer.class,org.hibernate.jpa.test.pack.war.Version.class);
  }
  finally {
    closeable.close();
  }
}
