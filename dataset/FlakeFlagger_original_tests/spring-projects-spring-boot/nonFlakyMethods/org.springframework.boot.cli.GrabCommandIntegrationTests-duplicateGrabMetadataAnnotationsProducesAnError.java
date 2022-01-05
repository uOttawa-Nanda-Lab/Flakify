@Test public void duplicateGrabMetadataAnnotationsProducesAnError() throws Exception {
  try {
    this.cli.grab("duplicateGrabMetadata.groovy");
    fail();
  }
 catch (  Exception ex) {
    assertThat(ex.getMessage(),containsString("Duplicate @GrabMetadata annotation"));
  }
}
