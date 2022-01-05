public void testNullMetaBlocks() throws Exception {
  Path mFile=new Path(ROOT_DIR,"nometa.hfile");
  FSDataOutputStream fout=createFSOutput(mFile);
  Writer writer=new Writer(fout,minBlockSize,Compression.Algorithm.NONE,null);
  writer.append("foo".getBytes(),"value".getBytes());
  writer.close();
  fout.close();
  Reader reader=new Reader(fs,mFile,null,false);
  reader.loadFileInfo();
  assertNull(reader.getMetaBlock("non-existant"));
}
