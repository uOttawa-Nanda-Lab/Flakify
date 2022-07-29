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

	@Test public void acceptHandshakeAsClient() throws Exception{HandshakeImpl1Server response=new HandshakeImpl1Server();HandshakeImpl1Client request=new HandshakeImpl1Client();Draft_6455 draft_6455=new Draft_6455();response.put("Upgrade","websocket");assertEquals(HandshakeState.NOT_MATCHED,draft_6455.acceptHandshakeAsClient(request,response));response.put("Connection","upgrade");assertEquals(HandshakeState.NOT_MATCHED,draft_6455.acceptHandshakeAsClient(request,response));response.put("Sec-WebSocket-Version","13");assertEquals(HandshakeState.NOT_MATCHED,draft_6455.acceptHandshakeAsClient(request,response));request.put("Sec-WebSocket-Key","dGhlIHNhbXBsZSBub25jZQ==");assertEquals(HandshakeState.NOT_MATCHED,draft_6455.acceptHandshakeAsClient(request,response));response.put("Sec-WebSocket-Accept","s3pPLMBiTxaQ9kYGzzhZRbK+xOo=");assertEquals(HandshakeState.MATCHED,draft_6455.acceptHandshakeAsClient(request,response));response.put("Sec-WebSocket-Protocol","chat");assertEquals(HandshakeState.NOT_MATCHED,draft_6455.acceptHandshakeAsClient(request,response));draft_6455=new Draft_6455(Collections.<IExtension>emptyList(),Collections.<IProtocol>singletonList(new Protocol("chat")));assertEquals(HandshakeState.MATCHED,draft_6455.acceptHandshakeAsClient(request,response));ArrayList<IProtocol> protocols=new ArrayList<IProtocol>();protocols.add(new Protocol(""));protocols.add(new Protocol("chat"));draft_6455=new Draft_6455(Collections.<IExtension>emptyList(),protocols);assertEquals(HandshakeState.MATCHED,draft_6455.acceptHandshakeAsClient(request,response));draft_6455=new Draft_6455();assertEquals(HandshakeState.NOT_MATCHED,draft_6455.acceptHandshakeAsClient(request,response));protocols.clear();protocols.add(new Protocol("chat3"));protocols.add(new Protocol("3chat"));draft_6455=new Draft_6455(Collections.<IExtension>emptyList(),protocols);assertEquals(HandshakeState.NOT_MATCHED,draft_6455.acceptHandshakeAsClient(request,response));}

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