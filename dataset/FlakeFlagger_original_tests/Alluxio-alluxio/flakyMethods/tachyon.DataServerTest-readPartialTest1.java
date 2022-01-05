@Test public void readPartialTest1() throws InvalidPathException, FileAlreadyExistException, IOException {
  int fileId=TestUtils.createByteFile(mTFS,"/testFile",WriteType.MUST_CACHE,10);
  long blockId=mTFS.getBlockId(fileId,0);
  DataServerMessage sendMsg;
  sendMsg=DataServerMessage.createBlockRequestMessage(blockId,0,6);
  SocketChannel socketChannel=SocketChannel.open(new InetSocketAddress(mTFS.getFileBlocks(fileId).get(0).getLocations().get(0).mHost,mTFS.getFileBlocks(fileId).get(0).getLocations().get(0).mPort + 1));
  while (!sendMsg.finishSending()) {
    sendMsg.send(socketChannel);
  }
  DataServerMessage recvMsg=DataServerMessage.createBlockResponseMessage(false,blockId,0,6);
  while (!recvMsg.isMessageReady()) {
    int numRead=recvMsg.recv(socketChannel);
    if (numRead == -1) {
      break;
    }
  }
  socketChannel.close();
  Assert.assertEquals(TestUtils.getIncreasingByteBuffer(6),recvMsg.getReadOnlyData());
}
