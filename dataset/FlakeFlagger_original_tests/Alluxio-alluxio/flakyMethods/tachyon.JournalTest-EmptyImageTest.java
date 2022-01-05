@Test public void EmptyImageTest() throws Exception {
  mLocalTachyonCluster.stop();
  Journal journal=new Journal(MasterConf.get().JOURNAL_FOLDER,"image.data","log.data");
  MasterInfo info=new MasterInfo(new InetSocketAddress(9999),journal);
  Assert.assertEquals(1,info.ls("/",true).size());
  Assert.assertTrue(info.getFileId("/") != -1);
  info.stop();
}
