package nl.rooftopenergy.bionic.rest.util;

/**
 * Created by alex on 2/19/15.
 */
public class MapEntity {
        private Double value;
        private final String name;
        private final Integer y;
        private final Integer x;

        MapEntity(String name, Integer y, Integer x){
            this.name = name;
            this.y = y;
            this.x = x;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Integer getY() {
            return y;
        }

        public Integer getX() {
            return x;
        }

}
