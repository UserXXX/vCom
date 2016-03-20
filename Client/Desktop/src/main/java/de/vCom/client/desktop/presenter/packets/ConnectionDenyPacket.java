/**
 * vCom is a chat service consisting of client and server application that encrypts every message
 * send and thus allows a secure and for third parties not readable communication.
 * Copyright (C) 2016 Kai Brandenbusch, Björn Lange, Christian Langer, Daniel Theis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.vCom.client.desktop.presenter.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Packet that notifies the client that the requested connection was denied.
 * @author Björn Lange
 */
public class ConnectionDenyPacket extends Packet {
	/**
	 * Protocol identifier for this packet.
	 */
	public static final int PROTOCOL_IDENTIFIER = 258;
	/**
	 * Error returned if the used User Identifier is blacklisted on the server.
	 */
	public static final byte ERROR_USER_IDENTIFIER_BLACKLISTED = 0;
	/**
	 * Error returned if the connecting IP is blacklisted on the server.
	 */
	public static final byte ERROR_IP_BLACKLISTED = 1;
	/**
	 * Error returned if the connecting IP or the used User Identifier is not whitelisted on the
	 * server and the whitelist is active.
	 */
	public static final byte ERROR_NOT_WHITELISTED = 2;
	
	private byte errorCode;

	/**
	 * Gets the error code, that gives the reason why the connection was denied.
	 * @return The error code. For a list see the constants of ConnectionDenyPacket.
	 */
	public byte getErrorCode() {
		return errorCode;
	}
	
	/**
	 * Creates a new ConnectionDenyPacket.
	 */
	public ConnectionDenyPacket() { }

	@Override
	public void readFrom(DataInputStream input) throws IOException {
		errorCode = input.readByte();
	}

	@Override
	public void writeTo(DataOutputStream output) throws IOException {
		throw new UnsupportedOperationException(
				"Client is not allowed to send a ConnectionDenyPacket!");
	}
}
