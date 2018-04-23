package com.stratum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class InvalidResourceSetupException extends RuntimeException {

	private static final long serialVersionUID = -8860031929489837481L;
	
	private Object resource;
    private String reason;

    public InvalidResourceSetupException(Object resource, String reason) {
        super(String.format("%s has invalid setup, cause: %s", resource, reason));
        this.setResource(resource);
        this.setReason(reason);
    }
    
	public Object getResource() {
		return resource;
	}

	public void setResource(Object resource) {
		this.resource = resource;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}