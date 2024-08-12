package model;

public class Claim {
    private String dateOfClaim;
    private String description;
    private String status;

    private int claimId;
    private int productId;
    private String username;
    
//    // Constructor
//    public Claim(String dateOfClaim, String description, String status,int claim) {
//        this.dateOfClaim = dateOfClaim;
//        this.description = description;
//        this.status = status;
//    }

    // Default Constructor
    public Claim() {
    }

    // Getters and Setters
    public String getDateOfClaim() {
        return dateOfClaim;
    }

    public void setDateOfClaim(String dateOfClaim) {
        this.dateOfClaim = dateOfClaim;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}
