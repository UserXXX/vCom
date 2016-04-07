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
import java.util.List;

import de.vCom.client.desktop.model.UserIdentifier;

/**
 * Packet for transferring the list of friend identifiers from server to client.
 * @author Björn Lange
 */
public class FriendlistSyncPacket extends Packet {
	/**
	 * Protocol identifier for this packet.
	 */
	public static final int PROTOCOL_IDENTIFIER = 261;

	private List<UserIdentifier> friendlist;
	
	/**
	 * Gets the list of friend identifiers.
	 * @return Gets the list of friend identifiers send by the server.
	 */
	public List<UserIdentifier> getFriendlist() {
		return friendlist;
	}
	
	/**
	 * Creates a new FriendlistSyncPacket.
	 */
	public FriendlistSyncPacket() { }
	
	@Override
	public void readFrom(DataInputStream input) throws IOException {
		friendlist = readUserIdentifierList(input);
	}

	@Override
	public void writeTo(DataOutputStream output) throws IOException {
		throw new UnsupportedOperationException(
				"Client is not allowed to send a FriendlistSyncPacket!");
	}
}
