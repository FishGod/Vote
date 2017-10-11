package wjc.model;

public class VoteInfor {
	private int voteId;
	private String voteName;
	private VoteDetail detail;
	private String voteType;
	public int getVoteId() {
		return voteId;
	}
	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}
	public String getVoteName() {
		return voteName;
	}
	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}
	public VoteDetail getDetail() {
		return detail;
	}
	public void setDetail(VoteDetail detail) {
		this.detail = detail;
	}
	public String getVoteType() {
		return voteType;
	}
	public void setVoteType(String voteType) {
		this.voteType = voteType;
	}
	
	
}
