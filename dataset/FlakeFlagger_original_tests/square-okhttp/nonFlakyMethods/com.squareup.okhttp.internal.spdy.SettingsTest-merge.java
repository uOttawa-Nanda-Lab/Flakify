@Test public void merge(){
  Settings a=new Settings();
  a.set(UPLOAD_BANDWIDTH,PERSIST_VALUE,100);
  a.set(DOWNLOAD_BANDWIDTH,PERSIST_VALUE,200);
  a.set(DOWNLOAD_RETRANS_RATE,0,300);
  Settings b=new Settings();
  b.set(DOWNLOAD_BANDWIDTH,0,400);
  b.set(DOWNLOAD_RETRANS_RATE,PERSIST_VALUE,500);
  b.set(MAX_CONCURRENT_STREAMS,PERSIST_VALUE,600);
  a.merge(b);
  assertEquals(100,a.getUploadBandwidth(-1));
  assertEquals(PERSIST_VALUE,a.flags(UPLOAD_BANDWIDTH));
  assertEquals(400,a.getDownloadBandwidth(-1));
  assertEquals(0,a.flags(DOWNLOAD_BANDWIDTH));
  assertEquals(500,a.getDownloadRetransRate(-1));
  assertEquals(PERSIST_VALUE,a.flags(DOWNLOAD_RETRANS_RATE));
  assertEquals(600,a.getMaxConcurrentStreams(-1));
  assertEquals(PERSIST_VALUE,a.flags(MAX_CONCURRENT_STREAMS));
}
