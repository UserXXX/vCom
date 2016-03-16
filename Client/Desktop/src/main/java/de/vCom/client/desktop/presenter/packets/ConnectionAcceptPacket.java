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
 * Packet from server to client that indicates that the server accepted the connection.
 * @author Björn Lange
 */
public class ConnectionAcceptPacket extends Packet {
	/**
	 * Protocol identifier for this packet.
	 */
	public static final int PROTOCOL_IDENTIFIER = 257;
	
	private int deviceIdentifier;
	
	/**
	 * Gets the identifier the server uses for this device.
	 * @return The device identifier to use for this server.
	 */
	public int getDeviceIdentifier() {
		return deviceIdentifier;
	}
	
	/**
	 * Creates a new ConnectionAcceptPacket.
	 */
	public ConnectionAcceptPacket() { }

	@Override
	public void readFrom(DataInputStream input) throws IOException {
		deviceIdentifier = input.readInt();
	}

	@Override
	public void writeTo(DataOutputStream output) throws IOException {
		throw new UnsupportedOperationException(
				"Client is not allowed to send a ConnectionAcceptPacket!");
	}
}
