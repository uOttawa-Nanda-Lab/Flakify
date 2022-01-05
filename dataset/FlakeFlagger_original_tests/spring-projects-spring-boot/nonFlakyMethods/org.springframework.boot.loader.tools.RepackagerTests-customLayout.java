@Test public void customLayout() throws Exception {
  TestJarFile libJar=new TestJarFile(this.temporaryFolder);
  libJar.addClass("a/b/C.class",ClassWithoutMainMethod.class);
  final File libJarFile=libJar.getFile();
  this.testJarFile.addClass("a/b/C.class",ClassWithMainMethod.class);
  File file=this.testJarFile.getFile();
  Repackager repackager=new Repackager(file);
  Layout layout=mock(Layout.class);
  final LibraryScope scope=mock(LibraryScope.class);
  given(layout.getLauncherClassName()).willReturn("testLauncher");
  given(layout.getLibraryDestination(anyString(),eq(scope))).willReturn("test/");
  repackager.setLayout(layout);
  repackager.repackage(new Libraries(){
    @Override public void doWithLibraries(    LibraryCallback callback) throws IOException {
      callback.library(new Library(libJarFile,scope));
    }
  }
);
  assertThat(hasEntry(file,"test/" + libJarFile.getName()),equalTo(true));
  assertThat(getManifest(file).getMainAttributes().getValue("Main-Class"),equalTo("testLauncher"));
}
