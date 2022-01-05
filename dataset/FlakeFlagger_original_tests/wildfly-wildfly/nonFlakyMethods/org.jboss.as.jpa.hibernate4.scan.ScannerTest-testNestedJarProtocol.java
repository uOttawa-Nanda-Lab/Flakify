@Test public void testNestedJarProtocol() throws Exception {
  File defaultPar=buildDefaultPar();
  File nestedEar=buildNestedEar(defaultPar);
  addPackageToClasspath(nestedEar);
  final VirtualFile nestedEarVirtualFile=VFS.getChild(nestedEar.getAbsolutePath());
  Closeable closeable=VFS.mountZip(nestedEarVirtualFile,nestedEarVirtualFile,tempFileProvider);
  try {
    VirtualFile parVirtualFile=nestedEarVirtualFile.getChild("defaultpar.par");
    Closeable closeable2=VFS.mountZip(parVirtualFile,parVirtualFile,tempFileProvider);
    try {
      ArchiveDescriptor archiveDescriptor=VirtualFileSystemArchiveDescriptorFactory.INSTANCE.buildArchiveDescriptor(parVirtualFile.toURL());
      AbstractScannerImpl.ResultCollector resultCollector=new AbstractScannerImpl.ResultCollector(new StandardScanOptions());
      archiveDescriptor.visitArchive(new AbstractScannerImpl.ArchiveContextImpl(new PersistenceUnitDescriptorAdapter(),true,resultCollector));
      validateResults(resultCollector,ApplicationServer.class,Version.class);
    }
  finally {
      closeable2.close();
    }
  }
  finally {
    closeable.close();
  }
  File nestedEarDir=buildNestedEarDir(defaultPar);
  final VirtualFile nestedEarDirVirtualFile=VFS.getChild(nestedEarDir.getAbsolutePath());
  try {
    VirtualFile parVirtualFile=nestedEarDirVirtualFile.getChild("defaultpar.par");
    closeable=VFS.mountZip(parVirtualFile,parVirtualFile,tempFileProvider);
    try {
      ArchiveDescriptor archiveDescriptor=VirtualFileSystemArchiveDescriptorFactory.INSTANCE.buildArchiveDescriptor(parVirtualFile.toURL());
      AbstractScannerImpl.ResultCollector resultCollector=new AbstractScannerImpl.ResultCollector(new StandardScanOptions());
      archiveDescriptor.visitArchive(new AbstractScannerImpl.ArchiveContextImpl(new PersistenceUnitDescriptorAdapter(),true,resultCollector));
      validateResults(resultCollector,ApplicationServer.class,Version.class);
    }
  finally {
      closeable.close();
    }
  }
  finally {
    closeable.close();
  }
}
