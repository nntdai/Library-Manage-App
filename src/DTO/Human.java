package DTO;

public class Human {
    private int id;
    private String tel;
    private String name;
    private String address;

    public Human(){}

    public Human(int id,String tel,String name,String address){
        this.id=id;
        this.tel=tel;
        this.name=name;
        this.address=address;
    }

    public Human(String name,String tel,String address){
        this.tel=tel;
        this.name=name;
        this.address=address;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getTel(){
        return tel;
    }

    public void setTel(String tel){
        this.tel=tel;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address=address;
    }

    public void output(){
        System.out.print(id+" "+name+" "+tel+" "+address+" ");
    }
}

