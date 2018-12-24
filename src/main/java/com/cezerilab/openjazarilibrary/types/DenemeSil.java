/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cezerilab.openjazarilibrary.types;

/**
 *
 * @author BAP1
 */
public class DenemeSil {
    public static void main(String[] args) {
        System.out.println(checkDays(WeekDays.Friday));
    }

    private static boolean checkDays(WeekDays day) {
        if (day==WeekDays.Saturday) {
            return true;
        }else{
            return false;
        }
    }
}
