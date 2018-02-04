package com.doupton.douptonsubbook;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
// TODO: Need javadoc for each file too!
/**
 * A monthly subscription.
 */

public class Subscription {
    private static final int MAX_NAME = 20;
    private static final int MAX_COMMENT = 30;

    private String name;
    private Date startDate;
    private BigDecimal monthlyCharge;
    private String comment;

    /**
     * Construct a subscription object.
     * @param name subscription name (<= {@value MAX_NAME} characters)
     * @param startDate subscription start date
     * @param monthlyCharge subscription monthly charge
     */
    public Subscription(String name, Date startDate, BigDecimal monthlyCharge){
        setName(name);
        setStartDate(startDate);
        setMonthlyCharge(monthlyCharge);
    }

    /**
     * Construct a subscription object.
     * @param name subscription name (<= {@value MAX_NAME} characters)
     * @param startDate subscription start date
     * @param monthlyCharge subscription monthly charge
     * @param comment subscription comment (optional, <= {@value MAX_COMMENT} characters)
     */
    public Subscription(String name, Date startDate, BigDecimal monthlyCharge, String comment){
        this(name, startDate, monthlyCharge);
        setComment(comment);
    }

    /**
     * Get the name for this subscription.
     * @return subscription name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Set the name for this subscription.
     * @param name subscription name
     * @throws IllegalArgumentException Name exceeds {@value MAX_NAME} characters or is empty.
     */
    public void setName(String name) throws IllegalArgumentException {
        if (name.length() > MAX_NAME){
            throw new IllegalArgumentException("Name exceeds " + MAX_NAME + " characters.");
        } else if (name.length() == 0) {
            throw new IllegalArgumentException("Name cannot be empty.");
        } else {
            this.name = name;
        }
    }

    /**
     * Get the start date for this subscription.
     * @return start date
     */
    public Date getStartDate(){
        return this.startDate;
    }

    /**
     * Set the start date for this subscription.
     * @param date start date
     */
    public void setStartDate(Date date){
        startDate = date;
    }

    /**
     * Get the monthly charge for this subscription.
     * @return monthly charge
     */
    public BigDecimal getMonthlyCharge(){
        return this.monthlyCharge;
    }

    /**
     * Set the monthly charge for this subscription.
     * @param charge monthly charge
     * @throws IllegalArgumentException charge cannot be negative
     */
    public void setMonthlyCharge(BigDecimal charge){
        if (charge.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Charge cannot be negative.");
        } else {
            this.monthlyCharge = charge;
        }
    }

    /**
     * Get the comment for this subscription.
     * @return comment
     */
    public String getComment(){
        return this.comment;
    }

    /**
     * Set the comment for this subscription.
     * @param comment comment
     * @throws IllegalArgumentException Name exceeds {@value MAX_COMMENT} characters.
     */
    public void setComment(String comment) throws IllegalArgumentException {
        if (comment.length() > MAX_COMMENT) {
            throw new IllegalArgumentException("Comment exceeds " + MAX_COMMENT + " characters.");
        } else{
            this.comment = comment;
        }
    }
}
