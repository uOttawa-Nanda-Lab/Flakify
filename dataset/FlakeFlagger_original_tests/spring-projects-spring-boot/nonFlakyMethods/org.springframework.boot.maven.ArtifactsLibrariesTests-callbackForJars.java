@Test public void callbackForJars() throws Exception {
  given(this.artifact.getType()).willReturn("jar");
  given(this.artifact.getScope()).willReturn("compile");
  this.libs.doWithLibraries(this.callback);
  verify(this.callback).library(this.libraryCaptor.capture());
  Library library=this.libraryCaptor.getValue();
  assertThat(library.getFile(),equalTo(this.file));
  assertThat(library.getScope(),equalTo(LibraryScope.COMPILE));
  assertThat(library.isUnpackRequired(),equalTo(false));
}
