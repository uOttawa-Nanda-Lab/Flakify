@Test public void readTest() throws InvalidPathException, FileAlreadyExistException, IOException {
  int fileId=TestUtils.createByteFile(mTFS,"/testFile",WriteType.MUST_CACHE,10);
  long blockId=mTFS.getBlockId(fileId,0);
  DataServerMessage sendMsg=DataServerMessage.createBlockRequestMessage(blockId);
  SocketChannel socketChannel=SocketChannel.open(new InetSocketAddress(mTFS.getFileBlocks(fileId).get(0).getLocations().get(0).mHost,mTFS.getFileBlocks(fileId).get(0).getLocations().get(0).mPort + 1));
  while (!sendMsg.finishSending()) {
    sendMsg.send(socketChannel);
  }
  DataServerMessage recvMsg=DataServerMessage.createBlockResponseMessage(false,blockId);
  while (!recvMsg.isMessageReady()) {
    int numRead=recvMsg.recv(socketChannel);
    if (numRead == -1) {
      break;
    }
  }
  socketChannel.close();
  Assert.assertEquals(TestUtils.getIncreasingByteBuffer(10),recvMsg.getReadOnlyData());
}
