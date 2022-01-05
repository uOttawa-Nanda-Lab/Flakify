public void testComparator() throws IOException {
  Path mFile=new Path(ROOT_DIR,"meta.tfile");
  FSDataOutputStream fout=createFSOutput(mFile);
  Writer writer=new Writer(fout,minBlockSize,(Compression.Algorithm)null,new RawComparator<byte[]>(){
    @Override public int compare(    byte[] b1,    int s1,    int l1,    byte[] b2,    int s2,    int l2){
      return -Bytes.compareTo(b1,s1,l1,b2,s2,l2);
    }
    @Override public int compare(    byte[] o1,    byte[] o2){
      return compare(o1,0,o1.length,o2,0,o2.length);
    }
  }
);
  writer.append("3".getBytes(),"0".getBytes());
  writer.append("2".getBytes(),"0".getBytes());
  writer.append("1".getBytes(),"0".getBytes());
  writer.close();
}
