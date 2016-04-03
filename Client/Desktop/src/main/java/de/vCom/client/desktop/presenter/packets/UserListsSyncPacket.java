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

import de.vCom.client.desktop.model.UserList;

/**
 * Packet for synchronizing the clients friend-/blocklist data with the servers. Send as a response
 * to the servers request for synchronization or initiatively when the friend-/blocklist changed.
 * @author Björn Lange
 */
public class UserListsSyncPacket extends Packet {
	/**
	 * Protocol identifier for this packet.
	 */
	public static final int PROTOCOL_IDENTIFIER = 260;

	/**
	 * Constant for the case when the servers and clients lists are synchronized.
	 */
	public static final byte SYNC_NO_CHANGES = 0;
	
	/**
	 * Constant for the case that the server has the newer version.
	 */
	public static final byte SYNC_SERVER_NEWER = 1;
	
	/**
	 * Constant for the case that the client has the newer version.
	 */
	public static final byte SYNC_CLIENT_NEWER = 2;
	
	private byte friendlistFlag;
	private UserList friendlist = null;
	private byte blocklistFlag;
	private UserList blocklist = null;
	
	/**
	 * Gets the synchronization state flag for the friendlist.
	 * @return The synchronization state flag for the friendlist.
	 */
	public byte getFriendlistFlag() {
		return friendlistFlag;
	}

	/**
	 * Sets the synchronization state flag for the friendlist.
	 * @param friendlistFlag The synchronization state flag for the friendlist.
	 */
	public void setFriendlistFlag(byte friendlistFlag) {
		this.friendlistFlag = friendlistFlag;
	}

	/**
	 * Gets the friendlist transferred with this packet if present.
	 * @return The friendlist transferred with this packet or null if none is present.
	 */
	public UserList getFriendlist() {
		return friendlist;
	}

	/**
	 * Sets the friendlist transferred with this packet if present.
	 * @param friendlist The friendlist transferred with this packet or null if none is present.
	 */
	public void setFriendlist(UserList friendlist) {
		this.friendlist = friendlist;
	}

	/**
	 * Gets the synchronization state flag for the blocklist.
	 * @return The synchronization state flag for the blocklist.
	 */
	public byte getBlocklistFlag() {
		return blocklistFlag;
	}

	/**
	 * Sets the synchronization state flag for the blocklist.
	 * @param friendlistFlag The synchronization state flag for the blocklist.
	 */
	public void setBlocklistFlag(byte blocklistFlag) {
		this.blocklistFlag = blocklistFlag;
	}

	/**
	 * Gets the blocklist transferred with this packet if present.
	 * @return The blocklist transferred with this packet or null if none is present.
	 */
	public UserList getBlocklist() {
		return blocklist;
	}

	/**
	 * Sets the blocklist transferred with this packet if present.
	 * @param friendlist The blocklist transferred with this packet or null if none is present.
	 */
	public void setBlocklist(UserList blocklist) {
		this.blocklist = blocklist;
	}
	
	/**
	 * Creates a new UserListsSyncPacket.
	 */
	public UserListsSyncPacket() { }

	@Override
	public void readFrom(DataInputStream input) throws IOException {
		throw new UnsupportedOperationException(
				"Client is not allowed to receive a UserListsSyncPacket!");
	}

	@Override
	public void writeTo(DataOutputStream output) throws IOException {
		checkStateValid();
		
		output.writeInt(PROTOCOL_IDENTIFIER);
		output.writeByte(friendlistFlag);
		if (friendlist != null) {
			writeUserList(output, friendlist);
		}
		output.writeByte(blocklistFlag);
		if (blocklist != null) {
			writeUserList(output, blocklist);
		}
	}

	/**
	 * Checks whether the packets state is valid for sending.
	 * @throws IllegalStateException If the state is not applicable for sending.
	 */
	private void checkStateValid() {
		if ((friendlistFlag == SYNC_NO_CHANGES || friendlistFlag == SYNC_SERVER_NEWER)
				&& friendlist != null) {
			throw new IllegalStateException("The friendlist is not allowed to be non null if the "
					+ "sync flag is SYNC_NP_CHANGES or SYNC_SERVER_NEWER!");
		}
		
		if (friendlistFlag == SYNC_CLIENT_NEWER	&& friendlist == null) {
			throw new IllegalStateException("The friendlist is not allowed to be null if the "
					+ "sync flag is SYNC_CLIENT_NEWER!");
		}
		
		if ((blocklistFlag == SYNC_NO_CHANGES || blocklistFlag == SYNC_SERVER_NEWER)
				&& blocklist != null) {
			throw new IllegalStateException("The blocklist is not allowed to be non null if the "
					+ "sync flag is SYNC_NP_CHANGES or SYNC_SERVER_NEWER!");
		}
		
		if (blocklistFlag == SYNC_CLIENT_NEWER	&& blocklist == null) {
			throw new IllegalStateException("The blocklist is not allowed to be null if the "
					+ "sync flag is SYNC_CLIENT_NEWER!");
		}
	}
}
