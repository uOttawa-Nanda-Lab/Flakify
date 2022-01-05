@Test public void libraries() throws Exception {
  TestJarFile libJar=new TestJarFile(this.temporaryFolder);
  libJar.addClass("a/b/C.class",ClassWithoutMainMethod.class);
  final File libJarFile=libJar.getFile();
  final File libJarFileToUnpack=libJar.getFile();
  final File libNonJarFile=this.temporaryFolder.newFile();
  FileCopyUtils.copy(new byte[]{0,1,2,3,4,5,6,7,8},libNonJarFile);
  this.testJarFile.addClass("a/b/C.class",ClassWithMainMethod.class);
  File file=this.testJarFile.getFile();
  Repackager repackager=new Repackager(file);
  repackager.repackage(new Libraries(){
    @Override public void doWithLibraries(    LibraryCallback callback) throws IOException {
      callback.library(new Library(libJarFile,LibraryScope.COMPILE));
      callback.library(new Library(libJarFileToUnpack,LibraryScope.COMPILE,true));
      callback.library(new Library(libNonJarFile,LibraryScope.COMPILE));
    }
  }
);
  assertThat(hasEntry(file,"lib/" + libJarFile.getName()),equalTo(true));
  assertThat(hasEntry(file,"lib/" + libJarFileToUnpack.getName()),equalTo(true));
  assertThat(hasEntry(file,"lib/" + libNonJarFile.getName()),equalTo(false));
  JarEntry entry=getEntry(file,"lib/" + libJarFileToUnpack.getName());
  assertThat(entry.getComment(),startsWith("UNPACK:"));
  assertThat(entry.getComment().length(),equalTo(47));
}
