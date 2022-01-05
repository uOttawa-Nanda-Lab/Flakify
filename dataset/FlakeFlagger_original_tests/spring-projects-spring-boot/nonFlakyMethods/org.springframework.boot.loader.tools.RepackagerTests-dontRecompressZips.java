@Test public void dontRecompressZips() throws Exception {
  TestJarFile nested=new TestJarFile(this.temporaryFolder);
  nested.addClass("a/b/C.class",ClassWithoutMainMethod.class);
  final File nestedFile=nested.getFile();
  this.testJarFile.addFile("test/nested.jar",nestedFile);
  this.testJarFile.addClass("A.class",ClassWithMainMethod.class);
  File file=this.testJarFile.getFile();
  Repackager repackager=new Repackager(file);
  repackager.repackage(new Libraries(){
    @Override public void doWithLibraries(    LibraryCallback callback) throws IOException {
      callback.library(new Library(nestedFile,LibraryScope.COMPILE));
    }
  }
);
  JarFile jarFile=new JarFile(file);
  try {
    assertThat(jarFile.getEntry("lib/" + nestedFile.getName()).getMethod(),equalTo(ZipEntry.STORED));
    assertThat(jarFile.getEntry("test/nested.jar").getMethod(),equalTo(ZipEntry.STORED));
  }
  finally {
    jarFile.close();
  }
}
