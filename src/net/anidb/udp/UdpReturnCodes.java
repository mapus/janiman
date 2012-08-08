/*
 * Java AniDB API - A Java API for AniDB.net
 * (c) Copyright 2009 grizzlyxp
 * http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package net.anidb.udp;

/**
 * The return codes.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 29.11.2009
 */
public interface UdpReturnCodes {
	// POSITIVE 2XX
	int LOGIN_ACCEPTED = 200;
	int LOGIN_ACCEPTED_NEW_VER = 201;
	int LOGGED_OUT = 203;
	int RESOURCE = 205;
	int STATS = 206;
	int TOP = 207;
	int UPTIME = 208;
	int ENCRYPTION_ENABLED = 209;
	int MYLIST_ENTRY_ADDED = 210;
	int MYLIST_ENTRY_DELETED = 211;
	int ADDED_FILE = 214;
	int ADDED_STREAM = 215;
	int ENCODING_CHANGED = 219;
	int FILE = 220;
	int MYLIST = 221;
	int MYLIST_STATS = 222;
	int GROUP_STATUS = 225;
	int ANIME = 230;
	int ANIME_BEST_MATCH = 231;
	int RANDOMANIME = 232;
	int ANIME_DESCRIPTION = 233;
	int CHARACTER = 235;
	int EPISODE = 240;
	int CREATOR = 245;
	int GROUP = 250;
	int BUDDY_LIST = 253;
	int BUDDY_STATE = 254;
	int BUDDY_ADDED = 255;
	int BUDDY_DELETED = 256;
	int BUDDY_ACCEPTED = 257;
	int BUDDY_DENIED = 258;
	int VOTED = 260;
	int VOTE_FOUND = 261;
	int VOTE_UPDATED = 262;
	int VOTE_REVOKED = 263;
	int NOTIFICATION_ENABLED = 270;
	int NOTIFICATION_NOTIFY = 271;
	int NOTIFICATION_MESSAGE = 272;
	int NOTIFICATION_BUDDY = 273;
	int NOTIFICATION_SHUTDOWN = 274;
	int PUSHACK_CONFIRMED = 280;
	int NOTIFYACK_SUCCESSFUL_M = 281;
	int NOTIFYACK_SUCCESSFUL_N = 282;
	int NOTIFICATION = 290;
	int NOTIFYLIST = 291;
	int NOTIFYGET_MESSAGE = 292;
	int NOTIFYGET_NOTIFY = 293;
	int SENDMSG_SUCCESSFUL = 294;
	int USER = 295;
	// AFFIRMATIVE/NEGATIVE 3XX
	int PONG = 300;
	int AUTHPONG = 301;
	int NO_SUCH_RESOURCE = 305;
	int API_PASSWORD_NOT_DEFINED = 309;
	int FILE_ALREADY_IN_MYLIST = 310;
	int MYLIST_ENTRY_EDITED = 311;
	int MULTIPLE_MYLIST_ENTRIES = 312;
	int SIZE_HASH_EXISTS = 314;
	int INVALID_DATA = 315;
	int STREAMNOID_USED = 316;
	int NO_SUCH_FILE = 320;
	int NO_SUCH_ENTRY = 321;
	int MULTIPLE_FILES_FOUND = 322;
	int NO_GROUPS_FOUND = 325;
	int NO_SUCH_ANIME = 330;
	int NO_SUCH_ANIME_DESCRIPTION = 333;
	int NO_SUCH_CHARACTER = 335;
	int NO_SUCH_EPISODE = 340;
	int NO_SUCH_CREATOR = 345;
	int NO_SUCH_GROUP = 350;
	int BUDDY_ALREADY_ADDED = 355;
	int NO_SUCH_BUDDY = 356;
	int BUDDY_ALREADY_ACCEPTED = 357;
	int BUDDY_ALREADY_DENIED = 358;
	int NO_SUCH_VOTE = 360;
	int INVALID_VOTE_TYPE = 361;
	int INVALID_VOTE_VALUE = 362;
	int PERMVOTE_NOT_ALLOWED = 363;
	int ALREADY_PERMVOTED = 364;
	int NOTIFICATION_DISABLED = 370;
	int NO_SUCH_PACKET_PENDING = 380;
	int NO_SUCH_ENTRY_M = 381;
	int NO_SUCH_ENTRY_N = 382;
	int NO_SUCH_MESSAGE = 392;
	int NO_SUCH_NOTIFY = 393;
	int NO_SUCH_USER = 394;
	// NEGATIVE 4XX
	int NOT_LOGGED_IN = 403;
	int NO_SUCH_MYLIST_FILE = 410;
	int NO_SUCH_MYLIST_ENTRY = 411;
	// CLIENT SIDE FAILURE 5XX
	int LOGIN_FAILED = 500;
	int LOGIN_FIRST = 501;
	int ACCESS_DENIED = 502;
	int CLIENT_VERSION_OUTDATED = 503;
	int CLIENT_BANNED = 504;
	int ILLEGAL_INPUT_OR_ACCESS_DENIED = 505;
	int INVALID_SESSION = 506;
	int NO_SUCH_ENCRYPTION_TYPE = 509;
	int ENCODING_NOT_SUPPORTED = 519;
	int BANNED = 555;
	int UNKNOWN_COMMAND = 598;
	// SERVER SIDE FAILURE 6XX
	int INTERNAL_SERVER_ERROR = 600;
	int ANIDB_OUT_OF_SERVICE = 601;
	int SERVER_BUSY = 602;
	int API_VIOLATION = 666;
	// OTHER
	int VERSION = 998;
}
