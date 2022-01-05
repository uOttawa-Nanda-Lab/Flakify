@Test public void downloading() throws TransferCancelledException {
  TransferEvent startedEvent=new TransferEvent.Builder(this.session,this.resource).build();
  this.session.getTransferListener().transferStarted(startedEvent);
  assertEquals(String.format("Downloading: %s%s%n",REPOSITORY,ARTIFACT),new String(this.baos.toByteArray()));
}
