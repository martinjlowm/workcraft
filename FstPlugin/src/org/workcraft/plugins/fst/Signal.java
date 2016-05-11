package org.workcraft.plugins.fst;

import org.workcraft.exceptions.ArgumentException;
import org.workcraft.observation.PropertyChangedEvent;
import org.workcraft.plugins.fsm.Symbol;

public class Signal extends Symbol {

    public static final String PROPERTY_TYPE = "Type";

    public enum Type {
        INPUT("input"),
        OUTPUT("output"),
        INTERNAL("internal"),
        DUMMY("dummy");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public static Type fromString(String s) {
            for (Type item : Type.values()) {
                if ((s != null) && (s.equals(item.name))) {
                    return item;
                }
            }
            throw new ArgumentException("Unexpected string: " + s);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private Type type = Type.DUMMY;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
        sendNotification(new PropertyChangedEvent(this, PROPERTY_TYPE));
    }

    public boolean hasDirection() {
        return getType() != Type.DUMMY;
    }

}

