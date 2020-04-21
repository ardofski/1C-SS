package Model;

import Model.Room.Location;

public class Path {

    Location l1,l2;
    public Path(int i1,int i2,int j1,int j2){
        l1 = new Location( i1,j1);
        l2 = new Location( i2,j2);
    }

    public Path( Location l1, Location l2 ){
        this.l1 = l1;
        this.l2 = l2;
    }

    public Location location1(){
        return l1;
    }

    public Location location2(){
        return l2;
    }

}
