/**
 * vCom is a chat service consisting of client and server application that encrypts every message
 * send and thus allows a secure and for third parties not readable communication.
 * Copyright (C) 2016 Kai Brandenbusch, Björn Lange, Christian Langer, Daniel Theis
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If
 * not, see <http://www.gnu.org/licenses/>.
 */
package de.vCom.client.desktop.presenter.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Packet for broadcasting a message from the server to all clients. Broadcast messages are
 * <b>not</b> encrypted and transport a single string. The intention for this packet is to send
 * i.e. maintenance information to the clients to notify them of the server shutting down or
 * rebooting soon.
 * @author Björn Lange
 */
public class BroadcastMessagePacket extends Packet {
	/**
	 * Protocol identifier for this packet.
	 */
	public static final int PROTOCOL_IDENTIFIER = 262;

	private String message;
	
	/**
	 * Gets the message from this packet.
	 * @return The message from this packet.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Creates a new {@code BroadcastMessagePacket}.
	 */
	public BroadcastMessagePacket() { }
	
	@Override
	public void readFrom(DataInputStream input) throws IOException {
		int length = input.readInt();
		byte[] msgData = new byte[length];
		input.readFully(msgData);
		message = new String(msgData, StandardCharsets.UTF_8);
	}

	@Override
	public void writeTo(DataOutputStream output) throws IOException {
		throw new UnsupportedOperationException(
				"Client is not allowed to send a BroadcastMessagePacket!");
	}
}
