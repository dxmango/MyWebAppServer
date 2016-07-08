package dataModels;

public class NormalHttpResponse {
	boolean succeed;
	Object information;

	public NormalHttpResponse() {
	}

	public boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}

	public Object getInformation() {
		return information;
	}

	public void setInformation(Object information) {
		this.information = information;
	}

}
