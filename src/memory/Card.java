package memory;

public class Card {

    public String name;
    private int id;
    private boolean turned;
    private boolean matched;
    private String image;

    public Card (String name, int id){
        this.name = name;
        this.id = id;
        this.turned = false;
        this.image = image;
        this.matched = false;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public boolean isTurned(){
        return turned;
    }

    public boolean isMatched(){
        return matched;
    }

    public void setTurned(){
        this.turned = true;
    }

    public void setMatched(){
        this.matched = true;
    } 
     public boolean getTurned(){
        return turned;
    }
    public boolean getMatched(){
        return matched;
    }
    public void setCovered(){
        this.turned = false;
    }
    public String getImage() {
        return image;
    }

    public String toString(){
        String info = "";
        return info + name + " " + id + " " +turned + " " + matched;
    }

}