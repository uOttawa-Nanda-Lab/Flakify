@Test public void addInputStreamShouldThrowActivitiExceptionWhenIOExceptionIsThrown() throws Exception {
  given(resource.getInputStream()).willThrow(new IOException());
  Throwable thrown=catchThrowable(() -> deploymentBuilder.addInputStream("any.xml",resource));
  assertThat(thrown).isInstanceOf(ActivitiException.class).hasMessageContaining("Couldn't auto deploy resource");
}
