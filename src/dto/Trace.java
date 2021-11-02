package dto;

import java.util.Date;

public class Trace {

    private int idTrace;
    private int user;
    private String operation;
    private Date date;

    
    public int getIdTrace() {
        return this.idTrace;
    }

    public void setIdTrace(int idTrace) {
        this.idTrace = idTrace;
    }
    

    public int getUser() {
        return this.user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    

}