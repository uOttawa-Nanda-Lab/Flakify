private OkBuffer gunzip(OkBuffer gzipped) throws IOException {
  OkBuffer result=new OkBuffer();
  GzipSource source=new GzipSource(gzipped);
  while (source.read(result,Integer.MAX_VALUE) != -1) {
  }
  return result;
}
