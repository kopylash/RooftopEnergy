package nl.rooftopenergy.bionic.rest.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;



/**
 * Created by Alex Iakovenko on 2/19/15.
 */
public class MapData {
    private List<MapEntity> list;

    public MapData(){
        list = new ArrayList<MapEntity>();
    }

    public MapData(List set){
        list = set;
    }
    public boolean addEntity(MapEntity entity){
        return list.add(entity);
    }

    public void setValue(String name, Double value){
        for (MapEntity e : list){
            if (e.getName().equals(name)){
                e.setValue(value) ;
                break;
            }
        }
    }

    public void clear(){
        for (MapEntity e : list){
            e.setValue(0d);
        }
    }
    public MapEntity getEntity(String name){
        for (MapEntity e : list){
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public List<MapEntity> getList(){
        return Collections.unmodifiableList(list);
    }


}
