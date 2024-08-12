package model;

import java.util.List;

public class Product {
    private int productId;
    private String productName;
    private String sno;
    private java.sql.Date pdate;
    private List<Claim> claims;
	private boolean hasClaims;
    private int claimCount; // Add this property
    private String username;


    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public java.sql.Date getPdate() {
        return pdate;
    }

    public void setPdate(java.sql.Date pdate) {
        this.pdate = pdate;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }

    public boolean hasClaims() {
        return claims != null && !claims.isEmpty();
    }
    
    public boolean isHasClaims() {
        return hasClaims;
    }
    
    public void setHasClaims(boolean hasClaims) {
        this.hasClaims = hasClaims;
    }

	public int getClaimCount() {
		return claimCount;
	}

	public void setClaimCount(int claimCount) {
		this.claimCount = claimCount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
