@Test public void renamesDuplicates() throws Exception {
  Artifact artifact1=mock(Artifact.class);
  Artifact artifact2=mock(Artifact.class);
  given(artifact1.getType()).willReturn("jar");
  given(artifact1.getScope()).willReturn("compile");
  given(artifact1.getGroupId()).willReturn("g1");
  given(artifact1.getFile()).willReturn(new File("a"));
  given(artifact2.getType()).willReturn("jar");
  given(artifact2.getScope()).willReturn("compile");
  given(artifact2.getGroupId()).willReturn("g2");
  given(artifact2.getFile()).willReturn(new File("a"));
  this.artifacts=new LinkedHashSet<Artifact>(Arrays.asList(artifact1,artifact2));
  this.libs=new ArtifactsLibraries(this.artifacts,null,mock(Log.class));
  this.libs.doWithLibraries(this.callback);
  verify(this.callback,times(2)).library(this.libraryCaptor.capture());
  assertThat(this.libraryCaptor.getAllValues().get(0).getName(),equalTo("g1-a"));
  assertThat(this.libraryCaptor.getAllValues().get(1).getName(),equalTo("g2-a"));
}
