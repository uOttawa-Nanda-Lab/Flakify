@Test public void duplicateLibraries() throws Exception {
  TestJarFile libJar=new TestJarFile(this.temporaryFolder);
  libJar.addClass("a/b/C.class",ClassWithoutMainMethod.class);
  final File libJarFile=libJar.getFile();
  this.testJarFile.addClass("a/b/C.class",ClassWithMainMethod.class);
  File file=this.testJarFile.getFile();
  Repackager repackager=new Repackager(file);
  this.thrown.expect(IllegalStateException.class);
  this.thrown.expectMessage("Duplicate library");
  repackager.repackage(new Libraries(){
    @Override public void doWithLibraries(    LibraryCallback callback) throws IOException {
      callback.library(new Library(libJarFile,LibraryScope.COMPILE,false));
      callback.library(new Library(libJarFile,LibraryScope.COMPILE,false));
    }
  }
);
}
