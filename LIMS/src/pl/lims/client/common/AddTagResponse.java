package pl.lims.client.common;

import java.io.Serializable;

public class AddTagResponse implements Serializable
{

	private static final long	serialVersionUID	= -7629430504696469762L;

	private boolean success;
	private String msg;
	
	public AddTagResponse(){}
	public AddTagResponse(String msgTag, boolean success)
	{
		this.success = success;
		this.msg = msgTag;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public String getMsg()
	{
		return msg;
	}
	
}
