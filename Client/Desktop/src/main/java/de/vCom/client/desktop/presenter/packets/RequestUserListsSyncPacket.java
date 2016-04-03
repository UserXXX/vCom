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
import java.util.Date;

/**
 * Packet that notifies the client, that the server wants to synchronize the friend- and blocklist
 * if necessary.
 * @author Björn Lange
 */
public class RequestUserListsSyncPacket extends Packet {
	/**
	 * Protocol identifier for this packet.
	 */
	public static final int PROTOCOL_IDENTIFIER = 259;

	private Date friendlistTimestamp;
	private Date blocklistTimestamp;
	
	/**
	 * Gets the timestamp corresponding to the state of the friendlist.
	 * @return The timestamp corresponding to the state of the friendlist.
	 */
	public Date getFriendlistTimestamp() {
		return friendlistTimestamp;
	}

	/**
	 * Gets the timestamp corresponding to the state of the blocklist.
	 * @return The timestamp corresponding to the state of the blocklist.
	 */
	public Date getBlocklistTimestamp() {
		return blocklistTimestamp;
	}
	
	/**
	 * Creates a new RequestUserListsSyncPacket.
	 */
	public RequestUserListsSyncPacket() { }
	
	@Override
	public void readFrom(DataInputStream input) throws IOException {
		friendlistTimestamp = new Date(input.readLong());
		blocklistTimestamp = new Date(input.readLong());
	}

	@Override
	public void writeTo(DataOutputStream output) throws IOException {
		throw new UnsupportedOperationException(
				"Client is not allowed to send a RequestUserListsSyncPacket!");
	}
}
