@Test public void downloaded() throws InterruptedException {
  Thread.sleep(100);
  TransferEvent completedEvent=new TransferEvent.Builder(this.session,this.resource).addTransferredBytes(4096).build();
  this.session.getTransferListener().transferSucceeded(completedEvent);
  String message=new String(this.baos.toByteArray()).replace("\\","/");
  assertThat(message,startsWith("Downloaded: " + REPOSITORY + ARTIFACT));
  assertThat(message,containsString("4KB at"));
  assertThat(message,containsString("KB/sec"));
  assertThat(message,endsWith("\n"));
}
