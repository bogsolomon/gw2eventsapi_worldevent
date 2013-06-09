package ca.bsolomon.gw2events.worldevent.util;

public class Server {

	private final int serverId;
	private final String serverName;
	
	private int fHashCode;
	
	public Server(int serverId, String serverName) {
		this.serverId = serverId;
		this.serverName = serverName;
	}

	public int getServerId() {
		return serverId;
	}

	public String getServerName() {
		return serverName;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Server))return false;
	    Server otherObject = (Server)other;
	    
	    if (otherObject.getServerId() == serverId)
	    	return true;
	    else 
	    	return false;
	}

	@Override
	public int hashCode() {
		if (fHashCode == 0) {
			int result = HashCodeUtil.SEED;
			result = HashCodeUtil.hash(result, serverId);
			fHashCode = result;
		}
		
		return fHashCode;
	}
	
	
}
