package com.revature.models;

public class Transfer {

	private Integer transfer_id;
	private Integer sender_id;
	private Integer receiver_id;
	private Double amount;
	private String status;
	
	public Transfer() {}
	
	public Transfer(Integer sender, Double amount, Integer receiver) {
		super();
		this.sender_id = sender;
		this.receiver_id = receiver;
		this.amount = amount;
		this.status = "pending";
	}

	public Integer getTransfer_id() {
		return transfer_id;
	}

	public void setTransfer_id(Integer transfer_id) {
		this.transfer_id = transfer_id;
	}

	public Integer getSender_id() {
		return sender_id;
	}

	public void setSender_id(Integer sender_id) {
		this.sender_id = sender_id;
	}

	public Integer getReceiver_id() {
		return receiver_id;
	}

	public void setReceiver_id(Integer receiver_id) {
		this.receiver_id = receiver_id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((receiver_id == null) ? 0 : receiver_id.hashCode());
		result = prime * result + ((sender_id == null) ? 0 : sender_id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((transfer_id == null) ? 0 : transfer_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transfer other = (Transfer) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (receiver_id == null) {
			if (other.receiver_id != null)
				return false;
		} else if (!receiver_id.equals(other.receiver_id))
			return false;
		if (sender_id == null) {
			if (other.sender_id != null)
				return false;
		} else if (!sender_id.equals(other.sender_id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (transfer_id == null) {
			if (other.transfer_id != null)
				return false;
		} else if (!transfer_id.equals(other.transfer_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transfer [transfer_id=" + transfer_id + ", sender_id=" + sender_id + ", receiver_id=" + receiver_id
				+ ", amount=" + amount + ", status=" + status + "]";
	}
	
	
}
