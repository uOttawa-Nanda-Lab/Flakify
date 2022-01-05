@Test public void callbackWithUnpack() throws Exception {
  given(this.artifact.getGroupId()).willReturn("gid");
  given(this.artifact.getArtifactId()).willReturn("aid");
  given(this.artifact.getType()).willReturn("jar");
  given(this.artifact.getScope()).willReturn("compile");
  Dependency unpack=new Dependency();
  unpack.setGroupId("gid");
  unpack.setArtifactId("aid");
  this.libs=new ArtifactsLibraries(this.artifacts,Collections.singleton(unpack),mock(Log.class));
  this.libs.doWithLibraries(this.callback);
  verify(this.callback).library(this.libraryCaptor.capture());
  assertThat(this.libraryCaptor.getValue().isUnpackRequired(),equalTo(true));
}
