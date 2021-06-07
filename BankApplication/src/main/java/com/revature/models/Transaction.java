package com.revature.models;

public class Transaction {

	private Integer id;
	private String type;
	private Integer ba_id;
	private Double amount;
	private Integer recv_id;
	private String status;
	
	public Transaction() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getBa_id() {
		return ba_id;
	}

	public void setBa_id(Integer ba_id) {
		this.ba_id = ba_id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getRecv_id() {
		return recv_id;
	}

	public void setRecv_id(Integer recv_id) {
		this.recv_id = recv_id;
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
		result = prime * result + ((ba_id == null) ? 0 : ba_id.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((recv_id == null) ? 0 : recv_id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Transaction other = (Transaction) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (ba_id == null) {
			if (other.ba_id != null)
				return false;
		} else if (!ba_id.equals(other.ba_id))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (recv_id == null) {
			if (other.recv_id != null)
				return false;
		} else if (!recv_id.equals(other.recv_id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if ("deposit".equalsIgnoreCase(type) || "withdrawal".equalsIgnoreCase(type)) {
			return "Transaction [id=" + id + ", type=" + type + ", bank account id=" + ba_id + ", amount=" + amount + "]";
		}
		return "Transaction [id=" + id + ", type=" + type + ", bank account id=" + ba_id + ", amount=" + amount + ", receiving account id="
				+ recv_id + ", status=" + status + "]";
	}
	
	
}
