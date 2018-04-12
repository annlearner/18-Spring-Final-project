package com.neuSpring18.dto;

public enum Category {

    NEW, USED, CERTIFIED;

    public static Category getCategory(String c) {

        switch(c) {

            case("new"): return NEW;
            case("used"): return USED;
            case("certified"): return CERTIFIED;
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case NEW:
                return "new";
            case USED:
                return "used";
            case CERTIFIED:
                return "certified";
        }
        return super.toString();
    }
}
