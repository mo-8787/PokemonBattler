package org.example;

public enum Types {
    WATER("Vatten"),
    DRAGON("Drak"),
    FIRE("Eld"),
    FLYING("Flygande"),
    GROUND("Jord"),
    NORMAL("Normal"),
    DARK("Mörk"),
    ICE("Is"),
    ELECTRIC("Elektrisk"),
    GRASS("Gräs"),
    FIGHTING("Kamp"),
    POISON("Gift"),
    PSYCHIC("Psykisk"),
    BUG("Insekt"),
    ROCK("Sten"),
    GHOST("Spöke"),
    STEEL("Stål"),
    FAIRY("Fe");

    private final String label;

    Types(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
