package ca.bsolomon.gw2events.worldevent.enums;

public enum Waypoint {

	TEQUATL("[&BNABAAA=]"),
	SHATERRER("[&BE4DAAA=]"),
	JORMAG("[&BHoCAAA=]"),
	MAW("[&BMIDAAA=]"),
	SB("[&BPcAAAA=]"),
	FIREELE("[&BEcAAAA=]"),
	JUNGLEWURM("[&BD4BAAA=]"),
	GOLEM("[&BNQCAAA=]"),
	DREDGE("[&BD8FAAA=]"),
	HARATHI("[&BLEAAAA=]"),
	FOULBEAR("[&BE4BAAA=]"),
	HYDRA("[&BKgBAAA=]"),
	FIRESHAM("[&BO4BAAA=]"),
	KARKA("[&BNcGAAA=]");
	
	private String wp;
	
	Waypoint(String wp) {
		this.wp = wp;
	}
	
	public String toString() {return wp;}
}
