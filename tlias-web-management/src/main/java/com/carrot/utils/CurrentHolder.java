package com.carrot.utils;

public class CurrentHolder {
    private static final ThreadLocal<Integer> CURRENT_HOLDER = new ThreadLocal<>();

    public static void setCurrentId(Integer empId) {
        CURRENT_HOLDER.set(empId);
    }

    public static Integer getCurrentId() {
        return CURRENT_HOLDER.get();
    }

    public static void remove() {
        CURRENT_HOLDER.remove();
    }

}
