@Test public void testLogCleaning() throws Exception {
  Configuration c=TEST_UTIL.getConfiguration();
  Path oldLogDir=new Path(TEST_UTIL.getTestDir(),HConstants.HREGION_OLDLOGDIR_NAME);
  FileSystem fs=FileSystem.get(c);
  AtomicBoolean stop=new AtomicBoolean(false);
  OldLogsCleaner cleaner=new OldLogsCleaner(1000,stop,c,fs,oldLogDir);
  long now=System.currentTimeMillis();
  fs.delete(oldLogDir,true);
  fs.mkdirs(oldLogDir);
  fs.createNewFile(new Path(oldLogDir,"a"));
  fs.createNewFile(new Path(oldLogDir,"1.hlog.dat.a"));
  fs.createNewFile(new Path(oldLogDir,"1.hlog.dat." + now));
  for (int i=0; i < 30; i++) {
    fs.createNewFile(new Path(oldLogDir,i + ".hlog.dat." + (now - 6000000)));
  }
  fs.createNewFile(new Path(oldLogDir,"a.hlog.dat." + (now + 10000)));
  assertEquals(34,fs.listStatus(oldLogDir).length);
  cleaner.chore();
  assertEquals(14,fs.listStatus(oldLogDir).length);
  cleaner.chore();
  assertEquals(1,fs.listStatus(oldLogDir).length);
}
