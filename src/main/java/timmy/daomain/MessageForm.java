package timmy.daomain;

import org.apache.struts.action.ActionForm;

public class MessageForm extends ActionForm{


	private static final long serialVersionUID = -4383076011316483267L;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
