package DTO;

import java.time.LocalDate;

public class Readers extends Human{
    private LocalDate fineDate;
    private boolean isLocked;
    
    public Readers(int id,String tel,String name,String address,LocalDate fineDate){
        super(id, tel, name, address);
        this.fineDate=fineDate;
        if(this.fineDate==null){
            isLocked=true;
        }else{
            isLocked=false ;
        }
    }

    public Readers(){}

    public Readers(String name,String tel,String address){
        super(name, tel, address);
    }
    
    public LocalDate getFineDate(){
        return fineDate;
    }

    public boolean getisLocked(){
        return isLocked;
    }

    public void setFineDate(LocalDate fineDate){
        this.fineDate=fineDate;
        if(fineDate==null){
            isLocked=false;
        }else{
            isLocked=true;
        }
    }

    public void output(){
        super.output();;
        System.out.println(fineDate+" "+isLocked);
    }
    
    public boolean compare(String name,String tel,String address) {
    	if(getName().toUpperCase().equals(name.toUpperCase()) && getTel().toUpperCase().equals(tel.toUpperCase()) && getAddress().toUpperCase().equals(address.toUpperCase())) {
    		return true;
    	}else {
    		return false;
    	}
    }
}

