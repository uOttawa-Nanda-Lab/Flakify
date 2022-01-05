/** 
 * http://tools.ietf.org/html/draft-ietf-httpbis-header-compression-05#appendix-E.3
 */
@Test public void readRequestExamplesWithHuffman() throws IOException {
  OkBuffer out=firstRequestWithHuffman();
  bytesIn.write(out,out.size());
  hpackReader.readHeaders();
  hpackReader.emitReferenceSet();
  checkReadFirstRequestWithHuffman();
  out=secondRequestWithHuffman();
  bytesIn.write(out,out.size());
  hpackReader.readHeaders();
  hpackReader.emitReferenceSet();
  checkReadSecondRequestWithHuffman();
  out=thirdRequestWithHuffman();
  bytesIn.write(out,out.size());
  hpackReader.readHeaders();
  hpackReader.emitReferenceSet();
  checkReadThirdRequestWithHuffman();
}
