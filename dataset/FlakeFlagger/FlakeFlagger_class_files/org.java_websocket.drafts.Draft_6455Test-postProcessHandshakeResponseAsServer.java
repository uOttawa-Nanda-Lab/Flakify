/*
 * Copyright (c) 2010-2018 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

package org.java_websocket.drafts;

import org.java_websocket.enums.CloseHandshakeType;
import org.java_websocket.enums.HandshakeState;
import org.java_websocket.extensions.DefaultExtension;
import org.java_websocket.extensions.IExtension;
import org.java_websocket.framing.BinaryFrame;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.TextFrame;
import org.java_websocket.handshake.HandshakeImpl1Client;
import org.java_websocket.handshake.HandshakeImpl1Server;
import org.java_websocket.protocols.IProtocol;
import org.java_websocket.protocols.Protocol;
import org.java_websocket.util.Charsetfunctions;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class Draft_6455Test {

	HandshakeImpl1Client handshakedataProtocolExtension;
	HandshakeImpl1Client handshakedataProtocol;
	HandshakeImpl1Client handshakedataExtension;
	HandshakeImpl1Client handshakedata;

	@Test public void postProcessHandshakeResponseAsServer() throws Exception{Draft_6455 draft_6455=new Draft_6455();HandshakeImpl1Server response=new HandshakeImpl1Server();HandshakeImpl1Client request=new HandshakeImpl1Client();request.put("Sec-WebSocket-Key","dGhlIHNhbXBsZSBub25jZQ==");request.put("Connection","upgrade");draft_6455.postProcessHandshakeResponseAsServer(request,response);assertTrue(response.hasFieldValue("Date"));assertTrue(response.hasFieldValue("Sec-WebSocket-Accept"));assertEquals("Web Socket Protocol Handshake",response.getHttpStatusMessage());assertEquals("TooTallNate Java-WebSocket",response.getFieldValue("Server"));assertEquals("upgrade",response.getFieldValue("Connection"));assertEquals("websocket",response.getFieldValue("Upgrade"));assertTrue(!response.hasFieldValue("Sec-WebSocket-Protocol"));response=new HandshakeImpl1Server();draft_6455.acceptHandshakeAsServer(handshakedata);draft_6455.postProcessHandshakeResponseAsServer(request,response);assertTrue(!response.hasFieldValue("Sec-WebSocket-Protocol"));assertTrue(!response.hasFieldValue("Sec-WebSocket-Extensions"));response=new HandshakeImpl1Server();draft_6455.acceptHandshakeAsServer(handshakedataProtocol);draft_6455.postProcessHandshakeResponseAsServer(request,response);assertTrue(!response.hasFieldValue("Sec-WebSocket-Protocol"));assertTrue(!response.hasFieldValue("Sec-WebSocket-Extensions"));response=new HandshakeImpl1Server();draft_6455.acceptHandshakeAsServer(handshakedataExtension);draft_6455.postProcessHandshakeResponseAsServer(request,response);assertTrue(!response.hasFieldValue("Sec-WebSocket-Protocol"));assertTrue(!response.hasFieldValue("Sec-WebSocket-Extensions"));response=new HandshakeImpl1Server();draft_6455.acceptHandshakeAsServer(handshakedataProtocolExtension);draft_6455.postProcessHandshakeResponseAsServer(request,response);assertTrue(!response.hasFieldValue("Sec-WebSocket-Protocol"));assertTrue(!response.hasFieldValue("Sec-WebSocket-Extensions"));response=new HandshakeImpl1Server();draft_6455=new Draft_6455(Collections.<IExtension>emptyList(),Collections.<IProtocol>singletonList(new Protocol("chat")));draft_6455.acceptHandshakeAsServer(handshakedataProtocol);draft_6455.postProcessHandshakeResponseAsServer(request,response);assertEquals("chat",response.getFieldValue("Sec-WebSocket-Protocol"));assertTrue(!response.hasFieldValue("Sec-WebSocket-Extensions"));response=new HandshakeImpl1Server();draft_6455.reset();draft_6455.acceptHandshakeAsServer(handshakedataExtension);draft_6455.postProcessHandshakeResponseAsServer(request,response);assertTrue(!response.hasFieldValue("Sec-WebSocket-Protocol"));assertTrue(!response.hasFieldValue("Sec-WebSocket-Extensions"));response=new HandshakeImpl1Server();draft_6455.reset();draft_6455.acceptHandshakeAsServer(handshakedataProtocolExtension);draft_6455.postProcessHandshakeResponseAsServer(request,response);assertEquals("chat",response.getFieldValue("Sec-WebSocket-Protocol"));assertTrue(!response.hasFieldValue("Sec-WebSocket-Extensions"));ArrayList<IProtocol> protocols=new ArrayList<IProtocol>();protocols.add(new Protocol("test"));protocols.add(new Protocol("chat"));draft_6455=new Draft_6455(Collections.<IExtension>emptyList(),protocols);draft_6455.acceptHandshakeAsServer(handshakedataProtocol);draft_6455.postProcessHandshakeResponseAsServer(request,response);assertEquals("test",response.getFieldValue("Sec-WebSocket-Protocol"));assertTrue(!response.hasFieldValue("Sec-WebSocket-Extensions"));response=new HandshakeImpl1Server();draft_6455.reset();draft_6455.acceptHandshakeAsServer(handshakedataExtension);draft_6455.postProcessHandshakeResponseAsServer(request,response);assertTrue(!response.hasFieldValue("Sec-WebSocket-Protocol"));assertTrue(!response.hasFieldValue("Sec-WebSocket-Extensions"));response=new HandshakeImpl1Server();draft_6455.reset();draft_6455.acceptHandshakeAsServer(handshakedataProtocolExtension);draft_6455.postProcessHandshakeResponseAsServer(request,response);assertEquals("test",response.getFieldValue("Sec-WebSocket-Protocol"));assertTrue(!response.hasFieldValue("Sec-WebSocket-Extensions"));}


	private class TestExtension extends DefaultExtension {
		@Override
		public int hashCode() {
			return getClass().hashCode();
		}

		@Override
		public IExtension copyInstance() {
			return new TestExtension();
		}

		@Override
		public boolean equals( Object o ) {
			if( this == o ) return true;
			if( o == null ) return false;
			return getClass() == o.getClass();
		}
	}
}